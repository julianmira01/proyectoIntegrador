/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.MaIdiomas;
import com.udea.fnsp.buho.modelo.PeIdiomas;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IdiomasPersonaDaoInterface {
    public List<MaIdiomas> selectIdiomas();    
    public List<PeIdiomas> findAll(String idPersona);
    public boolean create(PeIdiomas idioma);
    public boolean update(PeIdiomas idioma);
    public boolean delete(Integer id);    
}
