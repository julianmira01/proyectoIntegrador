/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.TipoIdDao;
import com.udea.fnsp.buho.interfaces.TipoIdDaoInterface;
import com.udea.fnsp.buho.modelo.MaTipoid;
import java.io.Serializable;
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
public class TipoIdBean implements Serializable {

    
    public TipoIdBean() {
    }
    
    private List<SelectItem> selectOneItemsTipoId;
    
    public List<SelectItem> getSelectOneItemsTipoId() {
        this.selectOneItemsTipoId = new ArrayList<SelectItem>();
        TipoIdDaoInterface tipoIdDao = new TipoIdDao();
        List<MaTipoid> tipoList  = tipoIdDao.selectItems();
        for(MaTipoid tipoid: tipoList)
        {
        SelectItem selectItem = new SelectItem(tipoid.getTiCod(),tipoid.getTiNombre() );
        this.selectOneItemsTipoId.add(selectItem);
        }
        return selectOneItemsTipoId;
    }
    
    
    
}
