/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class BeanUsuariosLogin {

    /**
     * Creates a new instance of BeanUsuariosLogin
     */
    private int idUsuario;
    private int idparticipante;
    private int rol;
    private String nombreRol;
    private String usuarioNombre;
    private String contrasenia;

    public BeanUsuariosLogin(int idUsuario, String nombreRol, String usuarioNombre) {
        this.idUsuario = idUsuario;
        this.nombreRol = nombreRol;
        this.usuarioNombre = usuarioNombre;
    }

    
    public BeanUsuariosLogin() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(int idparticipante) {
        this.idparticipante = idparticipante;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
