/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.ConvocatoriasDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreasinteres;
import com.udea.fnsp.buho.modelo.PeInteresespersona;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class ConvocatoriasDao implements ConvocatoriasDaoInterface, Serializable {

    @Override
    public List<PeInteresespersona> findAllPeintereses(String idPersona) {
        List<PeInteresespersona> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PeInteresespersona peInt join fetch peInt.maAreasinteres maArea join fetch maArea.maAreaconvoca WHERE peInt.pePersona = '" + idPersona + "'";
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
    public List<MaAreasinteres> findAllMaareasintereses() {
        List<MaAreasinteres> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaAreasinteres maArea join fetch maArea.maAreaconvoca where maArea.aiFechafin >= current_date()";
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
    public boolean create(PeInteresespersona interesPersona) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.save(interesPersona);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PeInteresespersona interesesPersona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();        
        try {
            sesion.beginTransaction();
            PeInteresespersona interes =   (PeInteresespersona) sesion.load(PeInteresespersona.class, id);
            sesion.delete(interes);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;        
    }

}
