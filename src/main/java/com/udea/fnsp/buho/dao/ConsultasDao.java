/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.ConsultasDaoInterface;
import com.udea.fnsp.buho.modelo.PeEstudios;
import com.udea.fnsp.buho.modelo.PePersona;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Sebastian Restrepo
 */
public class ConsultasDao implements ConsultasDaoInterface {

    @Override
    public List<PeEstudios> personasConsulta(String Criterio, String Busqueda) {
        List<PeEstudios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql;
        sql = "";
        if ("titulo".equals(Criterio)) {
            sql = "From PeEstudios estudios join fetch estudios.pePersona estuPer "
                    + "where estudios.estNombreestudio LIKE '%"+Busqueda+"%' group by estuPer.peNumeroid";
        } else if ("experiencia".equals(Criterio)) {
            sql = "FROM PePersona";
        }
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
