/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.UsuarioDaoInterface;
import com.udea.fnsp.buho.modelo.MaUsuario;
import com.udea.fnsp.buho.modelo.PePersona;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Usuario
 */
public class UsuarioDao implements UsuarioDaoInterface, Serializable {

//    Metodo para buscar usuario por identificacion, retorna un objeto de tipo usuario con todos
//    sus atributos
    @Override
    public MaUsuario findByUsuario(MaUsuario usuario) {
        MaUsuario model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM MaUsuario usu left join fetch usu.pePersona left join fetch usu.maPerfil WHERE usu.pePersona = '" + usuario.getPePersona().getPeNumeroid() + "'";
        try {
            sesion.beginTransaction();
            model = (MaUsuario) sesion.createQuery(sql).uniqueResult();
            //sesion.beginTransaction().commit();

        } catch (HibernateException e) {
            sesion.beginTransaction().rollback();
        }
        return model;
    }

    
    //Metodo para loguear el usuario
    @Override
    public MaUsuario login(MaUsuario usuario) {
        
        String encriUsuario = DigestUtils.md5Hex(usuario.getUsClave()); //Se recibe la clave que ingresa el usuario, y se encripta
        usuario.setUsClave(encriUsuario); // se le setea al usuario la clave encriptada
        MaUsuario model = this.findByUsuario(usuario);// Se utiliza el metodo para buscar si existe un usuario con esos datos en la base de datos
        if (model != null) { 
            if (!usuario.getUsClave().equals(model.getUsClave())) {// si el usuario existe, se compara la clave que ingreso el usuario con la que esta en la bd
                model = null;
            }

        }
        return model;
    }

    @Override
    public List<MaUsuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(MaUsuario usuario, PePersona persona) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.save(persona);
            sesion.save(usuario);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();

        }
        return flag;
    }

    @Override
    public boolean update(MaUsuario usuario) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.update(usuario);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();

        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
