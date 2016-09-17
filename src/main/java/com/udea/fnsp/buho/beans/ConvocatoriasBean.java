/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.ConvocatoriasDao;
import com.udea.fnsp.buho.interfaces.ConvocatoriasDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreasinteres;
import com.udea.fnsp.buho.modelo.PeEstudios;
import com.udea.fnsp.buho.modelo.PeExplaboral;
import com.udea.fnsp.buho.modelo.PeInteresespersona;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class ConvocatoriasBean implements Serializable {

    private List<MaAreasinteres> maAreasintereses;
    private List<PeInteresespersona> peInteresespersona;
    private MaAreasinteres convoSeleccionada;
    private PeInteresespersona interesPersona;
    ConvocatoriasDaoInterface convoDao = new ConvocatoriasDao();
    LoginBean loginMbean;
    EstudiosBean estudiosBean;
    ExpLaboralBean expBean;

    public ConvocatoriasBean() {
        this.maAreasintereses = new ArrayList<MaAreasinteres>();
        this.peInteresespersona = new ArrayList<PeInteresespersona>();
        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);
        estudiosBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{estudiosBean}", EstudiosBean.class);
        expBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{expLaboralBean}", ExpLaboralBean.class);
        convoSeleccionada = new MaAreasinteres();
        interesPersona = new PeInteresespersona();
    }

    public PeInteresespersona getInteresPersona() {
        return interesPersona;
    }

    public void setInteresPersona(PeInteresespersona interesPersona) {
        this.interesPersona = interesPersona;
    }

    public MaAreasinteres getConvoSeleccionada() {
        return convoSeleccionada;
    }

    public void setConvoSeleccionada(MaAreasinteres convoSeleccionada) {
        this.convoSeleccionada = convoSeleccionada;
    }

    public List<MaAreasinteres> getMaAreasintereses() {
        this.maAreasintereses = convoDao.findAllMaareasintereses();
        return maAreasintereses;
    }

    public void setMaAreasintereses(List<MaAreasinteres> maAreasintereses) {
        this.maAreasintereses = maAreasintereses;
    }

    public List<PeInteresespersona> getPeInteresespersona() {
        if (loginMbean.getUsuario() != null) {
            this.peInteresespersona = convoDao.findAllPeintereses(loginMbean.getUsuario().getPePersona().getPeNumeroid());
        }
        return peInteresespersona;

    }

    public void setPeInteresespersona(List<PeInteresespersona> peInteresespersona) {
        this.peInteresespersona = peInteresespersona;
    }

    public void btnCreateConvocatoria(ActionEvent actionEvent) {
        if (validarCertificados()) {
            String msg;
            this.interesPersona.setIpFecharegistro(new Date());
            this.interesPersona.setPePersona(loginMbean.getUsuario().getPePersona());
            this.interesPersona.setMaAreasinteres(convoSeleccionada);
            if (this.convoDao.create(interesPersona)) {
                msg = "Se agregó correctamente la convocatoria";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogAplicarConvocatoria').hide()");
            } else {
                msg = "Error al agregar la convocatoria";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void btnDeleteConvocatoria(ActionEvent actionEvent) {
        String msg;
        if (this.convoDao.delete(this.interesPersona.getIpCod())) {
            msg = "Se eliminó correctamente la convocatoria";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar la convocatoria";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    private boolean validarCertificados() {
        boolean flag = true;
        Iterator iteradorExperiencia = expBean.getPeExplaboral().listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
        Iterator iteradorEstudios = estudiosBean.getPeEstudios().listIterator();

        if (expBean.getPeExplaboral().isEmpty()) {
            String msg = "No tiene ninguna experiencia laboral registrada para aplicar a la convocatoria";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Experiencia Laboral", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        } else {
            while (iteradorExperiencia.hasNext()) {
                PeExplaboral b = (PeExplaboral) iteradorExperiencia.next();
                if (b.getEpCertificado() == null || "".equals(b.getEpCertificado())) {
                    String msg = "Por favor cargue un documento que certfique la experiencia laboral " + b.getEpCargo() + " en " + b.getEpInstitucion();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Experiencia Laboral", msg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    flag = false;
                }
            }
        }
        
        if (estudiosBean.getPeEstudios().isEmpty()) {
            String msg = "No tiene ningun estudio registrado para aplicar a la convocatoria";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudios", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            flag = false;
        } else {
            while (iteradorEstudios.hasNext()) {
                PeEstudios b = (PeEstudios) iteradorEstudios.next();
                if (b.getEstCertificado() == null || "".equals(b.getEstCertificado())) {
                    String msg = "Por favor cargue un documento que certfique el estudio " + b.getEstNombreestudio() + " en " + b.getEstInstitucion();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudios", msg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    flag = false;
                }
            }
        }

        return flag;
    }

}
