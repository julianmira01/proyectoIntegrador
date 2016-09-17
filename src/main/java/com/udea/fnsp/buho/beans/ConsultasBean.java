/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.ConsultasDao;
import com.udea.fnsp.buho.interfaces.ConsultasDaoInterface;
import com.udea.fnsp.buho.modelo.PeEstudios;
import com.udea.fnsp.buho.util.ConexionBD;
import com.udea.fnsp.buho.util.Rutas;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Sebastian Restrepo
 */
@ManagedBean
@SessionScoped
public class ConsultasBean implements Serializable {

    ConsultasDaoInterface consultasDao;
    Rutas rutas;

    public ConsultasBean() {
    }

    private List<PeEstudios> personas;
    private String Busqueda;
    private String CriteriodeBusqueda;

    public String getBusqueda() {
        return Busqueda;
    }

    public void setBusqueda(String Busqueda) {
        this.Busqueda = Busqueda;
    }

    public String getCriteriodeBusqueda() {
        return CriteriodeBusqueda;
    }

    public void setCriteriodeBusqueda(String CriteriodeBusqueda) {
        this.CriteriodeBusqueda = CriteriodeBusqueda;
    }

    public List<PeEstudios> getPersonas() {
        return personas;
    }

    public void setPersonas(List<PeEstudios> personas) {
        this.personas = personas;
    }

    public void buscarPersonas() {
        try {
            consultasDao = new ConsultasDao();
            this.personas = consultasDao.personasConsulta(this.CriteriodeBusqueda,this.Busqueda);

        } catch (Exception ex) {
        }

    }

    public void verPDF(String Identificacion, String rutafoto) throws Exception {
        //ConexionBD con = new ConexionBD("usersiifnsp", "M55E23XoYoXI3InivOM3LI6oMer23o", "bdsiifnsp", "localhost");
        ConexionBD con = new ConexionBD("root", "", "bdsiifnsp", "localhost");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        rutas = new Rutas();
        String rutaFoto = rutafoto;
        FileInputStream foto;
        FileInputStream fotoLogo;
        FileInputStream fotoSilueta;
        fotoLogo = new FileInputStream(rutas.getPathAplicacion() + "\\resources\\img\\escudo.png");
        if ("".equals(rutaFoto) || rutaFoto == null) {
            fotoSilueta = new FileInputStream(rutas.getRutaFotoHojaDeVida() + "silueta.jpg");
            parametros.put("foto", fotoSilueta);

        } else {
            foto = new FileInputStream(rutaFoto);
            parametros.put("foto", foto);
        }

        parametros.put("fotoLogo", fotoLogo);
        parametros.put("Identificacion", Identificacion);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\reportes\\hojaDeVida.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, con.getConexion());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=hojaDeVida.pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
}
