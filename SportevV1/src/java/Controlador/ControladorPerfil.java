/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.Daoperfil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class ControladorPerfil {

    /**
     * Creates a new instance of ControladorPerfil
     */
    BeanUsuariosLogin usuario;
    BeanDatosPersona Dpersona;
    public String respuesta = "";
    private List<BeanDatosPersona> datos;
    
    public String modificar(){
        Daoperfil Dper = new Daoperfil();
        if (Dper.modificar()) {
            respuesta = "index.xhtml";
        }else{
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }
    public ControladorPerfil() {
           HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        
        try {
           Daoperfil Dper = new Daoperfil();
            datos = Dper.listarDato();
        } catch (Exception e) {
        }
    }
    public void editar(int id){
        Daoperfil Dperson = new Daoperfil();
        List<BeanDatosPersona> listar;
        listar = Dperson.consular(id);
        for (BeanDatosPersona BPer : listar) {
            Dpersona = BPer;
        }
    }
    public String modifcarDatos(){
        Daoperfil Dp = new Daoperfil();
          System.out.println(Dpersona.getId());
          if (Dp.modificarDatos(this.Dpersona)) {
            respuesta = "Perfil.xhtml";
        }else{
              respuesta = "Error.xhtml";
          }
        return  respuesta;
    }
    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public List<BeanDatosPersona> getDatos() {
        return datos;
    }

    public void setDatos(List<BeanDatosPersona> datos) {
        this.datos = datos;
    }

    public BeanDatosPersona getDper() {
        return Dpersona;
    }

    public void setDper(BeanDatosPersona Dper) {
        this.Dpersona = Dper;
    }

    
    
    
}
