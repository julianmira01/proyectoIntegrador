/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.EstudiosDao;
import com.udea.fnsp.buho.interfaces.EstudiosPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreaestudio;
import com.udea.fnsp.buho.modelo.MaTipoestudio;
import com.udea.fnsp.buho.modelo.PeEstudios;
import com.udea.fnsp.buho.util.Rutas;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class EstudiosBean implements Serializable {

    private List<PeEstudios> peEstudios;
    private List<SelectItem> selectOneItemsTipoEstudio;
    private List<SelectItem> selectOneItemsAreaEstudio;
    private PeEstudios estudioSeleccionado = new PeEstudios();
    private UploadedFile certificadoArchivo;
    EstudiosPersonaDaoInterface estudiosPersonaDao = new EstudiosDao();
    LoginBean loginMbean;
    Rutas rutas = new Rutas();
    private final String rutaCertificado = rutas.getRutaCertificadosEstudios();
    String NombreCertificado;

    @SuppressWarnings("empty-statement")
    public EstudiosBean() {
        this.peEstudios = new ArrayList<PeEstudios>();
        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);

    }

    public List<PeEstudios> getPeEstudios() {
        if (loginMbean.getUsuario() != null) {
            this.peEstudios = estudiosPersonaDao.findAll(loginMbean.getUsuario().getPePersona().getPeNumeroid());
        }
        return peEstudios;

    }

    public List<SelectItem> getSelectOneItemsTipoEstudio() {
        this.selectOneItemsTipoEstudio = new ArrayList<SelectItem>();
        List<MaTipoestudio> tipoList = estudiosPersonaDao.selectTipoestudio();
        for (MaTipoestudio estudios : tipoList) {
            SelectItem selectItem = new SelectItem(estudios.getTpCod(), estudios.getTpNombre());
            this.selectOneItemsTipoEstudio.add(selectItem);
        }
        return selectOneItemsTipoEstudio;
    }

    public List<SelectItem> getSelectOneItemsAreaEstudio() {
        this.selectOneItemsAreaEstudio = new ArrayList<SelectItem>();
        List<MaAreaestudio> tipoList = estudiosPersonaDao.selectMaareaestudio();
        for (MaAreaestudio areaestudios : tipoList) {
            SelectItem selectItem = new SelectItem(areaestudios.getAeCod(), areaestudios.getAeNombre());
            this.selectOneItemsAreaEstudio.add(selectItem);
        }
        return selectOneItemsAreaEstudio;
    }

    public PeEstudios getEstudioSeleccionado() {
        return estudioSeleccionado;
    }

    public void setEstudioSeleccionado(PeEstudios estudioSeleccionado) {
        this.estudioSeleccionado = estudioSeleccionado;
    }

    public UploadedFile getCertificadoArchivo() {
        return certificadoArchivo;
    }

    public void setCertificadoArchivo(UploadedFile certificadoArchivo) {
        this.certificadoArchivo = certificadoArchivo;
    }

    public void btnCreateEstudio(ActionEvent actionEvent) throws IOException {
        String msg;
        if (ValidarEstudio()) {
            this.estudioSeleccionado.setPePersona(this.loginMbean.getUsuario().getPePersona());
            this.estudioSeleccionado.setEstUpdate(new Date());
            if (this.estudiosPersonaDao.create(estudioSeleccionado)) {
                msg = "Se agregó correctamente el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogInsertarEstudio').hide()");
                this.estudioSeleccionado = new PeEstudios();
            } else {
                msg = "Error al agregar el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.estudioSeleccionado = new PeEstudios();
            }
        }

    }

    public void btnUpdateEstudio(ActionEvent actionEvent) {
        String msg;
        if (ValidarEstudio()) {
            this.estudioSeleccionado.setEstUpdate(new Date());
            if (this.estudiosPersonaDao.update(this.estudioSeleccionado)) {
                msg = "Se modificó correctamente el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogUpdateEstudio').hide()");
                this.estudioSeleccionado = new PeEstudios();
            } else {
                msg = "Error al modificar el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.estudioSeleccionado = new PeEstudios();
            }
        }
    }

    public void btnDeleteEstudio(ActionEvent actionEvent) throws IOException {
        String msg;
        String rutCertificado = this.estudioSeleccionado.getEstCertificado();
        if (this.estudiosPersonaDao.delete(this.estudioSeleccionado.getEstCod())) {
            if (rutCertificado != null && !"".equals(rutCertificado)) {
                File certificadoBorrar = new File(rutCertificado);
                certificadoBorrar.delete();
            }
            msg = "Se eliminó correctamente el estudio";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.estudioSeleccionado = new PeEstudios();

        } else {
            msg = "Error al eliminar el estudio";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.estudioSeleccionado = new PeEstudios();
        }

    }

    public void upload() throws IOException {
        NombreCertificado = this.estudioSeleccionado.getPePersona().getPeNumeroid()
                + this.estudioSeleccionado.getMaTipoestudio().getTpNombre()
                + this.estudioSeleccionado.getEstCod() + ".pdf";
        NombreCertificado = rutas.quitarCaracteresEspeciales(NombreCertificado);
        String tipo = certificadoArchivo.getContentType();
        if ("application/pdf".equals(tipo)) {
            try {
                if (copyFile(NombreCertificado, certificadoArchivo.getInputstream())) {
                    FacesMessage msg = new FacesMessage("Certificado cargado correctamente");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    String msg = "Error al cargar certificado";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga", msg);
                    FacesContext.getCurrentInstance().addMessage(null, message);

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {
            String msg = "El formato debe de ser en pdf";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    private boolean copyFile(String fileName, InputStream in) {
        boolean flag;
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(rutaCertificado + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            updateRutaCertificadoBaseDatos();
            System.out.println("New file created!");
            flag = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            flag = false;
        }
        return flag;
    }

    private void updateRutaCertificadoBaseDatos() {
        
        this.estudioSeleccionado.setEstCertificado(rutaCertificado + NombreCertificado);
        this.estudioSeleccionado.setEstUrl(this.rutas.getUrlCertificadosEstudios() + NombreCertificado);
        this.estudioSeleccionado.setEstArchivo("Archivo");
        this.estudioSeleccionado.setEstUpdate(new Date());
        String msg;
        if (this.estudiosPersonaDao.update(this.estudioSeleccionado)) {
            msg = "Se modificó correctamente el estudio";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.estudioSeleccionado = new PeEstudios();
        } else {
            msg = "Error al modificar el estudio";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.estudioSeleccionado = new PeEstudios();
        }

    }

    private boolean ValidarEstudio() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;

        if (this.estudioSeleccionado.getMaAreaestudio().getAeCod() == 0) {
            Mensaje = "Seleccione el area a la que pertenece el estudio";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if (this.estudioSeleccionado.getMaTipoestudio().getTpCod() == 0) {
            Mensaje = "Ingrese el Tipo al que pertenece el estudio";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.estudioSeleccionado.getEstNombreestudio())) {
            Mensaje = "Ingrese el nombre del estudio";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.estudioSeleccionado.getEstInstitucion())) {
            Mensaje = "Ingrese la institución en la que desarrollo el estudio";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.estudioSeleccionado.getEstEstado())) {
            Mensaje = "Ingrese el estado en la que se encuentre el estudio";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        return flag;
    }

}
