/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.PersonaDao;
import com.udea.fnsp.buho.dao.UsuarioDao;
import com.udea.fnsp.buho.interfaces.PersonaDaoInterface;
import com.udea.fnsp.buho.interfaces.UsuarioDaoInterface;
import com.udea.fnsp.buho.modelo.MaUsuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private MaUsuario usuario;
    private UsuarioDaoInterface usuarioDao;
    private PersonaDaoInterface personaDao;
    PersonaBean personaBean;

    public LoginBean() {
        this.usuarioDao = new UsuarioDao();
        this.personaDao = new PersonaDao();
        this.usuario = new MaUsuario();
    }

    public MaUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MaUsuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;

        boolean loggedIn;
        String ruta = "";        

        this.usuario = this.usuarioDao.login(this.usuario);
        if (this.usuario != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getPePersona().getPeNombres().concat(" " + this.usuario.getPePersona().getPeApellidos()));
            personaBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{personaBean}", PersonaBean.class);
            personaBean.setPersonaSeleccionada(this.personaDao.findByPersona(usuario.getPePersona().getPeNumeroid()));
            ruta = "inicio.xhtml";

        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingreso", "Usuario y/o Clave es incorrecto.");
            if (this.usuario == null) {
                this.usuario = new MaUsuario();

            }
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);

    }

    public void logout() {

        String ruta = "index.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext faceContext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }

    
}
