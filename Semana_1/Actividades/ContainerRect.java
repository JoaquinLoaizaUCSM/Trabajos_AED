
public class ContainerRect 
{
	private Rectangulo[] rectangulos;
	private double[] distancias;
	private double[] areas;
	private int n;
	private int contador;
	private static int numrec = 0; 
	
	public ContainerRect(int n)
	{
		this.n = n;
		this.rectangulos = new Rectangulo[n];
		this.distancias = new double[n];
		this.areas = new double[n];
		this.contador = 0;
		
	}
	
	public int getNumRectangulo()
	{
		return numrec;
	}
	
	public boolean  addRectangulo(Rectangulo rect)
	{
		if (numrec < n)
		{ 

			rectangulos[numrec] = rect;
			areas[numrec] = rect.calculoArea();
			distancias[numrec] = calcularDistancia(rect);
			numrec++; 
			return true;
		}
		System.out.println("El contenedor esta Lleno");
		return false;
	}
	
	private double calcularDistancia(Rectangulo rect)
	{
		Coordenada esquina1 = rect.getEsquina1();
		Coordenada esquina2 = rect.getEsquina2();
		return esquina1.distancia(esquina2);
					
	}
	
	public void MostrarInfo()
	{
		for (int i = 0; i < numrec; i++) 
		{
            System.out.println("Rectangulo " + (i + 1) + ": " + rectangulos[i]);
            System.out.println("Distancia Euclidiana: " + distancias[i]);
            System.out.println("Area: " + areas[i]);

        }
	}
	
	@Override
	public String toString() 
	{
	    
	    StringBuilder sb = new StringBuilder();

	    sb.append(String.format("%-12s %-30s %-12s %-8s\n", "|Rectangulo|", "|Coordenadas|", "|Distancia|", "|Area|")); 
	    // Metodo Raro de GPT:
	    // %-12s: Alinea a la izquierda una cadena de 12 caracteres de ancho
	    // %-30s: Alinea a la izquierda una cadena de 30 caracteres de ancho
	    // %-12s: Alinea a la izquierda una cadena de 12 caracteres de ancho
	    // %-8s: Alinea a la izquierda una cadena de 8 caracteres de ancho
	    
	    for (int i = 0; i < numrec; i++) 
	    {
	        String coordenadas = rectangulos[i].toString();
	        
	        String distancia = String.format("%.3f", distancias[i]);
	        String area = String.format("%.2f", areas[i]);
	        
	        sb.append(String.format("%-12d %-30s %-12s %-8s\n", (i + 1), coordenadas, distancia, area));
	    }

	    return sb.toString();
	}

	
}
