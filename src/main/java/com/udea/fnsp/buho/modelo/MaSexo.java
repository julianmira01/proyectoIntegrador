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
 * MaSexo generated by hbm2java
 */
@Entity
@Table(name="ma_sexo"
    ,catalog="bdsiifnsp"
)
public class MaSexo  implements java.io.Serializable {


     private String seCod;
     private String seNombre;
     private Set pePersonas = new HashSet(0);

    public MaSexo() {
    }

	
    public MaSexo(String seCod) {
        this.seCod = seCod;
    }
    public MaSexo(String seCod, String seNombre, Set pePersonas) {
       this.seCod = seCod;
       this.seNombre = seNombre;
       this.pePersonas = pePersonas;
    }
   
     @Id 

    
    @Column(name="se_cod", unique=true, nullable=false, length=1)
    public String getSeCod() {
        return this.seCod;
    }
    
    public void setSeCod(String seCod) {
        this.seCod = seCod;
    }

    
    @Column(name="Se_nombre", length=15)
    public String getSeNombre() {
        return this.seNombre;
    }
    
    public void setSeNombre(String seNombre) {
        this.seNombre = seNombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maSexo")
    public Set getPePersonas() {
        return this.pePersonas;
    }
    
    public void setPePersonas(Set pePersonas) {
        this.pePersonas = pePersonas;
    }




}

