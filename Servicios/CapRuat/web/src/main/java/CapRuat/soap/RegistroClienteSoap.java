package CapRuat.soap;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import CapRuat.dao.Cliente;
import CapRuat.dao.RequestClienteDao;
import CapRuat.dao.ResponseClienteDao;
import CapRuat.servicio.ClienteFacadeLocal;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
			use = SOAPBinding.Use.LITERAL,
			parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class RegistroClienteSoap {
	
	@Inject
	private ClienteFacadeLocal clienteServicio;
	
	@WebMethod
	public ResponseClienteDao registroCliente(
								@WebParam(name="nombreCliente") String nombreCliente, 
								@WebParam(name="direccionCliente") String direccionCliente, 
								@WebParam(name="telefonoCliente") String telefonoCliente){
		ResponseClienteDao response = new ResponseClienteDao();
		try{
			Cliente cliente = new Cliente();
			cliente.setDirCliente(direccionCliente);
			cliente.setNomCliente(nombreCliente);
			cliente.setTelCliente(telefonoCliente);
			clienteServicio.create(cliente);
			response.setContinuarFlujo(true);
			response.setMensaje("El dato se ha insertado correctamente");
		}catch(Exception ex){
			response.setContinuarFlujo(false);
			response.setMensaje("Error: " + ex.getMessage());
		}
		finally {
			if(response.isContinuarFlujo()){
				return response;
			}else{
				return response;
			}
		}
		
	}
}
