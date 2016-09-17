package com.udea.fnsp.buho.modelo;
// Generated 25/06/2014 12:01:44 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MaPerfil generated by hbm2java
 */
@Entity
@Table(name="ma_perfil"
    ,catalog="bdsiifnsp"
)
public class MaPerfil  implements java.io.Serializable {


     private int pfCod;
     private String pfNombre;
     private boolean pfMenuPersona;
     private boolean pfMenuPersonaInfo;
     private boolean pfMenuPersonaAcad;
     private boolean pfMenuPersonaLaboral;
     private boolean pfMenuConvocatorias;
     private boolean pfMenuConvocatoriasVigentes;
     private boolean pfMenuVisualizar;
     private boolean pfMenuVisualizarHoja;
     private boolean pfMenuConsultas;
     private boolean pfMenuConsultasConsu;
     private boolean pfMenuPerfil;
     private boolean pfMenuPerfilContrasena;
     private boolean pfMenuAdministrar;
     private boolean pfMenuAdministrarPerfil;
     private boolean pfMenuAdministrarUsuario;
     private Set maUsuarios = new HashSet(0);

    public MaPerfil() {
        this.pfCod = 2;
    }

	
    public MaPerfil(int pfCod, String pfNombre, boolean pfMenuPersona, boolean pfMenuPersonaInfo, boolean pfMenuPersonaAcad, boolean pfMenuPersonaLaboral, boolean pfMenuConvocatorias, boolean pfMenuConvocatoriasVigentes, boolean pfMenuVisualizar, boolean pfMenuVisualizarHoja, boolean pfMenuConsultas, boolean pfMenuConsultasConsu, boolean pfMenuPerfil, boolean pfMenuPerfilContrasena, boolean pfMenuAdministrar, boolean pfMenuAdministrarPerfil, boolean pfMenuAdministrarUsuario) {
        this.pfCod = pfCod;
        this.pfNombre = pfNombre;
        this.pfMenuPersona = pfMenuPersona;
        this.pfMenuPersonaInfo = pfMenuPersonaInfo;
        this.pfMenuPersonaAcad = pfMenuPersonaAcad;
        this.pfMenuPersonaLaboral = pfMenuPersonaLaboral;
        this.pfMenuConvocatorias = pfMenuConvocatorias;
        this.pfMenuConvocatoriasVigentes = pfMenuConvocatoriasVigentes;
        this.pfMenuVisualizar = pfMenuVisualizar;
        this.pfMenuVisualizarHoja = pfMenuVisualizarHoja;
        this.pfMenuConsultas = pfMenuConsultas;
        this.pfMenuConsultasConsu = pfMenuConsultasConsu;
        this.pfMenuPerfil = pfMenuPerfil;
        this.pfMenuPerfilContrasena = pfMenuPerfilContrasena;
        this.pfMenuAdministrar = pfMenuAdministrar;
        this.pfMenuAdministrarPerfil = pfMenuAdministrarPerfil;
        this.pfMenuAdministrarUsuario = pfMenuAdministrarUsuario;
    }
    public MaPerfil(int pfCod, String pfNombre, boolean pfMenuPersona, boolean pfMenuPersonaInfo, boolean pfMenuPersonaAcad, boolean pfMenuPersonaLaboral, boolean pfMenuConvocatorias, boolean pfMenuConvocatoriasVigentes, boolean pfMenuVisualizar, boolean pfMenuVisualizarHoja, boolean pfMenuConsultas, boolean pfMenuConsultasConsu, boolean pfMenuPerfil, boolean pfMenuPerfilContrasena, boolean pfMenuAdministrar, boolean pfMenuAdministrarPerfil, boolean pfMenuAdministrarUsuario, Set maUsuarios) {
       this.pfCod = pfCod;
       this.pfNombre = pfNombre;
       this.pfMenuPersona = pfMenuPersona;
       this.pfMenuPersonaInfo = pfMenuPersonaInfo;
       this.pfMenuPersonaAcad = pfMenuPersonaAcad;
       this.pfMenuPersonaLaboral = pfMenuPersonaLaboral;
       this.pfMenuConvocatorias = pfMenuConvocatorias;
       this.pfMenuConvocatoriasVigentes = pfMenuConvocatoriasVigentes;
       this.pfMenuVisualizar = pfMenuVisualizar;
       this.pfMenuVisualizarHoja = pfMenuVisualizarHoja;
       this.pfMenuConsultas = pfMenuConsultas;
       this.pfMenuConsultasConsu = pfMenuConsultasConsu;
       this.pfMenuPerfil = pfMenuPerfil;
       this.pfMenuPerfilContrasena = pfMenuPerfilContrasena;
       this.pfMenuAdministrar = pfMenuAdministrar;
       this.pfMenuAdministrarPerfil = pfMenuAdministrarPerfil;
       this.pfMenuAdministrarUsuario = pfMenuAdministrarUsuario;
       this.maUsuarios = maUsuarios;
    }
   
     @Id 

    
    @Column(name="pf_cod", unique=true, nullable=false)
    public int getPfCod() {
        return this.pfCod;
    }
    
    public void setPfCod(int pfCod) {
        this.pfCod = pfCod;
    }

    
    @Column(name="pf_nombre", nullable=false, length=50)
    public String getPfNombre() {
        return this.pfNombre;
    }
    
