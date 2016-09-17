/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.PaisesDaoInterface;
import com.udea.fnsp.buho.modelo.MaPais;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class PaisesDao implements PaisesDaoInterface,Serializable {

    @Override
    public List<MaPais> selectItems() {
        List<MaPais> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaPais order by paisNombre";
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
