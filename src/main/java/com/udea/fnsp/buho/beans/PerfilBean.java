/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.PerfilDao;
import com.udea.fnsp.buho.interfaces.PerfilDaoInterface;
import com.udea.fnsp.buho.modelo.MaPerfil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class PerfilBean implements Serializable{

    private MaPerfil perfil = new MaPerfil();
    private List<MaPerfil> perfiles;

    public PerfilBean() {
    }

    public void prepararPerfil(Integer id) {
        PerfilDaoInterface perfilDao = new PerfilDao();
        perfil = perfilDao.buscarPorId(id);
    }

    public void actualizarPerfil() {
        // Condiciones de Menu Principal

        //Menu Administrar
        if (perfil.isPfMenuAdministrarPerfil() == true || perfil.isPfMenuAdministrarUsuario() == true) {
            perfil.setPfMenuAdministrar(true);
        } else if (perfil.isPfMenuAdministrarPerfil() == false && perfil.isPfMenuAdministrarUsuario() == false) {
            perfil.setPfMenuAdministrar(false);
        }

        //Menu Consultas
        if (perfil.isPfMenuConsultasConsu() == true) {
            perfil.setPfMenuConsultas(true);
        } else if (perfil.isPfMenuConsultasConsu() == false) {
            perfil.setPfMenuConsultas(false);
        }

        //Menu Convocatorias
        if (perfil.isPfMenuConvocatoriasVigentes() == true) {
            perfil.setPfMenuConvocatorias(true);
        } else if (perfil.isPfMenuConvocatoriasVigentes() == false) {
            perfil.setPfMenuConvocatorias(false);
        }

        //Menu Perfil
        if (perfil.isPfMenuPerfilContrasena() == true) {
            perfil.setPfMenuPerfil(true);
        } else if (perfil.isPfMenuPerfilContrasena() == false) {
            perfil.setPfMenuPerfil(false);
        }
        
         //Menu Visualizar
        if (perfil.isPfMenuVisualizarHoja() == true) {
            perfil.setPfMenuVisualizar(true);
        } else if (perfil.isPfMenuVisualizarHoja() == false) {
            perfil.setPfMenuVisualizar(false);
        }
        
        //Menu Persona
        if (perfil.isPfMenuPersonaAcad() == true || perfil.isPfMenuPersonaInfo() == true || perfil.isPfMenuPersonaLaboral() == true) {
            perfil.setPfMenuPersona(true);
        } else if (perfil.isPfMenuPersonaAcad() == false && perfil.isPfMenuPersonaInfo() == false && perfil.isPfMenuPersonaLaboral() == false) {
            perfil.setPfMenuPersona(false);
        }        

        PerfilDaoInterface perfilDao = new PerfilDao();
        perfilDao.actualizar(perfil);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Datos actualizado correctamente"));
        perfil = new MaPerfil();
    }

    public MaPerfil getPerfil() {
        if (perfil == null) {
            perfil = new MaPerfil();
        }
        return perfil;
    }

    public void setPerfil(MaPerfil perfil) {
        this.perfil = perfil;
    }

    public List<MaPerfil> getPerfiles() {
        PerfilDaoInterface perfilDao = new PerfilDao();
        perfiles = perfilDao.buscarTodos();
        return perfiles;
    }

}
