/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.ExpLaboralDao;
import com.udea.fnsp.buho.interfaces.ExperienciaPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreaexperiencia;
import com.udea.fnsp.buho.modelo.MaDedicacion;
import com.udea.fnsp.buho.modelo.MaNaturalezainst;
import com.udea.fnsp.buho.modelo.PeExplaboral;
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
public class ExpLaboralBean implements Serializable {

    private List<PeExplaboral> peExplaboral;
    private List<SelectItem> selectOneItemsArea;
    private List<SelectItem> selectOneItemsNatuInstitucion;
    private List<SelectItem> selectOneItemsDedicacion;
    private PeExplaboral explaboralSeleccionado = new PeExplaboral();
    Rutas rutas = new Rutas();
    private UploadedFile certificadoArchivo;
    ExperienciaPersonaDaoInterface expPerDao = new ExpLaboralDao();
    LoginBean loginMbean;
    private final String rutaCertificado = rutas.getRutaCertificadosExpLaboral();
    String NombreCertificado;

    public ExpLaboralBean() {
        this.peExplaboral = new ArrayList<PeExplaboral>();
        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);
    }

    public List<PeExplaboral> getPeExplaboral() {
        if (loginMbean.getUsuario() != null) {
            this.peExplaboral = expPerDao.findAll(loginMbean.getUsuario().getPePersona().getPeNumeroid());
        }
        return peExplaboral;
    }

    public List<SelectItem> getSelectOneItemsArea() {
        this.selectOneItemsArea = new ArrayList<SelectItem>();
        List<MaAreaexperiencia> tipoList = expPerDao.selectAreaexperiencia();
        for (MaAreaexperiencia areaexp : tipoList) {
            SelectItem selectItem = new SelectItem(areaexp.getAeCod(), areaexp.getAeNombre());
            this.selectOneItemsArea.add(selectItem);
        }
        return selectOneItemsArea;
    }

    public List<SelectItem> getSelectOneItemsNatuInstitucion() {
        this.selectOneItemsNatuInstitucion = new ArrayList<SelectItem>();
        List<MaNaturalezainst> tipoList = expPerDao.selectNaturalezainst();
        for (MaNaturalezainst natur : tipoList) {
            SelectItem selectItem = new SelectItem(natur.getNiCod(), natur.getNiNombre());
            this.selectOneItemsNatuInstitucion.add(selectItem);
        }
        return selectOneItemsNatuInstitucion;
    }

    public List<SelectItem> getSelectOneItemsDedicacion() {
        this.selectOneItemsDedicacion = new ArrayList<SelectItem>();
        List<MaDedicacion> tipoList = expPerDao.selectDedicacion();
        for (MaDedicacion dedica : tipoList) {
            SelectItem selectItem = new SelectItem(dedica.getDdCod(), dedica.getDdNombre());
            this.selectOneItemsDedicacion.add(selectItem);
        }

        return selectOneItemsDedicacion;
    }

    public PeExplaboral getExplaboralSeleccionado() {
        return explaboralSeleccionado;
    }

    public void setExplaboralSeleccionado(PeExplaboral explaboralSeleccionado) {
        this.explaboralSeleccionado = explaboralSeleccionado;
    }

    public UploadedFile getCertificadoArchivo() {
        return certificadoArchivo;
    }

    public void setCertificadoArchivo(UploadedFile certificadoArchivo) {
        this.certificadoArchivo = certificadoArchivo;
    }

    public void btnCreateExperiencia(ActionEvent actionEvent) throws IOException {
        String msg;
        if (ValidarExperiencia()) {
            this.explaboralSeleccionado.setPePersona(this.loginMbean.getUsuario().getPePersona());
            this.explaboralSeleccionado.setEpFecharegistro(new Date());
            if (this.expPerDao.create(explaboralSeleccionado)) {
                msg = "Se agregó correctamente la experiencia";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogInsertarExperiencia').hide()");
            } else {
                msg = "Error al agregar la experiencia";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void btnUpdateEstudio(ActionEvent actionEvent) {
        String msg;
        if (ValidarExperiencia()) {
            this.explaboralSeleccionado.setEpFecharegistro(new Date());
            if (this.expPerDao.update(this.explaboralSeleccionado)) {
                msg = "Se modificó correctamente el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogActualizarExperiencia').hide()");
            } else {
                msg = "Error al modificar el estudio";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void btnDeleteExperiencia(ActionEvent actionEvent) throws IOException {
        String msg;
        String rutCertificado = this.explaboralSeleccionado.getEpCertificado();
        if (this.expPerDao.delete(this.explaboralSeleccionado.getEpCod())) {
            if (rutCertificado == null && "".equals(rutCertificado)) {
                File certificadoBorrar = new File(rutCertificado);
                certificadoBorrar.delete();
            }
            msg = "Se eliminó correctamente la experiencia";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            msg = "Error al eliminar la experiencia";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eliminación", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void upload() throws IOException {
        NombreCertificado = this.explaboralSeleccionado.getPePersona().getPeNumeroid()
                + this.explaboralSeleccionado.getEpCargo() + this.explaboralSeleccionado.getEpCod() + ".pdf";
        NombreCertificado = NombreCertificado.replace(" ", "");
        NombreCertificado = rutas.quitarCaracteresEspeciales(NombreCertificado);
        String tipo = certificadoArchivo.getContentType();
        if ("application/pdf".equals(tipo)) {
            try {
                if (copyFile(rutaCertificado + NombreCertificado, certificadoArchivo.getInputstream())) {
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
            OutputStream out = new FileOutputStream(new File(fileName));
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
        this.explaboralSeleccionado.setEpCertificado(rutaCertificado + NombreCertificado);
        this.explaboralSeleccionado.setEpUrl(this.rutas.getUrlCertificadosExpLaboral()+NombreCertificado);
        this.explaboralSeleccionado.setEpArchivo("Archivo");
        this.explaboralSeleccionado.setEpFecharegistro(new Date());
        String msg;
        if (this.expPerDao.update(this.explaboralSeleccionado)) {
            msg = "Se modificó correctamente la experiencia";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar la experiencia";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    private boolean ValidarExperiencia() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;

        if (this.explaboralSeleccionado.getMaAreaexperiencia().getAeCod() == 0) {
            Mensaje = "Seleccione el area de experiencia";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if (this.explaboralSeleccionado.getMaNaturalezainst().getNiCod() == 0) {
            Mensaje = "Seleccione la naturaleza de la institución";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.explaboralSeleccionado.getEpCargo())) {
            Mensaje = "Ingrese el nombre del cargo";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.explaboralSeleccionado.getEpInstitucion())) {
            Mensaje = "Ingrese el nombre de la institución donde laboró";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if (this.explaboralSeleccionado.getEpFechaingreso() == null) {
            Mensaje = "Ingrese la fecha de Ingreso a la institución";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if (this.explaboralSeleccionado.getEpFechaingreso() == null) {
            Mensaje = "Ingrese la fecha de Ingreso a la institución";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.explaboralSeleccionado.getEpActual())) {
            Mensaje = "Seleccione si este es su trabajo actual";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if (this.explaboralSeleccionado.getMaDedicacion().getDdCod() == 0) {
            Mensaje = "Seleccione la dedicacion de tiempo del trabajo";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        return flag;
    }

}
