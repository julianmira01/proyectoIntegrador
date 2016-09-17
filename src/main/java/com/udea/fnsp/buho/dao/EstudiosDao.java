/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.EstudiosPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreaestudio;
import com.udea.fnsp.buho.modelo.MaTipoestudio;
import com.udea.fnsp.buho.modelo.PeEstudios;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class EstudiosDao implements EstudiosPersonaDaoInterface, Serializable {

    @Override
    public List<MaTipoestudio> selectTipoestudio() {
        List<MaTipoestudio> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaTipoestudio";
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
    public List<PeEstudios> findAll(String idPersona) {
        List<PeEstudios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PeEstudios peEst join fetch peEst.maTipoestudio join fetch peEst.maAreaestudio WHERE peEst.pePersona = '" + idPersona + "'";
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
    public List<MaAreaestudio> selectMaareaestudio() {
        List<MaAreaestudio> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaAreaestudio";
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
    public boolean create(PeEstudios estudio) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.save(estudio);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PeEstudios estudio) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.update(estudio);
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
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            PeEstudios estudio =  (PeEstudios) sesion.load(PeEstudios.class, id);
            sesion.delete(estudio);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

}
