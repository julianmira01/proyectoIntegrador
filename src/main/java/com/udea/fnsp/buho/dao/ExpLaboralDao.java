/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.ExperienciaPersonaDaoInterface;
import com.udea.fnsp.buho.modelo.MaAreaexperiencia;
import com.udea.fnsp.buho.modelo.MaDedicacion;
import com.udea.fnsp.buho.modelo.MaNaturalezainst;
import com.udea.fnsp.buho.modelo.PeExplaboral;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class ExpLaboralDao implements ExperienciaPersonaDaoInterface, Serializable {

    @Override
    public List<MaAreaexperiencia> selectAreaexperiencia() {
        List<MaAreaexperiencia> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaAreaexperiencia";
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
    public List<PeExplaboral> findAll(String idPersona) {
        List<PeExplaboral> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PeExplaboral peExp join fetch peExp.maDedicacion join fetch peExp.maNaturalezainst"
                + " join fetch peExp.maAreaexperiencia left join fetch peExp.maTipoexperiencia  WHERE peExp.pePersona = '" + idPersona + "'";
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
    public List<MaNaturalezainst> selectNaturalezainst() {
        List<MaNaturalezainst> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaNaturalezainst";
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
    public List<MaDedicacion> selectDedicacion() {
        List<MaDedicacion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaDedicacion";
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
    public boolean create(PeExplaboral explaboral) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.save(explaboral);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PeExplaboral explaboral) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.update(explaboral);
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
            PeExplaboral explaboral = (PeExplaboral) sesion.load(PeExplaboral.class, id);
            sesion.delete(explaboral);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;

    }

}
