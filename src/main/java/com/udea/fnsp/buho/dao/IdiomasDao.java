/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.IdiomasPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaIdiomas;
import com.udea.fnsp.buho.modelo.PeIdiomas;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class IdiomasDao implements IdiomasPersonaDaoInterface,Serializable {

    @Override
    public List<MaIdiomas> selectIdiomas() {
        List<MaIdiomas> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaIdiomas";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();

        } catch (HibernateException e) {
            sesion.beginTransaction().rollback();
        }
        return listado;
    }

    @Override
    public List<PeIdiomas> findAll(String idPersona) {
        List<PeIdiomas> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PeIdiomas idi join fetch idi.maIdiomas WHERE idi.pePersona = '"+idPersona+"'";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
            
        } catch (HibernateException e) {
            sesion.beginTransaction().rollback();
        }
        return listado;
    }

    @Override
    public boolean create(PeIdiomas idioma) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
            sesion.beginTransaction();
            sesion.save(idioma);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PeIdiomas idioma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {      
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();        
        try {
            sesion.beginTransaction();
            PeIdiomas idioma =  (PeIdiomas) sesion.load(PeIdiomas.class, id);
            sesion.delete(idioma);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;        
    }
    
}
