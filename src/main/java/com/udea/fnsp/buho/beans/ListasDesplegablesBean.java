/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.ListasDesplegablesDao;
import com.udea.fnsp.buho.interfaces.ListasDesplegablesDaoInterface;
import com.udea.fnsp.buho.modelo.MaTipoexperiencia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class ListasDesplegablesBean {

    /**
     * Creates a new instance of ListasDesplegablesBean
     */
    public ListasDesplegablesBean() {
    }
    
    
     private List<SelectItem> selectOneItemsTipoExp;

    public List<SelectItem> getSelectOneItemsTipoExp() {
        this.selectOneItemsTipoExp = new ArrayList<SelectItem>();
        ListasDesplegablesDaoInterface listasDao = new ListasDesplegablesDao();
        List<MaTipoexperiencia> tipoExp = listasDao.selectItemsTipoExp();
        for(MaTipoexperiencia tipoid: tipoExp)
        {
        SelectItem selectItem = new SelectItem(tipoid.getTeCod(),tipoid.getTeDescirpcion() );
        this.selectOneItemsTipoExp.add(selectItem);
        }
        
        return selectOneItemsTipoExp;
    }
     
     
}
