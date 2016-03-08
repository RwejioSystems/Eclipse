package graficos;

public final class Sprite {
	private final int lado;
	private int x;
	private int y;
	public int[] pixeles;
	private HojaSprites hoja;
	
	//coleccion de sprites
	public static final Sprite VACIO=new Sprite(32, 0);
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
	public static final Sprite ARENA = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERA = new Sprite(32, 2, 0, 0, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERAB = new Sprite(32, 2, 0, 1, HojaSprites.desierto);
	public static final Sprite CENTRO_CARRETERA = new Sprite(32, 3, 0, 0, HojaSprites.desierto);
	public static final Sprite ESQUINA_CARRETERA = new Sprite(32, 4, 0, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA = new Sprite(32, 5, 0, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_INFERIOR = new Sprite(32, 6, 0, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_CARRETERA = new Sprite(32, 0, 3, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_CARRETERAB = new Sprite(32, 0, 3, 1, HojaSprites.desierto);
	public static final Sprite PUERTA_SUPERIOR_IZQUIERDA = new Sprite(32, 7, 0, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_INTERMEDIA_IZQUIERDA = new Sprite(32, 7, 1, 0, HojaSprites.desierto);
	
	public static final Sprite PUERTA_SUPERIOR_DERECHA = new Sprite(32, 7, 0, 1, HojaSprites.desierto);
	public static final Sprite PUERTA_INTERMEDIA_DERECHA = new Sprite(32, 7, 1, 1, HojaSprites.desierto);
	
	public static final Sprite PUERTA_SUPERIOR = new Sprite(32, 8, 0, 0, HojaSprites.desierto);
			
	public static final Sprite PUERTA_INFERIOR = new Sprite(32, 1, 3, 0, HojaSprites.desierto);
	public static final Sprite OXIDO = new Sprite(32, 9, 0, 0, HojaSprites.desierto);
	//public static final Sprite PUERTA_SUPERIOR_CENTRAL = new Sprite(32, 0, 0, HojaSprites.desierto);
	public static final Sprite VENTANA_PARED = new Sprite(32, 2, 3, 0, HojaSprites.desierto);
	public static final Sprite PARED_SUPERIOR = new Sprite(32, 3, 3, 0, HojaSprites.desierto);
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
	
	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja){
		this.lado=lado;
		
		pixeles= new int[lado* lado];
		
		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja=hoja;
		cargarSprite(version);
	}
	
	private void cargarSprite(int version){
		if(version == 0){
			cargaNormal();
		}else{
			cargaManipulada(version);
		}
	}
	
	private void cargaNormal(){
		for(int y=0; y < lado; y++){
			for(int x=0; x< lado; x++){
				pixeles[x + y * lado]=hoja.pixeles[ ( x + this.x) + ( y + this.y ) * hoja.obenerAncho()];
			}
		}
	}
	
	private void cargaManipulada(final int version){
		int[] pixelesTemporales;
		pixelesTemporales= iniciarPixelesTemporales();
		switch (version) {
		case 1:
			invertirX(pixelesTemporales);
			break;
		case 2:
			invertirY(pixelesTemporales);
			break;
		case 3:
			invertirXY(pixelesTemporales);
			break;
		case 4:
			rotar90Izquierda(pixelesTemporales);
			break;
		case 5:
			rotar90Derecha(pixelesTemporales);
			break;
		case 6:
			rotarIzquierda90InvertirY(pixelesTemporales);
			break;
		case 7:
			rotarDerecha90InvertirY(pixelesTemporales); 
			break;
		default:
			cargaNormal();
		}
	}
	
	private int[] iniciarPixelesTemporales(){
		int[] pixelesTemporales=new int[lado*lado];
		
		for(int y=0; y < lado; y++){
			for(int x=0; x< lado; x++){
				pixelesTemporales[x + y * lado] = hoja.pixeles[ ( x + this.x) + ( y + this.y ) * hoja.obenerAncho()];
			}
		}
		return pixelesTemporales;
	}
	
	//1
	private void invertirX(int[] pixelesTemporales){
		int i=0;
		for(int y = 0; y < lado; y++){
			for(int x = lado-1; x > -1; x--){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
	
	//2
	private void invertirY(int[] pixelesTemporales){
		int i=0;
		for(int y = lado-1; y > -1; y--){
			for(int x = 0; x < lado; x++){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
	
	//3
	private void invertirXY(int[] pixelesTemporales){
		for(int i = 0; i < pixeles.length; i++){
			pixeles[i]=pixelesTemporales[(pixelesTemporales.length-1)-i];
		}
	}
	
	//4
	private void rotar90Izquierda(int[] pixelesTemporales){
		int i=0;
		for(int x = lado-1; x > -1; x--){
			for(int y = 0; y < lado; y++){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
	
	//5
	private void rotar90Derecha(int[] pixelesTemporales){
		int i = 0;
		for(int x = 0; x < lado; x++){
			for(int y = lado; y > -1; y--){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
	
	//6
	private void rotarIzquierda90InvertirY(int[] pixelesTemporales){
		int i=0;
		for(int x = 0; x < lado; x++){
			for(int y = 0; y < lado; y++){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
	
	//7
	private void rotarDerecha90InvertirY(int[] pixelesTemporales){
		int i=0;
		for(int x = lado-1; x > -1; x--){
			for(int y = lado-1; y > -1; y--){
				pixeles[i]=pixelesTemporales[x + y *lado];
				i++;
			}
		}
	}
}