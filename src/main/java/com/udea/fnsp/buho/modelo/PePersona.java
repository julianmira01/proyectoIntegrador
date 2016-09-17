package com.udea.fnsp.buho.modelo;
// Generated 25/06/2014 12:01:44 PM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PePersona generated by hbm2java
 */
@Entity
@Table(name = "pe_persona", catalog = "bdsiifnsp"
)
public class PePersona implements java.io.Serializable {

    private String peNumeroid;
    private MaPais maPais;
    private MaMunicipio maMunicipioByPeLugarnacimiento;
    private MaTipoid maTipoid;
    private MaMunicipio maMunicipioByPeLugarexpced;
    private MaSexo maSexo;
    private MaMunicipio maMunicipioByPeCiudadresidencia;
    private Date peFechaexpced;
    private String peLibretamilitar;
    private String peDistritoclase;
    private String peApellidos;
    private String peNombres;
    private Date peFechaNac;
    private String peDireccionresidencia;
    private String peTelefono;
    private String peTeloficina;
    private String peCelular;
    private String peEmail;
    private String pePerfil;
    private String peFoto;
    private Date peUpdate;
    private Boolean peDisponibilidadTrabajoBogota;
    private Boolean peDisponibilidadTrabajoOtrasRegiones;
    private Set peIdiomases = new HashSet(0);
    private Set peInteresespersonas = new HashSet(0);
    private Set maUsuarios = new HashSet(0);
    private Set peEstudioses = new HashSet(0);
    private Set peExplaborals = new HashSet(0);

    public PePersona() {
        this.maTipoid = new MaTipoid();
        this.maSexo = new MaSexo();
        this.maPais = new MaPais();
    }

    public PePersona(String peNumeroid, Date peUpdate) {
        this.peNumeroid = peNumeroid;
        this.peUpdate = peUpdate;
    }

    public PePersona(String peNumeroid, MaPais maPais, MaMunicipio maMunicipioByPeLugarnacimiento, MaTipoid maTipoid, MaMunicipio maMunicipioByPeLugarexpced, MaSexo maSexo, MaMunicipio maMunicipioByPeCiudadresidencia, Date peFechaexpced, String peLibretamilitar, String peDistritoclase, String peApellidos, String peNombres, Date peFechaNac, String peDireccionresidencia, String peTelefono, String peTeloficina, String peCelular, String peEmail, String pePerfil, String peFoto, Date peUpdate, Set peIdiomases, Set peInteresespersonas, Set maUsuarios, Set peEstudioses, Set peExplaborals) {
        this.peNumeroid = peNumeroid;
        this.maPais = maPais;
        this.maMunicipioByPeLugarnacimiento = maMunicipioByPeLugarnacimiento;
        this.maTipoid = maTipoid;
        this.maMunicipioByPeLugarexpced = maMunicipioByPeLugarexpced;
        this.maSexo = maSexo;
        this.maMunicipioByPeCiudadresidencia = maMunicipioByPeCiudadresidencia;
        this.peFechaexpced = peFechaexpced;
        this.peLibretamilitar = peLibretamilitar;
        this.peDistritoclase = peDistritoclase;
        this.peApellidos = peApellidos;
        this.peNombres = peNombres;
        this.peFechaNac = peFechaNac;
        this.peDireccionresidencia = peDireccionresidencia;
        this.peTelefono = peTelefono;
        this.peTeloficina = peTeloficina;
        this.peCelular = peCelular;
        this.peEmail = peEmail;
        this.pePerfil = pePerfil;
        this.peFoto = peFoto;
        this.peUpdate = peUpdate;
        this.peIdiomases = peIdiomases;
        this.peInteresespersonas = peInteresespersonas;
        this.maUsuarios = maUsuarios;
        this.peEstudioses = peEstudioses;
        this.peExplaborals = peExplaborals;
    }

    @Id

    @Column(name = "pe_numeroid", unique = true, nullable = false, length = 20)
    public String getPeNumeroid() {
        return this.peNumeroid;
    }

