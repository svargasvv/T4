package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.data_structures.*;
import model.util.Sort;
import model.vo.VOMovingViolation;
import view.MovingViolationsManagerView;

@SuppressWarnings("unused")
public class Controller {

	private MovingViolationsManagerView view;

	// TODO Definir las estructuras de datos para cargar las infracciones del
	// periodo definido
	private LinkedList<VOMovingViolation> infracciones;

	// Muestra obtenida de los datos cargados
	Comparable<VOMovingViolation>[] muestra;

	// Copia de la muestra de datos a ordenar
	Comparable<VOMovingViolation>[] muestraCopia;
	SimpleDateFormat sdf;
	public Controller() {
		view = new MovingViolationsManagerView();
		infracciones = new LinkedList<VOMovingViolation>();
		sdf= new SimpleDateFormat ("yyyy-MM-dd HH:mm");
		// TODO inicializar las estructuras de datos para la carga de informacion de
		// archivos
	}

	/**
	 * Leer los datos de las infracciones de los archivos. Cada infraccion debe ser
	 * Comparable para ser usada en los ordenamientos. Todas infracciones
	 * (MovingViolation) deben almacenarse en una Estructura de Datos (en el mismo
	 * orden como estan los archivos) A partir de estos datos se obtendran muestras
	 * para evaluar los algoritmos de ordenamiento
	 * 
	 * @return numero de infracciones leidas
	 */
	public int loadMovingViolations() {
		return cargar("data/Moving_Violations_Issued_in_January_2018.csv")
				+ cargar("data/Moving_Violations_Issued_in_February_2018.csv")
				+ cargar("data/Moving_Violations_Issued_in_March_2018.csv");
	}

