/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoUsuarioLogin;
import java.sql.Connection;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class Controladorlogin {


    /**
     * Creates a new instance of LoginAppBean
     */
    private BeanUsuariosLogin usuario;

    
    
    /**
     * Creates a new instance of LoginAppBean
     */



    public Controladorlogin() {
        usuario = new BeanUsuariosLogin();
       
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public String validarUsuario() {
        try {
            
            //usuario = (BeanUsuariosLogin) session.getAttribute("persona");
            DaoUsuarioLogin control = new DaoUsuarioLogin(Conexion.obtenerConexion());
            BeanUsuariosLogin validarUsuario = control.consultar(this.usuario);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("user", validarUsuario);
            if(validarUsuario == null){
                FacesMessage mensaje = new FacesMessage("Datos Invalidos");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null,mensaje );
                System.out.println("Datos Invalidos ");
                return "";
            }
            else{
                String nombreRol = control.consultarRol(this.usuario);
                String retorno = "";
                FacesMessage mensaje = new FacesMessage("Datos validos");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null,mensaje );
                System.out.println("Usario Valido puedes ingresar ");
                
                if(nombreRol!= null && nombreRol.equals("Administrador")){
                    retorno = "CrudCampeonato";
                }
                
                if(nombreRol!= null && nombreRol.equals("Entrenador")){
                    retorno = "EquiposEntrenador";
                }
                
                if(nombreRol!= null && nombreRol.equals("Jugador")){
                    retorno = "index";
                }
                
                return retorno;
            }      
            
        } catch (NamingException ex) {
           return "";
        } catch (SQLException ex) {
            return "";
        }
    }
    public String cerrarSession(){
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session.removeAttribute("user");

    
    return "inicio.xhtml";
    }

}
