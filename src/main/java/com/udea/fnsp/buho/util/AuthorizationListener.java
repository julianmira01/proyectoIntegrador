/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
       FacesContext facesContext = event.getFacesContext();
       String currentPage = facesContext.getViewRoot().getViewId();
       boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object usuario = sesion.getAttribute("usuario");
        
        if(!isLoginPage && usuario == null)
        {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "index.xhtml");
        
        }
       
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    @Override
    public PhaseId getPhaseId() {
       return PhaseId.RESTORE_VIEW;
    }
    
}
