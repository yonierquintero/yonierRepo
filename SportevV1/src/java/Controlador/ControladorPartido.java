/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanEquipo;
import Modelo.Bean.BeanPartido;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoCampeonato;
import Modelo.Dao.DaoEquipo;
import Modelo.Dao.DaoPartido;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class ControladorPartido {

    /**
     * Creates a new instance of ControladorPartido
     */
    private BeanPartido BPar = new BeanPartido();
    private List<BeanPartido> consPar;
    BeanUsuariosLogin usuario;

    public String respuesta = "";

    public String elininar(int id) {
        DaoPartido dapartido = new DaoPartido();
        if (dapartido.Eliminar(id)) {
            respuesta = "index.xhtml";
        } else {
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }

    public String registrarPart() {
        DaoPartido DPar = new DaoPartido();
        if (DPar.registrarPartido(this.BPar)) {
            respuesta = "/index.xhtml";
        } else {
            respuesta = "/Error.xhtml";
        }
        return respuesta;
    }

    public String modificar() {
        DaoPartido DPar = new DaoPartido();
        System.out.println(BPar.getIdPartido());
        if (DPar.modificar(this.BPar)) {
            respuesta = "RegistrarPartido.xhtml";
        } else {
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }

    public List<SelectItem> consultarPartido() {
        DaoEquipo Dpar = new DaoEquipo();
        List<BeanEquipo> listar;
        List<SelectItem> listarpa = new ArrayList();
        listar = Dpar.listarEquipo();
        listar.stream().forEach((s) -> {
            listarpa.add(new SelectItem(s.getIdEquipo(), s.getNombreEquipo()));
        });
        return listarpa;
    }
    public List<SelectItem> consulCamp() {
        DaoCampeonato Dpar = new DaoCampeonato();
        List<BeanCampeonato> listar;
        List<SelectItem> listarpa = new ArrayList();
        listar = Dpar.listarCampeonato();
        listar.stream().forEach((s) -> {
            listarpa.add(new SelectItem(s.getIdCampeonato(), s.getNombre()));
        });
        return listarpa;
    }

    public void consPart(int id) {
        DaoPartido DPar = new DaoPartido();
        List<BeanPartido> listar;
        listar = DPar.consultar(id);
        for (BeanPartido Bpar : listar) {
            BPar = Bpar;
        }
    }
    /*  
     public void consultar(int id){
     DaoPartido daopartido = new DaoPartido();
     List<BeanPartido> listar;
     listar = daopartido.listarpartido();
     for(BeanPartido Bpar : listar){
     BPar = Bpar;
     }
     }*/

    public ControladorPartido() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        try {
            DaoPartido Dpar = new DaoPartido();
            consPar = Dpar.listarpartido();

        } catch (Exception e) {
        }

    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public BeanPartido getBpar() {
        return BPar;
    }

    public void setBpar(BeanPartido Bpar) {
        this.BPar = Bpar;
    }

    public List<BeanPartido> getConsPar() {
        return consPar;
    }

    public void setConsPar(List<BeanPartido> consPar) {
        this.consPar = consPar;
    }

}
