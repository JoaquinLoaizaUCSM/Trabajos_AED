package Trabajos_AED.Semana_1.Actividades;


public class ContainerRect 
{
	private Rectangulo[] rectangulos;
	private double[] distancias;
	private double[] areas;
	private int n;
	private static int numrec = 0; 
	
	public ContainerRect(int n)
	{
		this.n = n;
		this.rectangulos = new Rectangulo[n];
		this.distancias = new double[n];
		this.areas = new double[n];
		
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
	public String toString() {
		// Usa un StringBuilder para construir la salida formateada
		StringBuilder sb = new StringBuilder();

		// Agrega el encabezado de la tabla con alineación estructurada
		sb.append(String.format("%-6s %-35s %-10s %-10s\n",
				"Index", "Coordenadas", "Distancia", "Area"));

		// Agrega una línea divisoria para mejorar la legibilidad
		sb.append("---------------------------------------------------------------\n");

		// Recorre los rectángulos almacenados y agrega sus datos a la tabla
		for (int i = 0; i < numrec; i++) {
			sb.append(String.format("%-6d %-35s %-10.2f %-10.2f\n",
					(i + 1),  // Muestra el índice del rectángulo (desde 1 en lugar de 0 para mayor claridad)
					rectangulos[i], // Usa el método toString() de Rectangulo para mostrar las coordenadas
					distancias[i],  // Muestra la distancia con dos decimales
					areas[i]));  // Muestra el área con dos decimales
		}

		// Retorna la cadena con la tabla formateada
		return sb.toString();
	}
	
}
