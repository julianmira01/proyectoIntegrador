/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.MaAreaestudio;
import com.udea.fnsp.buho.modelo.MaTipoestudio;
import com.udea.fnsp.buho.modelo.PeEstudios;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface EstudiosPersonaDaoInterface {    
    public List<MaTipoestudio> selectTipoestudio();    
    public List<PeEstudios> findAll(String idPersona);
    public List<MaAreaestudio> selectMaareaestudio();
    public boolean create(PeEstudios estudio);
    public boolean update(PeEstudios estudio);
    public boolean delete(Integer id);   
    
}
