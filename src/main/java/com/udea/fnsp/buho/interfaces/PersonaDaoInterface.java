/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.PePersona;
/**
 *
 * @author Usuario
 */
public interface PersonaDaoInterface {
    public PePersona findByPersona(String identificacion);
    public boolean create(PePersona persona);
    public boolean update(PePersona persona);
    public boolean delete(Integer id);
    
}
