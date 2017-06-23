/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCategoria;
import Modelo.Bean.BeanEps;
import Modelo.Bean.BeanGenero;
import Modelo.Bean.BeanPreinscripcion;
import Modelo.Bean.BeanRH;
import Modelo.Dao.DaoCategoria;
import Modelo.Dao.DaoGenero;
import Modelo.Dao.DaoPreinscripcion;
import Modelo.Dao.DaoRh;
import Modelo.Dao.DaoTipoDoc;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class ControladorPreinscripcion {

    //hola mundo
    private BeanPreinscripcion preinscripcion = new BeanPreinscripcion();
    private BeanGenero genero = new BeanGenero();
    private List<BeanPreinscripcion> consPreins;
    public String Respuesta;
    
   
 
    public String preinscribir(){
        DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
        if (DPreinscripcion.preinscripcion(this.preinscripcion)) {
            Respuesta = "index.xhtml";
        }else{
            Respuesta = "Error.xhtml";
        }
        return Respuesta;
    }
    
    public void consultarEquiposPreins(int id){
        DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
        consPreins = DPreinscripcion.listar(id);
    }
   
    public BeanGenero getGenero() {
        return genero;
    }

    public void setGenero(BeanGenero genero) {
        this.genero = genero;
    }

    public List<BeanPreinscripcion> getConsPreins() {
        return consPreins;
    }

    public void setConsPreins(List<BeanPreinscripcion> consPreins) {
        this.consPreins = consPreins;
    }
    
    public BeanPreinscripcion getPreinscripcion() {
        return preinscripcion;
    }

    public void setPreinscripcion(BeanPreinscripcion preinscripcion) {
        this.preinscripcion = preinscripcion;
    }

    public ControladorPreinscripcion() {
        
    }
    
}
