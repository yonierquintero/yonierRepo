/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanMensaje;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoMensaje;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class ControladorMensaje {

    /**
     * Creates a new instance of ControladorMensaje
     */
    BeanMensaje Menss;
    private BeanUsuariosLogin user;
    private String respuesta = "";
    public ControladorMensaje() {
    }
       public String registrarCampeonato() {
        DaoMensaje Dms = new DaoMensaje();
        if (Dms.enviarMensaje(this.user)) {
            respuesta = "/index.xhtml";

        } else {
            respuesta = "/Error.xhtml";
        }
        return respuesta;
    }
    
    public List<SelectItem> listaAdmin() {
        DaoMensaje Dmj = new DaoMensaje();
        List<BeanUsuariosLogin> listar;
        List<SelectItem> listarAd = new ArrayList();
        listar = Dmj.listarUsuario();
        listar.stream().forEach((s) -> {
        listarAd.add(new SelectItem(s.getIdUsuario(),s.getUsuarioNombre(),s.getNombreRol()));
    });
        return listarAd;
    }

    public BeanMensaje getMenss() {
        return Menss;
    }

    public void setMenss(BeanMensaje Menss) {
        this.Menss = Menss;
    }

    public BeanUsuariosLogin getUser() {
        return user;
    }

    public void setUser(BeanUsuariosLogin user) {
        this.user = user;
    }
    
    
}
