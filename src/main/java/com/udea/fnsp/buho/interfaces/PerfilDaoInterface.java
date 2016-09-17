/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.MaPerfil;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PerfilDaoInterface {
    public boolean actualizar(MaPerfil perfil);
    public MaPerfil buscarPorId(Integer id);
    public List<MaPerfil> buscarTodos();
    
}
