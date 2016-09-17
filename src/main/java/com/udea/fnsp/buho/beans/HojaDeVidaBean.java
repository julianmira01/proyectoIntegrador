/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.util.ConexionBD;
import com.udea.fnsp.buho.util.Rutas;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class HojaDeVidaBean {

    /**
     * Creates a new instance of HojaDeVidaBean
     */
    LoginBean loginMbean;
    Rutas rutas = new Rutas();

    public HojaDeVidaBean() {
        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(),
                        "#{loginBean}", LoginBean.class);
    }

    public void verPDF() throws Exception {
        ConexionBD con = new ConexionBD("usersiifnsp", "M55E23XoYoXI3InivOM3LI6oMer23o", "bdsiifnsp", "localhost");
        //ConexionBD con = new ConexionBD("root", "", "bdsiifnsp", "localhost");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        String rutaFoto = this.loginMbean.getUsuario().getPePersona().getPeFoto();
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
        parametros.put("Identificacion", loginMbean.getUsuario().getPePersona().getPeNumeroid());
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

    public void printReport() throws ClassNotFoundException, IOException, JRException {
        ConexionBD con = new ConexionBD("usersiifnsp", "M55E23XoYoXI3InivOM3LI6oMer23o", "bdsiifnsp", "localhost");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        String rutaFoto = this.loginMbean.getUsuario().getPePersona().getPeFoto();
        FileInputStream foto = null;
        FileInputStream fotoLogo;
        FileInputStream fotoSilueta = null;
        fotoLogo = new FileInputStream(rutas.getPathAplicacion() + "\\resources\\img\\escudo.png");
        if ("".equals(rutaFoto) || rutaFoto == null) {
            fotoSilueta = new FileInputStream(rutas.getRutaFotoHojaDeVida() + "silueta.jpg");
            parametros.put("foto", fotoSilueta);

        } else {
            foto = new FileInputStream(rutaFoto);
            parametros.put("foto", foto);
        }

        parametros.put("fotoLogo", fotoLogo);
        parametros.put("Identificacion", loginMbean.getUsuario().getPePersona().getPeNumeroid());

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        InputStream reportStream = ctx.getExternalContext().getResourceAsStream("/reportes/hojaDeVida.jasper");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.flush();

        response.setContentType("application/pdf");

        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, con.getConexion());

        servletOutputStream.flush();
        servletOutputStream.close();
        ctx.responseComplete();
    }

    public void descargarPDF(ActionEvent event) throws Exception {
        ConexionBD con = new ConexionBD("usersiifnsp", "M55E23XoYoXI3InivOM3LI6oMer23o", "bdsiifnsp", "localhost");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        String rutaFoto = this.loginMbean.getUsuario().getPePersona().getPeFoto();
        FileInputStream foto = null;
        FileInputStream fotoLogo;
        FileInputStream fotoSilueta = null;
        fotoLogo = new FileInputStream(rutas.getPathAplicacion() + "\\resources\\img\\escudo.png");
        if ("".equals(rutaFoto) || rutaFoto == null) {
            fotoSilueta = new FileInputStream(rutas.getRutaFotoHojaDeVida() + "silueta.jpg");
            parametros.put("foto", fotoSilueta);

        } else {
            foto = new FileInputStream(rutaFoto);
            parametros.put("foto", foto);
        }

        parametros.put("fotoLogo", fotoLogo);
        parametros.put("Identificacion", this.loginMbean.getUsuario().getPePersona().getPeNumeroid());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\reportes\\hojaDeVida.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con.getConexion());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=" + this.loginMbean.getUsuario().getPePersona().getPeNombres() + "hojaDeVida.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        fotoLogo.close();
        fotoSilueta.close();
        foto.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, JRException {
        ConexionBD con = new ConexionBD("usersiifnsp", "M55E23XoYoXI3InivOM3LI6oMer23o", "bdsiifnsp", "localhost");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("Identificacion", loginMbean.getUsuario().getPePersona().getPeNumeroid());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\reportes\\hojaDeVida.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, con.getConexion());
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
