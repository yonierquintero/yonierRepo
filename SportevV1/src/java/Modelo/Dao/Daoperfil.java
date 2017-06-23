/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
public class Daoperfil extends Conexion {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public boolean listo = false;
    BeanUsuariosLogin Beusu = new BeanUsuariosLogin();

    public Daoperfil() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Beusu = (BeanUsuariosLogin) session.getAttribute("user");
    }

    public boolean modificar() {
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("update usuario set Usuario = '" + Beusu.getUsuarioNombre() + "',Contrasenia = '" + Beusu.getContrasenia() + "' where idUsuario = '" + Beusu.getIdUsuario() + "';");

            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (Exception ex) {
                Logger.getLogger(DaoUsuarioLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;

    }
    
    public List<BeanDatosPersona> consular(int id){
        List<BeanDatosPersona> lisXIdper = new ArrayList();
        BeanDatosPersona Bemper = new BeanDatosPersona();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("update usuario set Usuario = '" + Beusu.getUsuarioNombre() + "',Contrasenia = ' where = '"+id+"';");
                while (rs.next()) {
                Bemper.setNombre(rs.getString(1));
                Bemper.setApellido(rs.getString(2));
                Bemper.setDocumento(rs.getString(3));
                Bemper.setCorreo(rs.getString(4));

                lisXIdper.add(Bemper);
                }
                desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lisXIdper;
    }
    public boolean modificarDatos(Object obc){
        BeanDatosPersona Bdatos = (BeanDatosPersona)obc;
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("Update persona set Nombre = '"+Bdatos.getNombre()+"',Apellido = '"+Bdatos.getApellido()+"',Identificacion = '"+Bdatos.getDocumento()+"',Email = '"+Bdatos.getCorreo()+"' ");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (Exception ex) {
                Logger.getLogger(Daoperfil.class.getName()).log(Level.SEVERE, null,ex);
            }
            e.printStackTrace();
        }
        return listo;
    }
    public List<BeanDatosPersona> listarDato() {
        List<BeanDatosPersona> listarDatos = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("Select id, Nombre,Apellido,Identificacion,Email From persona where Id = '"+Beusu.getIdparticipante()+"'");
            while (rs.next()) {
                BeanDatosPersona Bpersona = new BeanDatosPersona();
                Bpersona.setId(rs.getString(1));
                Bpersona.setNombre(rs.getString(2));
                Bpersona.setApellido(rs.getString(3));
                Bpersona.setDocumento(rs.getString(4));
                Bpersona.setCorreo(rs.getString(5));

                listarDatos.add(Bpersona);
            }
            desconectarBD(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listarDatos;
    }
}