	public int cargar(String pRuta) {
		int counter = 0;

		File archivo = new File(pRuta);
		try {

			Scanner sc = new Scanner(archivo);

			sc.nextLine();
			while (sc.hasNext()) {
				counter++;
				String actual = sc.nextLine();

				actual = actual.replace(",,,", ",-1,-1,");
				actual = actual.replace(",,", ",-1,");
				actual = actual.replace(", ", "_");

				StringTokenizer st = new StringTokenizer(actual, ",");

				ArrayList<String> datosActual = new ArrayList<String>();

				while (st.hasMoreTokens()) {

					datosActual.add(st.nextToken());

				}
				String x[]= datosActual.get(13).split("T");
				String fecha= x[0]+" "+ x[1];
				infracciones.add(new VOMovingViolation(datosActual.get(0), datosActual.get(2), fecha,
						datosActual.get(9), datosActual.get(12), datosActual.get(15),sdf));

			}
			archivo = null;
			sc.reset();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return counter;

	}

	/**
	 * Generar una muestra aleatoria de tamaNo n de los datos leidos. Los datos de
	 * la muestra se obtienen de las infracciones guardadas en la Estructura de
	 * Datos.
	 * 
	 * @param n tamaNo de la muestra, n > 0
	 * @return muestra generada
	 */
	public Comparable<VOMovingViolation>[] generarMuestra(int n) {
		if (n < 0 || n > 240000) {
			return null;
		}
		muestra = new Comparable[n];
		int counter = 0;
		Random numero = new Random();
		int aleatorio = 0;
		while (counter != n) {
			
			if (counter%200==0)
			{
				
				numero= new Random(System.currentTimeMillis() );
			}
			aleatorio = numero.nextInt(n);
			
			System.out.println(aleatorio+ "Numero");
			muestra[counter] = infracciones.getElement(aleatorio);
			counter++;
		}
		// TODO Llenar la muestra aleatoria con los datos guardados en la estructura de
		// datos

		return muestra;

	}

	/**
	 * Generar una copia de una muestra. Se genera un nuevo arreglo con los mismos
	 * elementos.
	 * 
	 * @param muestra - datos de la muestra original
	 * @return copia de la muestra
	 */
	public Comparable<VOMovingViolation>[] obtenerCopia(Comparable<VOMovingViolation>[] muestra) {
		Comparable<VOMovingViolation>[] copia = new Comparable[muestra.length];
		for (int i = 0; i < muestra.length; i++) {
			copia[i] = muestra[i];
		}
		return copia;
	}

	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public void ordenarShellSort(Comparable<VOMovingViolation>[] datos) {

		Sort.ordenarShellSort(datos);
	}

	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public void ordenarMergeSort(Comparable<VOMovingViolation>[] datos) {

		Sort.ordenarMergeSort(datos);
	}

	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public void ordenarQuickSort(Comparable<VOMovingViolation>[] datos) {

		Sort.ordenarQuickSort(datos);
	}

	/**
	 * Invertir una muestra de datos (in place). datos[0] y datos[N-1] se
	 * intercambian, datos[1] y datos[N-2] se intercambian, datos[2] y datos[N-3] se
	 * intercambian, ...
	 * 
	 * @param datos - conjunto de datos a invertir (inicio) y conjunto de datos
	 *              invertidos (final)
	 */
	public void invertirMuestra(Comparable[] datos) {
		int n = datos.length;
		int counter = 0;
		while (counter != n / 2) {

			Comparable temporal = datos[counter];
			datos[counter] = datos[n - counter - 1];
			datos[n - counter - 1] = temporal;
			counter++;

		}
		// TODO implementar
	}

	public void run() {
		long startTime;
		long endTime;
		long duration;

		int nDatos = 0;
		int nMuestra = 0;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while (!fin) {
			view.printMenu();

			int option = sc.nextInt();

			switch (option) {
			case 1:
				// Cargar infracciones
				nDatos = this.loadMovingViolations();
				view.printMensage("Numero infracciones cargadas:" + nDatos);
				break;

			case 2:
				// Generar muestra de infracciones a ordenar
				view.printMensage("Dar tamaNo de la muestra: ");
				nMuestra = sc.nextInt();
				muestra = this.generarMuestra(nMuestra);
				view.printMensage("Muestra generada");
				break;

			case 3:
				// Mostrar los datos de la muestra actual (original)
				if (nMuestra > 0 && muestra != null && muestra.length == nMuestra) {
					view.printDatosMuestra(nMuestra, muestra);
				} else {
					view.printMensage("Muestra invalida");
				}
				break;

			case 4:
				// Aplicar ShellSort a una copia de la muestra
				if (nMuestra > 0 && muestra != null && muestra.length == nMuestra) {
					muestraCopia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.ordenarShellSort(muestraCopia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento ShellSort: " + duration + " milisegundos");
				} else {
					view.printMensage("Muestra invalida");
				}
				break;

			case 5:
				// Aplicar MergeSort a una copia de la muestra
				if (nMuestra > 0 && muestra != null && muestra.length == nMuestra) {
					muestraCopia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.ordenarMergeSort(muestraCopia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento MergeSort: " + duration + " milisegundos");
				} else {
					view.printMensage("Muestra invalida");
				}
				break;

			case 6:
				// Aplicar QuickSort a una copia de la muestra
				if (nMuestra > 0 && muestra != null && muestra.length == nMuestra) {
					muestraCopia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.ordenarQuickSort(muestraCopia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento QuickSort: " + duration + " milisegundos");
				} else {
					view.printMensage("Muestra invalida");
				}
				break;

			case 7:
				// Mostrar los datos de la muestra ordenada (muestra copia)
				if (nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra) {
					view.printDatosMuestra(nMuestra, muestraCopia);
				} else {
					view.printMensage("Muestra Ordenada invalida");
				}
				break;

			case 8:
				// Una muestra ordenada se convierte en la muestra a ordenar
				if (nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra) {
					muestra = muestraCopia;
					view.printMensage("La muestra ordenada (copia) es ahora la muestra de datos a ordenar");
				}
				break;

			case 9:
				// Invertir la muestra a ordenar
				if (nMuestra > 0 && muestra != null && muestra.length == nMuestra) {
					this.invertirMuestra(muestra);
					view.printMensage("La muestra de datos a ordenar fue invertida");
				} else {
					view.printMensage("Muestra invalida");
				}

				break;

			case 10:
				fin = true;
				sc.close();
				break;
			}
		}
	}

}
