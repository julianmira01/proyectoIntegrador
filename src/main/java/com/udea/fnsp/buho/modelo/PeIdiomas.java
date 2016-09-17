package com.udea.fnsp.buho.modelo;
// Generated 25/06/2014 12:01:44 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PeIdiomas generated by hbm2java
 */
@Entity
@Table(name="pe_idiomas"
    ,catalog="bdsiifnsp"
)
public class PeIdiomas  implements java.io.Serializable {


     private Integer pidCod;
     private PePersona pePersona;
     private MaIdiomas maIdiomas;
     private String pidLectura;
     private String pidEscritura;
     private String pidHabla;

    public PeIdiomas() {
        this.pePersona = new PePersona();
        this.maIdiomas = new MaIdiomas();
    }

    public PeIdiomas(PePersona pePersona, MaIdiomas maIdiomas, String pidLectura, String pidEscritura, String pidHabla) {
       this.pePersona = pePersona;
       this.maIdiomas = maIdiomas;
       this.pidLectura = pidLectura;
       this.pidEscritura = pidEscritura;
       this.pidHabla = pidHabla;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="pid_cod", unique=true, nullable=false)
    public Integer getPidCod() {
        return this.pidCod;
    }
    
    public void setPidCod(Integer pidCod) {
        this.pidCod = pidCod;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pid_persona", nullable=false)
    public PePersona getPePersona() {
        return this.pePersona;
    }
    
    public void setPePersona(PePersona pePersona) {
        this.pePersona = pePersona;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pid_idioma", nullable=false)
    public MaIdiomas getMaIdiomas() {
        return this.maIdiomas;
    }
    
    public void setMaIdiomas(MaIdiomas maIdiomas) {
        this.maIdiomas = maIdiomas;
    }

    
    @Column(name="pid_lectura", nullable=false, length=10)
    public String getPidLectura() {
        return this.pidLectura;
    }
    
    public void setPidLectura(String pidLectura) {
        this.pidLectura = pidLectura;
    }

    
    @Column(name="pid_escritura", nullable=false, length=10)
    public String getPidEscritura() {
        return this.pidEscritura;
    }
    
    public void setPidEscritura(String pidEscritura) {
        this.pidEscritura = pidEscritura;
    }

    
    @Column(name="pid_habla", nullable=false, length=10)
    public String getPidHabla() {
        return this.pidHabla;
    }
    
    public void setPidHabla(String pidHabla) {
        this.pidHabla = pidHabla;
    }




}

