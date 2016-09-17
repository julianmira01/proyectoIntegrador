/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.dao;

import com.udea.fnsp.buho.interfaces.PersonaDaoInterface;
import com.udea.fnsp.buho.modelo.PePersona;
import com.udea.fnsp.buho.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class PersonaDao implements PersonaDaoInterface, Serializable {

    //Metodo para crear persona, se recibe un objeto de tipo PePersona y se guarda en la bd
    @Override
    public boolean create(PePersona persona) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            sesion.save(persona);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    //Metodo para actualizar los datos de una pérsona;
    @Override
    public boolean update(PePersona persona) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();
            PePersona personadb = (PePersona) sesion.load(PePersona.class, persona.getPeNumeroid()); // Se carga la persona que esta en la bd
            personadb.setMaTipoid(persona.getMaTipoid()); // Se empieza a setearles la nueva informacion
            personadb.setMaMunicipioByPeCiudadresidencia(persona.getMaMunicipioByPeCiudadresidencia());
            personadb.setMaMunicipioByPeLugarnacimiento(persona.getMaMunicipioByPeLugarnacimiento());
            personadb.setMaMunicipioByPeLugarexpced(persona.getMaMunicipioByPeLugarexpced());
            personadb.setPeFechaexpced(persona.getPeFechaexpced());
            personadb.setMaSexo(persona.getMaSexo());
            personadb.setPeApellidos(persona.getPeApellidos());
            personadb.setPeCelular(persona.getPeCelular());
            personadb.setPeDireccionresidencia(persona.getPeDireccionresidencia());
            personadb.setPeEmail(persona.getPeEmail());
            personadb.setPeFechaNac(persona.getPeFechaNac());
            personadb.setPeLibretamilitar(persona.getPeLibretamilitar());
            personadb.setPeNombres(persona.getPeNombres());
            personadb.setPeNumeroid(persona.getPeNumeroid());
            personadb.setPeTelefono(persona.getPeTelefono());
            personadb.setPeTeloficina(persona.getPeTeloficina());
            personadb.setPeUpdate(new Date());
            personadb.setPePerfil(persona.getPePerfil());
            personadb.setPeDistritoclase(persona.getPeDistritoclase());
            personadb.setMaPais(persona.getMaPais());
            personadb.setPeFoto(persona.getPeFoto());
            personadb.setPeDisponibilidadTrabajoBogota(persona.getPeDisponibilidadTrabajoBogota());
            personadb.setPeDisponibilidadTrabajoOtrasRegiones(persona.getPeDisponibilidadTrabajoOtrasRegiones());
            sesion.update(personadb); //Por ultimo se guarda el objeto persona con la informacion que se haya modificado
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

    //Metodo que busca la persona por identificacion.En el query se hace la relacion con todos sus objetos dependientes, para poder ser mostrada la informacion, 
    //si esto no se hace, se genera un erro al tratar de mostrar informacion que este en las otras tablas en las que se tiene relacion
    @Override
    public PePersona findByPersona(String identificacion) {
        PePersona model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PePersona pe left join fetch pe.maMunicipioByPeLugarnacimiento naci left join fetch naci.maDepartamento"
                + " left join fetch pe.maMunicipioByPeCiudadresidencia muni"
                + " left join fetch muni.maDepartamento left join fetch pe.maTipoid"
                + " left join fetch pe.maSexo left join fetch pe.maPais left join fetch "
                + "pe.maMunicipioByPeLugarexpced exp left join fetch exp.maDepartamento WHERE pe.peNumeroid = '" + identificacion + "'";
        try {
             sesion.beginTransaction();
            model = (PePersona) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();

        } catch (HibernateException e) {
            sesion.beginTransaction().rollback();
        }
        return model;
    }

}
