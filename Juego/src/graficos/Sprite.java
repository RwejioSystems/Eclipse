package graficos;

public final class Sprite {
	private final int lado;
	private int x;
	private int y;
	public int[] pixeles;
	private HojaSprites hoja;
	
	//coleccion de sprites
	public static final Sprite VACIO=new Sprite(32, 0);
	public static final Sprite ASFALTO= new Sprite(32, 0, 0, HojaSprites.desierto);
	//fin de la coleccion
	
	public int obtenLado(){
		return lado;
	}
	
	public Sprite(int lado, int color){
		this.lado=lado;
		pixeles=new int [lado * lado];
		for(int i=0; i< pixeles.length; i++){
			pixeles[i]=color;  
		}
	}
	
	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
		this.lado=lado;
		
		pixeles= new int[lado* lado];
		
		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja=hoja;
		
		for(int y=0; y < lado; y++){
			for(int x=0; x< lado; x++){
				pixeles[x + y * lado]=hoja.pixeles[ ( x + this.x) + ( y + this.y ) * hoja.obenerAncho()];
			}
		}
	}
}