    public void setPeNumeroid(String peNumeroid) {
        this.peNumeroid = peNumeroid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_nacionalidad")
    public MaPais getMaPais() {
        return this.maPais;
    }

    public void setMaPais(MaPais maPais) {
        this.maPais = maPais;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_lugarnacimiento")
    public MaMunicipio getMaMunicipioByPeLugarnacimiento() {
        return this.maMunicipioByPeLugarnacimiento;
    }

    public void setMaMunicipioByPeLugarnacimiento(MaMunicipio maMunicipioByPeLugarnacimiento) {
        this.maMunicipioByPeLugarnacimiento = maMunicipioByPeLugarnacimiento;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_tipoid")
    public MaTipoid getMaTipoid() {
        return this.maTipoid;
    }

    public void setMaTipoid(MaTipoid maTipoid) {
        this.maTipoid = maTipoid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_lugarexpced")
    public MaMunicipio getMaMunicipioByPeLugarexpced() {
        return this.maMunicipioByPeLugarexpced;
    }

    public void setMaMunicipioByPeLugarexpced(MaMunicipio maMunicipioByPeLugarexpced) {
        this.maMunicipioByPeLugarexpced = maMunicipioByPeLugarexpced;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_sexo")
    public MaSexo getMaSexo() {
        return this.maSexo;
    }

    public void setMaSexo(MaSexo maSexo) {
        this.maSexo = maSexo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pe_ciudadresidencia")
    public MaMunicipio getMaMunicipioByPeCiudadresidencia() {
        return this.maMunicipioByPeCiudadresidencia;
    }

    public void setMaMunicipioByPeCiudadresidencia(MaMunicipio maMunicipioByPeCiudadresidencia) {
        this.maMunicipioByPeCiudadresidencia = maMunicipioByPeCiudadresidencia;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "pe_fechaexpced", length = 10)
    public Date getPeFechaexpced() {
        return this.peFechaexpced;
    }

    public void setPeFechaexpced(Date peFechaexpced) {
        this.peFechaexpced = peFechaexpced;
    }

    @Column(name = "pe_libretamilitar", length = 20)
    public String getPeLibretamilitar() {
        return this.peLibretamilitar;
    }

    public void setPeLibretamilitar(String peLibretamilitar) {
        this.peLibretamilitar = peLibretamilitar;
    }

    @Column(name = "pe_distritoclase", length = 30)
    public String getPeDistritoclase() {
        return this.peDistritoclase;
    }

    public void setPeDistritoclase(String peDistritoclase) {
        this.peDistritoclase = peDistritoclase;
    }

    @Column(name = "pe_apellidos", length = 35)
    public String getPeApellidos() {
        return this.peApellidos;
    }

    public void setPeApellidos(String peApellidos) {
        this.peApellidos = peApellidos;
    }

    @Column(name = "pe_nombres", length = 80)
    public String getPeNombres() {
        return this.peNombres;
    }

    public void setPeNombres(String peNombres) {
        this.peNombres = peNombres;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "pe_fechaNac", length = 10)
    public Date getPeFechaNac() {
        return this.peFechaNac;
    }

    public void setPeFechaNac(Date peFechaNac) {
        this.peFechaNac = peFechaNac;
    }

    @Column(name = "pe_direccionresidencia", length = 250)
    public String getPeDireccionresidencia() {
        return this.peDireccionresidencia;
    }

    public void setPeDireccionresidencia(String peDireccionresidencia) {
        this.peDireccionresidencia = peDireccionresidencia;
    }

    @Column(name = "pe_telefono", length = 15)
    public String getPeTelefono() {
        return this.peTelefono;
    }

    public void setPeTelefono(String peTelefono) {
        this.peTelefono = peTelefono;
    }

    @Column(name = "pe_teloficina", length = 15)
    public String getPeTeloficina() {
        return this.peTeloficina;
    }

    public void setPeTeloficina(String peTeloficina) {
        this.peTeloficina = peTeloficina;
    }

    @Column(name = "pe_celular", length = 15)
    public String getPeCelular() {
        return this.peCelular;
    }

    public void setPeCelular(String peCelular) {
        this.peCelular = peCelular;
    }

    @Column(name = "pe_email", length = 250)
    public String getPeEmail() {
        return this.peEmail;
    }

    public void setPeEmail(String peEmail) {
        this.peEmail = peEmail;
    }

    @Column(name = "pe_perfil")
    public String getPePerfil() {
        return this.pePerfil;
    }

    public void setPePerfil(String pePerfil) {
        this.pePerfil = pePerfil;
    }

    @Column(name = "pe_foto", length = 250)
    public String getPeFoto() {
        return this.peFoto;
    }

    public void setPeFoto(String peFoto) {
        this.peFoto = peFoto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pe_update", nullable = false, length = 19)
    public Date getPeUpdate() {
        return this.peUpdate;
    }

    public void setPeUpdate(Date peUpdate) {
        this.peUpdate = peUpdate;
    }

      @Column(name = "pe_disponibilidad_trabajo_bogota")
    public Boolean getPeDisponibilidadTrabajoBogota() {
        return peDisponibilidadTrabajoBogota;
    }

    public void setPeDisponibilidadTrabajoBogota(Boolean peDisponibilidadTrabajoBogota) {
        this.peDisponibilidadTrabajoBogota = peDisponibilidadTrabajoBogota;
    }

      @Column(name = "pe_disponibilidad_trabajo_otras_regiones")
    public Boolean getPeDisponibilidadTrabajoOtrasRegiones() {
        return peDisponibilidadTrabajoOtrasRegiones;
    }

    public void setPeDisponibilidadTrabajoOtrasRegiones(Boolean peDisponibilidadTrabajoOtrasRegiones) {
        this.peDisponibilidadTrabajoOtrasRegiones = peDisponibilidadTrabajoOtrasRegiones;
    }
    
    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pePersona")
    public Set getPeIdiomases() {
        return this.peIdiomases;
    }

    public void setPeIdiomases(Set peIdiomases) {
        this.peIdiomases = peIdiomases;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pePersona")
    public Set getPeInteresespersonas() {
        return this.peInteresespersonas;
    }

    public void setPeInteresespersonas(Set peInteresespersonas) {
        this.peInteresespersonas = peInteresespersonas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pePersona")
    public Set getMaUsuarios() {
        return this.maUsuarios;
    }

    public void setMaUsuarios(Set maUsuarios) {
        this.maUsuarios = maUsuarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pePersona")
    public Set getPeEstudioses() {
        return this.peEstudioses;
    }

    public void setPeEstudioses(Set peEstudioses) {
        this.peEstudioses = peEstudioses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pePersona")
    public Set getPeExplaborals() {
        return this.peExplaborals;
    }

    public void setPeExplaborals(Set peExplaborals) {
        this.peExplaborals = peExplaborals;
    }

}