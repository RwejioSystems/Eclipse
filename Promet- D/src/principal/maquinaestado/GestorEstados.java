package principal.maquinaestado;

import java.awt.Graphics;

import principal.maquinaestado.estados.juego.GestorJuego;

public class GestorEstados {
	private EstadoJuego [] estados;
	private EstadoJuego estadoActual;
	
	public GestorEstados(){
		iniciarEstados();
		iniciarEstadoActual();
	}
	
	private void iniciarEstados() {
		estados = new EstadoJuego [2];
		estados[0] = new GestorJuego();
		//añadir los demas estados a medida que se construyan
	}
	
	private void iniciarEstadoActual() {
		estadoActual=estados[0];
	}
	
	public void actualizar(){
		estadoActual.actualizar();
	}
	
	public void dibujar(final Graphics g){
		estadoActual.dibujar(g);
	}
	
	public void cambiarEstadoActual(final int nuevoEstado){
		estadoActual = estados[nuevoEstado];
	}
	
	public EstadoJuego obtenerEstadoActual(){
		return estadoActual;
	}
}
