/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapRuat.controller;

import CapRuat.dao.Cliente;
import CapRuat.servicio.ClienteFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Alexeis
 */
@Named(value = "testManagedBean")
@RequestScoped
public class testManagedBean {

    @Inject
    private ClienteFacadeLocal em;
    /**
     * Creates a new instance of testManagedBean
     */
    public testManagedBean() {
    }
    
    public void insertarDatos(){
        Cliente cliente = new Cliente();
        cliente.setDirCliente("Calle los álamos");
        cliente.setNomCliente("Juan Morales");
        cliente.setTelCliente("88877744");
        em.create(cliente);
    }
}
