/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.CiudadesDaoInterface;
import com.udea.fnsp.buho.modelo.MaDepartamento;
import com.udea.fnsp.buho.modelo.MaMunicipio;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class CiudadesDao implements CiudadesDaoInterface,Serializable {

   
    @Override
    public List<MaMunicipio> selectMunicipios(String IdDepartamento) {
        List<MaMunicipio> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaMunicipio where maDepartamento='" + IdDepartamento + "' order by mpioNombre";
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
    public List<MaDepartamento> selectDepartamentos() {
        List<MaDepartamento> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "FROM MaDepartamento order by deptoNombre";
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
