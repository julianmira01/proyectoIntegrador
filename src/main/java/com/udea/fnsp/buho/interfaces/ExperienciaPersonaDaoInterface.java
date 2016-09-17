/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;


import com.udea.fnsp.buho.modelo.MaAreaexperiencia;
import com.udea.fnsp.buho.modelo.MaDedicacion;
import com.udea.fnsp.buho.modelo.MaNaturalezainst;
import com.udea.fnsp.buho.modelo.PeExplaboral;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ExperienciaPersonaDaoInterface {    
    public List<MaAreaexperiencia> selectAreaexperiencia();    
    public List<PeExplaboral> findAll(String idPersona);
    public List<MaNaturalezainst> selectNaturalezainst();
    public List<MaDedicacion> selectDedicacion();
    public boolean create(PeExplaboral explaboral);
    public boolean update(PeExplaboral explaboral);
    public boolean delete(Integer id);   
    
}
