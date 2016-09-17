/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.TipoIdDaoInterface;
import com.udea.fnsp.buho.modelo.MaTipoid;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class TipoIdDao implements TipoIdDaoInterface,Serializable {

    //Se consulta el listado de tipos de id, para llenar los combos
    @Override
    public List<MaTipoid> selectItems() {
         List<MaTipoid> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaTipoid";
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
