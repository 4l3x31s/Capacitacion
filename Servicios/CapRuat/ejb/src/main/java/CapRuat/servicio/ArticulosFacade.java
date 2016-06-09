/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapRuat.servicio;

import CapRuat.dao.Articulos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexeis
 */
@Stateless
public class ArticulosFacade extends AbstractFacade<Articulos> implements ArticulosFacadeLocal {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticulosFacade() {
        super(Articulos.class);
    }
    
}
