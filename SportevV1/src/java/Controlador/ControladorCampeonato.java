/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanCategoria;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoCampeonato;
import Modelo.Dao.DaoCategoria;
import Modelo.Dao.DaoPreinscripcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class ControladorCampeonato {

    /**
     * Creates a new instance of ControladorCampeonato
     */
    private String nombre;
    private BeanCampeonato BCamp = new BeanCampeonato();
    private List<BeanCampeonato> consCamp;
    private BeanUsuariosLogin usuario;
    
    private boolean x=false;
    
    public String respuesta = "";
    public String emviarPerfil()  {
        if (true) {
            respuesta = "/perfil.xhtml";
        }
        return respuesta;
    }
    
    public String activar(){
        x=true;
    return "/Pantallas/rolAdministradorIni.xhtml";
    }
    
    public String registrarCampeonato() {
        DaoCampeonato DCamp = new DaoCampeonato();
        if (DCamp.registrarCampeonato(this.BCamp)) {
            respuesta = "/index.xhtml";

        } else {
            respuesta = "/Error.xhtml";
        }
        return respuesta;
    }

    public String eliminar() throws SQLException {
        DaoCampeonato DCamp = new DaoCampeonato();
        if (DCamp.eliminar(this.BCamp)) {
            respuesta = "CrudCampeonato.xhtml";
        } else {
            respuesta = "/Error.xhtml";
        }
        return respuesta;
    }

    public void editar(int id) {
        DaoCampeonato DCampeonato = new DaoCampeonato();
        List<BeanCampeonato> listar;
        listar = DCampeonato.consultar(id);
        for (BeanCampeonato BCampeonato : listar) {
            BCamp = BCampeonato;
        }

    }

    public String modificar() {
        DaoCampeonato DCamp = new DaoCampeonato();
        System.out.println(BCamp.getIdCampeonato());
        if (DCamp.modificar(this.BCamp)) {
            respuesta = "CrudCampeonato.xhtml";
        } else {
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }

    public ControladorCampeonato() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        try {
            DaoCampeonato DCampeonato = new DaoCampeonato();
            consCamp = DCampeonato.listarCampeonato();

        } catch (Exception e) {
        }
    }

    public BeanCampeonato getBCamp() {
        return BCamp;
    }

    public List<BeanCampeonato> getConsCamp() {
        return consCamp;
    }

    public void setConsCamp(List<BeanCampeonato> consCamp) {
        this.consCamp = consCamp;
    }

    public void setBCamp(BeanCampeonato BCamp) {
        this.BCamp = BCamp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

}
