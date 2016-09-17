/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;


import com.udea.fnsp.buho.modelo.MaAreasinteres;
import com.udea.fnsp.buho.modelo.PeInteresespersona;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ConvocatoriasDaoInterface {        
    public List<PeInteresespersona> findAllPeintereses(String idPersona);
    public List<MaAreasinteres> findAllMaareasintereses();
    public boolean create(PeInteresespersona interesPersona);
    public boolean update(PeInteresespersona interesesPersona);
    public boolean delete(Integer id);    
}
