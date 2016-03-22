package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoB implements KeyListener{
	private final int numero_teclas = 256;
	private final boolean teclas[] = new boolean[numero_teclas];
	private boolean arriba;
	private boolean abajo;
	private boolean izquierda;
	private boolean derecha;
	private boolean correr;
	private boolean salir;
	
	public boolean pulsadoArriba(){
		return arriba;
	}
	
	public boolean pulsadoAbajo(){
		return abajo;
	}
	
	public boolean pulsadoIzquierda(){
		return izquierda;
	}
	
	public boolean pulsadoDerecha(){
		return derecha;
	}
	
	public boolean pulsadoCorrer(){
		return correr;
	}
	
	public boolean pulsadoSalir(){
		return salir;
	}
	
	public void actualizar(){
		arriba = teclas[KeyEvent.VK_W] || teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_S] || teclas[KeyEvent.VK_DOWN];
		izquierda = teclas[KeyEvent.VK_A] || teclas[KeyEvent.VK_LEFT];
		derecha = teclas[KeyEvent.VK_D] || teclas[KeyEvent.VK_RIGHT];
		
		correr = teclas[KeyEvent.VK_SHIFT];
		
		salir = teclas[KeyEvent.VK_ESCAPE];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()]=false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}