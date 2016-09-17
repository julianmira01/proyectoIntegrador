/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.interfaces;

import com.udea.fnsp.buho.modelo.MaUsuario;
import com.udea.fnsp.buho.modelo.PePersona;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsuarioDaoInterface {
    //Metodos del CRUD
    public MaUsuario findByUsuario(MaUsuario usuario);
    public MaUsuario login(MaUsuario usuario);
    public List<MaUsuario> findAll();
    public boolean create(MaUsuario usuario,PePersona persona);
    public boolean update(MaUsuario usuario);
    public boolean delete(Integer id);
}
