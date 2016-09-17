/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.util;

import java.io.File;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
public class Rutas implements Serializable {

    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    String RutaCertificadosExpLaboral;
    String RutaCertificadosEstudios;
    String RutaFotoHojaDeVida;
    String PathAplicacion;
    String UrlCertificadosEstudios;
    String UrlCertificadosExpLaboral;

    public Rutas() {
    }

    public String getRutaCertificadosExpLaboral() {
        RutaCertificadosExpLaboral = servletContext.getRealPath("") + File.separatorChar
                + "resources" + File.separatorChar + "CertificadosExperienciaLaboral" + File.separatorChar;
        return RutaCertificadosExpLaboral;
    }

    public String getRutaCertificadosEstudios() {
        RutaCertificadosEstudios = servletContext.getRealPath("") + File.separatorChar
                + "resources" + File.separatorChar + "CertificadosEstudios" + File.separatorChar;
        return RutaCertificadosEstudios;
    }

    public String getRutaFotoHojaDeVida() {
        RutaFotoHojaDeVida = servletContext.getRealPath("") + File.separatorChar
                + "resources" + File.separatorChar + "FotosHojaDeVida" + File.separatorChar;
        return RutaFotoHojaDeVida;
    }

    public String getPathAplicacion() {
        PathAplicacion = servletContext.getRealPath("");
        return PathAplicacion;
    }

    public String getUrlCertificadosEstudios() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UrlCertificadosEstudios =((HttpServletRequest) request).getScheme()+"://" + ((HttpServletRequest) request).getServerName() + ":"
                + ((HttpServletRequest) request).getServerPort() + ((HttpServletRequest) request).getContextPath() + "/resources/CertificadosEstudios/";
        return UrlCertificadosEstudios;
    }

    public String getUrlCertificadosExpLaboral() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UrlCertificadosExpLaboral =((HttpServletRequest) request).getScheme()+"://" + ((HttpServletRequest) request).getServerName() + ":"
                + ((HttpServletRequest) request).getServerPort() + ((HttpServletRequest) request).getContextPath() + "/resources/CertificadosExperienciaLaboral/";
        return UrlCertificadosExpLaboral;
    }
    
    

    public String quitarCaracteresEspeciales(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }//remove1

}
