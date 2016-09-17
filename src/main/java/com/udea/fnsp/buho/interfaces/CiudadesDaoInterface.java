/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.MaDepartamento;
import com.udea.fnsp.buho.modelo.MaMunicipio;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface CiudadesDaoInterface{
    public List<MaMunicipio> selectMunicipios(String IdDepartamento);
    public List<MaDepartamento> selectDepartamentos();
}
