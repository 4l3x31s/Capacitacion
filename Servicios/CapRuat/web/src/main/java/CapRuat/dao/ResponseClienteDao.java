package CapRuat.dao;

public class ResponseClienteDao {
	private boolean continuarFlujo;
	private String mensaje;
	public boolean isContinuarFlujo() {
		return continuarFlujo;
	}
	public void setContinuarFlujo(boolean continuarFlujo) {
		this.continuarFlujo = continuarFlujo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
