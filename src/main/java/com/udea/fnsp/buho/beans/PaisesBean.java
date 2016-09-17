/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.PaisesDao;
import com.udea.fnsp.buho.interfaces.PaisesDaoInterface;
import com.udea.fnsp.buho.modelo.MaPais;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class PaisesBean {

    /**
     * Creates a new instance of PaisesBean
     */
    public PaisesBean() {
    }
    
    private List<SelectItem> selectOneItemsPaises;
    
    public List<SelectItem> getSelectOneItemsPaises() {
        this.selectOneItemsPaises = new ArrayList<SelectItem>();
        PaisesDaoInterface tipoIdDao = new PaisesDao();
        List<MaPais> tipoList  = tipoIdDao.selectItems();
        for(MaPais pais: tipoList)
        {
        SelectItem selectItem = new SelectItem(pais.getPaisCod(),pais.getPaisNombre());
        this.selectOneItemsPaises.add(selectItem);
        }
        return selectOneItemsPaises;
    }
    
}
