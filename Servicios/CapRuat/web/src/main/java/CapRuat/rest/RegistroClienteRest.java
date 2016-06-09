package CapRuat.rest;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.google.gson.Gson;

import CapRuat.dao.Cliente;
import CapRuat.dao.RequestClienteDao;
import CapRuat.dao.ResponseClienteDao;
import CapRuat.servicio.ClienteFacadeLocal;

@Path("/cliente")
@RequestScoped
public class RegistroClienteRest {
	
	@Inject
	private ClienteFacadeLocal clienteServicio;
	
	@POST
	@Path("/registro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registroCliente(RequestClienteDao requestClienteDao){
		ResponseClienteDao response = new ResponseClienteDao();
		try{
			Cliente cliente = new Cliente();
			cliente.setDirCliente(requestClienteDao.getDireccionCliente());
			cliente.setNomCliente(requestClienteDao.getNombreCliente());
			cliente.setTelCliente(requestClienteDao.getTelefonoCliente());
			clienteServicio.create(cliente);
			response.setContinuarFlujo(true);
			response.setMensaje("El dato se ha insertado correctamente");
		}catch(Exception ex){
			response.setContinuarFlujo(false);
			response.setMensaje("Error: " + ex.getMessage());
		}
		finally {
			Gson gson = new Gson();
			if(response.isContinuarFlujo()){
				return Response.ok(gson.toJson(response)).build();
			}else{
				return Response.ok(gson.toJson(response)).build();
			}
		}
		
	}
}
