/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.PerfilDaoInterface;
import com.udea.fnsp.buho.modelo.MaPerfil;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class PerfilDao implements PerfilDaoInterface{

    @Override
    public boolean actualizar(MaPerfil perfil) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.merge(perfil);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();

        }
        return flag;
    }

    @Override
    public MaPerfil buscarPorId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (MaPerfil) session.load(MaPerfil.class, id);
    }

    @Override
    public List<MaPerfil> buscarTodos() {
        List<MaPerfil> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaPerfil";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
            
        } catch (HibernateException e) {
            sesion.beginTransaction().rollback();
        }
        return listado;
    }
    
}
