/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.CiudadesDao;
import com.udea.fnsp.buho.interfaces.CiudadesDaoInterface;
import com.udea.fnsp.buho.modelo.MaDepartamento;
import com.udea.fnsp.buho.modelo.MaMunicipio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sEBASTIAN
 */
@ManagedBean
@SessionScoped
public class CiudadesBean implements Serializable {

    /**
     * Creates a new instance of selectBean
     * Prueba Julian
     */
    private String departamento;
    private String ciudad;
    
    /**prueba repor */

    private Map<String, String> departamentos = new HashMap<String, String>();
    private Map<String, Map<String, String>> datosCiudad = new HashMap<String, Map<String, String>>();
    private Map<String, String> ciudades = new HashMap<String, String>();

    public CiudadesBean() {
        CiudadesDaoInterface CiudadesDao = new CiudadesDao();
        List<MaDepartamento> tipoDepa = CiudadesDao.selectDepartamentos();
        for (MaDepartamento depar : tipoDepa) {
            departamentos.put(depar.getDeptoNombre(), depar.getDptoCod());
        }
        for (MaDepartamento depart : tipoDepa) {
            Map<String, String> ciudadesDepartamento = new HashMap<String, String>();
            List<MaMunicipio> tipoMuni = CiudadesDao.selectMunicipios(depart.getDptoCod());
            for (MaMunicipio ciuda : tipoMuni) {
                ciudadesDepartamento.put(ciuda.getMpioNombre(), ciuda.getCodMpio());
                datosCiudad.put(depart.getDptoCod(), ciudadesDepartamento);
            }

        }
    }

    public Map<String, String> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Map<String, String> departamentos) {
        this.departamentos = departamentos;
    }

    public Map<String, Map<String, String>> getDeparCiu() {
        return datosCiudad;
    }

    public void setDeparCiu(Map<String, Map<String, String>> deparCiu) {
        this.datosCiudad = deparCiu;
    }

    public Map<String, String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Map<String, String> ciudades) {
        this.ciudades = ciudades;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Map<String, Map<String, String>> getDatosCiudad() {
        return datosCiudad;
    }

    public void setDatosCiudad(Map<String, Map<String, String>> datosCiudad) {
        this.datosCiudad = datosCiudad;
    }

    public void handleCityChange() {

        if (departamento != null && !departamento.equals("")) {
            ciudades = datosCiudad.get(departamento);
        } else {
            ciudades = new HashMap<String, String>();
        }
    }

}
