/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author Capera
 */
public class DaoUsuarioLogin extends Conexion {

    Connection cnn;

    public DaoUsuarioLogin(Connection cnn) {
        this.cnn = cnn;
    }

    private DaoUsuarioLogin() {
    }

    public String[] contraseniaAleatoria() {
        String[] Valores = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9",};
        String[] pass = new String[15];
        for (int i = 0; i < pass.length; i++) {
            int rdn = (int) (Math.random() * 61);
            pass[i] = Valores[rdn];
            System.out.printf(pass[i]);
        }
        return pass;
    }

    public static void main(String[] args) {
        DaoUsuarioLogin DLog = new DaoUsuarioLogin();
        DLog.contraseniaAleatoria();
    }

    public BeanUsuariosLogin consultar(BeanUsuariosLogin vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT * FROM usuario WHERE Usuario = ? "
                + "and Contrasenia = ?");
        sentencia.setString(1, vo.getUsuarioNombre());
        sentencia.setString(2, vo.getContrasenia());
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            vo.setIdUsuario(resultado.getInt("idUsuario"));
            vo.setIdparticipante(resultado.getInt("Idpersona"));
            vo.setUsuarioNombre(resultado.getString("Usuario"));
            vo.setContrasenia(resultado.getString("Contrasenia"));

            return vo;
        }
        return null;
    }

    public String consultarRol(BeanUsuariosLogin vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT r.NombreRol "
                + "from rol r, usuario u, rolusuario ru "
                + "where r.idRol = ru.IdRol "
                + "and u.idUsuario = ru.IdUsuario "
                + "and u.Usuario = ? "
                + "and u.Contrasenia = ?");
        sentencia.setString(1, vo.getUsuarioNombre());
        sentencia.setString(2, vo.getContrasenia());
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return resultado.getString("r.NombreRol");
        }
        return null;

    }

}
