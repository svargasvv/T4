package model.util;

public class Sort {

	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public static void ordenarShellSort(Comparable[] datos) {
		int n = datos.length;

		// 3x+1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && less(datos[j], datos[j - h]); j -= h) {
					exchange(datos, j, j - h);
				}
			}

			h /= 3;
		}

		// TODO implementar el algoritmo ShellSort
	}

	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public static void ordenarMergeSort(Comparable[] datos) {
		Comparable[] aux = new Comparable[datos.length];
		sortMerge(datos, aux, 0, datos.length - 1);
		assert isSorted(datos);
		// TODO implementar el algoritmo MergeSort
	}

	private static void sortMerge(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sortMerge(a, aux, lo, mid);
		sortMerge(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// merge back to a[]
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}

		// postcondition: a[lo .. hi] is sorted
		assert isSorted(a, lo, hi);
	}

	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * 
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos
	 *              ordenados (final)
	 */
	public static void ordenarQuickSort(Comparable[] datos) {
		
		int n = datos.length;
        for (int i = 0; i < n; i++) {
            int r = i + ((int)Math.random()*((n-1)-i+1)+i);     // between i and n-1
            Comparable temp = datos[i];
            datos[i] = datos[r];
            datos[r] = temp;
        }
        sortQuick(datos, 0, datos.length - 1);
        assert isSorted(datos);
		
		// TODO implementar el algoritmo QuickSort
	}
	private static void sortQuick(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sortQuick(a, lo, j-1);
        sortQuick(a, j+1, hi);
        assert isSorted(a, lo, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exchange(a, i, j);
        }

        // put partitioning item v at a[j]
        exchange(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * 
	 * @param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso
	 *         contrario.
	 */
	private static boolean less(Comparable v, Comparable w) {
		if (v.compareTo(w) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * Intercambiar los datos de las posicion i y j
	 * 
	 * @param datos contenedor de datos
	 * @param i     posicion del 1er elemento a intercambiar
	 * @param j     posicion del 2o elemento a intercambiar
	 */
	private static void exchange(Comparable[] datos, int i, int j) {
		Comparable temporal = datos[i];
		datos[i] = datos[j];
		datos[j] = temporal;
		// TODO implementar
	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

}
