/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanMensaje;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
public class DaoMensaje extends Conexion {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement psp = null;

    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();
    public boolean listo = false;

    public DaoMensaje() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");

    }

    public boolean enviarMensaje(Object obj) {
        BeanMensaje BeMssje = (BeanMensaje) obj;
        try {
            puente = obtenerConexion().createStatement();
            puente.executeUpdate("INSERT INTO `mensaje` ( `idemisor`, `idreceptor`, `asunto`, `texto`) "
                    + "VALUES ('" + BLog.getIdUsuario() + "','" + BeMssje.getIdReceptor() + "','" + BeMssje.getAsunto() + "' , '" + BeMssje.getTexto() + "',"
                    + "'" + BeMssje.getTexto() + "');");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }

    public List<BeanMensaje> verMensje() {
        List<BeanMensaje> vermensaje = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("select ps.`Nombre`, mj.`asunto`, mj.`text`o \n"
                    + "FROM mensaje as mj INNER JOIN usuario as us ON  us.`idUsuario `= mj.`idreceptor` INNER JOIN persona as ps ON ps.`Id` = us.`Idpersona` "
                    + " WHERE us.`Idpersona` = '" + BLog.getIdparticipante() + "'");
            while (rs.next()) {
                BeanMensaje BeaMess = new BeanMensaje();
                BeaMess.setIdEmisor(rs.getString(1));
                BeaMess.setAsunto(rs.getString(2));
                BeaMess.setTexto(rs.getString(3));

                vermensaje.add(BeaMess);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vermensaje;
    }

  public List<BeanUsuariosLogin> listarUsuario() {
        List<BeanUsuariosLogin> listCat = new ArrayList();

        try {
            puente = Conexion.obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT per.`Nombre`, us.`idUsuario`, rl.`NombreRol` FROM persona "
                    + "as per INNER JOIN usuario as us on us.`Idpersona` = per.Id INNER JOIN rolusuario "
                    + "as rus ON rus.IdUsuario = us.idUsuario INNER JOIN rol as rl ON rl.idRol = rus.IdRol");

            while (rs.next()) {
                listCat.add(new BeanUsuariosLogin(rs.getInt("idUsuario"), rs.getString("Nombre"),rs.getString("NombreRol")));
            }
            Conexion.desconectarBD(conn);
        } catch (Exception e) {
            try {
                Conexion.reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoMensaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCat;
    }
}