    public void setPfNombre(String pfNombre) {
        this.pfNombre = pfNombre;
    }

    
    @Column(name="pf_menu_persona", nullable=false)
    public boolean isPfMenuPersona() {
        return this.pfMenuPersona;
    }
    
    public void setPfMenuPersona(boolean pfMenuPersona) {
        this.pfMenuPersona = pfMenuPersona;
    }

    
    @Column(name="pf_menu_persona_Info", nullable=false)
    public boolean isPfMenuPersonaInfo() {
        return this.pfMenuPersonaInfo;
    }
    
    public void setPfMenuPersonaInfo(boolean pfMenuPersonaInfo) {
        this.pfMenuPersonaInfo = pfMenuPersonaInfo;
    }

    
    @Column(name="pf_menu_persona_acad", nullable=false)
    public boolean isPfMenuPersonaAcad() {
        return this.pfMenuPersonaAcad;
    }
    
    public void setPfMenuPersonaAcad(boolean pfMenuPersonaAcad) {
        this.pfMenuPersonaAcad = pfMenuPersonaAcad;
    }

    
    @Column(name="pf_menu_persona_laboral", nullable=false)
    public boolean isPfMenuPersonaLaboral() {
        return this.pfMenuPersonaLaboral;
    }
    
    public void setPfMenuPersonaLaboral(boolean pfMenuPersonaLaboral) {
        this.pfMenuPersonaLaboral = pfMenuPersonaLaboral;
    }

    
    @Column(name="pf_menu_convocatorias", nullable=false)
    public boolean isPfMenuConvocatorias() {
        return this.pfMenuConvocatorias;
    }
    
    public void setPfMenuConvocatorias(boolean pfMenuConvocatorias) {
        this.pfMenuConvocatorias = pfMenuConvocatorias;
    }

    
    @Column(name="pf_menu_convocatorias_vigentes", nullable=false)
    public boolean isPfMenuConvocatoriasVigentes() {
        return this.pfMenuConvocatoriasVigentes;
    }
    
    public void setPfMenuConvocatoriasVigentes(boolean pfMenuConvocatoriasVigentes) {
        this.pfMenuConvocatoriasVigentes = pfMenuConvocatoriasVigentes;
    }

    
    @Column(name="pf_menu_visualizar", nullable=false)
    public boolean isPfMenuVisualizar() {
        return this.pfMenuVisualizar;
    }
    
    public void setPfMenuVisualizar(boolean pfMenuVisualizar) {
        this.pfMenuVisualizar = pfMenuVisualizar;
    }

    
    @Column(name="pf_menu_visualizar_hoja", nullable=false)
    public boolean isPfMenuVisualizarHoja() {
        return this.pfMenuVisualizarHoja;
    }
    
    public void setPfMenuVisualizarHoja(boolean pfMenuVisualizarHoja) {
        this.pfMenuVisualizarHoja = pfMenuVisualizarHoja;
    }

    
    @Column(name="pf_menu_consultas", nullable=false)
    public boolean isPfMenuConsultas() {
        return this.pfMenuConsultas;
    }
    
    public void setPfMenuConsultas(boolean pfMenuConsultas) {
        this.pfMenuConsultas = pfMenuConsultas;
    }

    
    @Column(name="pf_menu_consultas_consu", nullable=false)
    public boolean isPfMenuConsultasConsu() {
        return this.pfMenuConsultasConsu;
    }
    
    public void setPfMenuConsultasConsu(boolean pfMenuConsultasConsu) {
        this.pfMenuConsultasConsu = pfMenuConsultasConsu;
    }

    
    @Column(name="pf_menu_perfil", nullable=false)
    public boolean isPfMenuPerfil() {
        return this.pfMenuPerfil;
    }
    
    public void setPfMenuPerfil(boolean pfMenuPerfil) {
        this.pfMenuPerfil = pfMenuPerfil;
    }

    
    @Column(name="pf_menu_perfil_contrasena", nullable=false)
    public boolean isPfMenuPerfilContrasena() {
        return this.pfMenuPerfilContrasena;
    }
    
    public void setPfMenuPerfilContrasena(boolean pfMenuPerfilContrasena) {
        this.pfMenuPerfilContrasena = pfMenuPerfilContrasena;
    }

    
    @Column(name="pf_menu_administrar", nullable=false)
    public boolean isPfMenuAdministrar() {
        return this.pfMenuAdministrar;
    }
    
    public void setPfMenuAdministrar(boolean pfMenuAdministrar) {
        this.pfMenuAdministrar = pfMenuAdministrar;
    }

    
    @Column(name="pf_menu_administrar_perfil", nullable=false)
    public boolean isPfMenuAdministrarPerfil() {
        return this.pfMenuAdministrarPerfil;
    }
    
    public void setPfMenuAdministrarPerfil(boolean pfMenuAdministrarPerfil) {
        this.pfMenuAdministrarPerfil = pfMenuAdministrarPerfil;
    }

    
    @Column(name="pf_menu_administrar_usuario", nullable=false)
    public boolean isPfMenuAdministrarUsuario() {
        return this.pfMenuAdministrarUsuario;
    }
    
    public void setPfMenuAdministrarUsuario(boolean pfMenuAdministrarUsuario) {
        this.pfMenuAdministrarUsuario = pfMenuAdministrarUsuario;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maPerfil")
    public Set getMaUsuarios() {
        return this.maUsuarios;
    }
    
    public void setMaUsuarios(Set maUsuarios) {
        this.maUsuarios = maUsuarios;
    }




}


