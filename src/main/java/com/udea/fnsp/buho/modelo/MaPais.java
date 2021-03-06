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
 * MaPais generated by hbm2java
 */
@Entity
@Table(name="ma_pais"
    ,catalog="bdsiifnsp"
)
public class MaPais  implements java.io.Serializable {


     private int paisCod;
     private String paisContinente;
     private String paisNombre;
     private Set pePersonas = new HashSet(0);

    public MaPais() {
        this.paisCod = 0;
    }

	
    public MaPais(int paisCod, String paisNombre) {
        this.paisCod = paisCod;
        this.paisNombre = paisNombre;
    }
    public MaPais(int paisCod, String paisContinente, String paisNombre, Set pePersonas) {
       this.paisCod = paisCod;
       this.paisContinente = paisContinente;
       this.paisNombre = paisNombre;
       this.pePersonas = pePersonas;
    }
   
     @Id 

    
    @Column(name="pais_cod", unique=true, nullable=false)
    public int getPaisCod() {
        return this.paisCod;
    }
    
    public void setPaisCod(int paisCod) {
        this.paisCod = paisCod;
    }

    
    @Column(name="pais_continente", length=50)
    public String getPaisContinente() {
        return this.paisContinente;
    }
    
    public void setPaisContinente(String paisContinente) {
        this.paisContinente = paisContinente;
    }

    
    @Column(name="pais_nombre", nullable=false, length=60)
    public String getPaisNombre() {
        return this.paisNombre;
    }
    
    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maPais")
    public Set getPePersonas() {
        return this.pePersonas;
    }
    
    public void setPePersonas(Set pePersonas) {
        this.pePersonas = pePersonas;
    }




}


