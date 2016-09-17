package com.udea.fnsp.buho.modelo;
// Generated 25/06/2014 12:01:44 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MaIdiomas generated by hbm2java
 */
@Entity
@Table(name="ma_idiomas"
    ,catalog="bdsiifnsp"
)
public class MaIdiomas  implements java.io.Serializable {


     private Integer idCod;
     private String idNombre;
     private Set peIdiomases = new HashSet(0);

    public MaIdiomas() {
    }

    public MaIdiomas(String idNombre, Set peIdiomases) {
       this.idNombre = idNombre;
       this.peIdiomases = peIdiomases;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_cod", unique=true, nullable=false)
    public Integer getIdCod() {
        return this.idCod;
    }
    
    public void setIdCod(Integer idCod) {
        this.idCod = idCod;
    }

    
    @Column(name="id_nombre", length=20)
    public String getIdNombre() {
        return this.idNombre;
    }
    
    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maIdiomas")
    public Set getPeIdiomases() {
        return this.peIdiomases;
    }
    
    public void setPeIdiomases(Set peIdiomases) {
        this.peIdiomases = peIdiomases;
    }




}

