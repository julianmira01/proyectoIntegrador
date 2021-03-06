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
 * MaAreaestudio generated by hbm2java
 */
@Entity
@Table(name="ma_areaestudio"
    ,catalog="bdsiifnsp"
)
public class MaAreaestudio  implements java.io.Serializable {


     private Integer aeCod;
     private String aeNombre;
     private Set peEstudioses = new HashSet(0);

    public MaAreaestudio() {
    }

    public MaAreaestudio(String aeNombre, Set peEstudioses) {
       this.aeNombre = aeNombre;
       this.peEstudioses = peEstudioses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ae_cod", unique=true, nullable=false)
    public Integer getAeCod() {
        return this.aeCod;
    }
    
    public void setAeCod(Integer aeCod) {
        this.aeCod = aeCod;
    }

    
    @Column(name="ae_nombre", length=150)
    public String getAeNombre() {
        return this.aeNombre;
    }
    
    public void setAeNombre(String aeNombre) {
        this.aeNombre = aeNombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maAreaestudio")
    public Set getPeEstudioses() {
        return this.peEstudioses;
    }
    
    public void setPeEstudioses(Set peEstudioses) {
        this.peEstudioses = peEstudioses;
    }




}


