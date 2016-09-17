/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.IdiomasDao;
import com.udea.fnsp.buho.interfaces.IdiomasPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaIdiomas;
import com.udea.fnsp.buho.modelo.PeIdiomas;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class IdiomasBean implements Serializable {

    private List<PeIdiomas> peIdiomas;
    private List<SelectItem> selectOneItemsIdiomas;    
    private PeIdiomas idiomaSeleccionado = new PeIdiomas();
    IdiomasPersonaDaoInterface idioPersonaDao = new IdiomasDao();
    LoginBean loginMbean;

    /**
     * Creates a new instance of IdiomasBean
     */
    public IdiomasBean() {
        this.peIdiomas = new ArrayList<PeIdiomas>();
        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);;
    }

    public PeIdiomas getIdiomaSeleccionado() {
        return idiomaSeleccionado;
    }

    public void setIdiomaSeleccionado(PeIdiomas idiomaSeleccionado) {
        this.idiomaSeleccionado = idiomaSeleccionado;
    }

      

    public List<PeIdiomas> getPeIdiomas() {
        if (loginMbean.getUsuario() != null) {
            this.peIdiomas = idioPersonaDao.findAll(loginMbean.getUsuario().getPePersona().getPeNumeroid());
        }
        return peIdiomas;
    }

    public List<SelectItem> getSelectOneItemsIdiomas() {
        this.selectOneItemsIdiomas = new ArrayList<SelectItem>();
        IdiomasPersonaDaoInterface idiomasDao = new IdiomasDao();
        List<MaIdiomas> tipoList = idiomasDao.selectIdiomas();
        for (MaIdiomas idiomas : tipoList) {
            SelectItem selectItem = new SelectItem(idiomas.getIdCod(), idiomas.getIdNombre());
            this.selectOneItemsIdiomas.add(selectItem);
        }
        return selectOneItemsIdiomas;
    }

    public void btnCreateIdioma(ActionEvent actionEvent) {
        String msg;
        if (ValidarIdiomas()) {
            this.idiomaSeleccionado.setPePersona(this.loginMbean.getUsuario().getPePersona());
            if (this.idioPersonaDao.create(idiomaSeleccionado)) {
                msg = "Se agregó correctamente el idioma";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogInsertarIdioma').hide()");
            } else {
                msg = "Error al agregar idioma";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void btnDeleteIdioma(ActionEvent actionEvent) {
        String msg;
        if (this.idioPersonaDao.delete(this.idiomaSeleccionado.getPidCod())) {
            msg = "Se eliminó correctamente el idioma";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el idioma";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    private boolean ValidarIdiomas() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;

        if ("".equals(this.idiomaSeleccionado.getPidEscritura())) {
            Mensaje = "Ingrese una Calificación para Escritura";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if (this.idiomaSeleccionado.getMaIdiomas().getIdCod() == 0) {
            Mensaje = "Seleccione un idioma";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.idiomaSeleccionado.getPidHabla())) {
            Mensaje = "Ingrese una Calificación para Habla";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.idiomaSeleccionado.getPidLectura())) {
            Mensaje = "Ingrese una Calificación para Lectura";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        return flag;
    }

}
