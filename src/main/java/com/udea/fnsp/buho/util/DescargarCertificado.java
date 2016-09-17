/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class DescargarCertificado implements Serializable {

    private StreamedContent descargaCertificado;

    FacesMessage message;
    String Mensaje;

    public DescargarCertificado() {
    }

    public StreamedContent getDesCargarCertificado() throws Exception {
        return descargaCertificado;
    }

    public void setDescargarCertificado(StreamedContent file) {
        this.descargaCertificado = file;
    }

    public void prepDownload(String ruta) throws Exception {
        if ("".equals(ruta) || ruta == null) {
            Mensaje = "No se ha cargado certificado";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga", Mensaje);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            File file = new File(ruta);
            InputStream input = new FileInputStream(file);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            setDescargarCertificado(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
        }

    }

}
