package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class Reportes {

    /** 
     * Compara los tiempos de los algoritmos de ordenamiento
     * y devuelve un mapa algoritmo -> tiempo en milisegundos.
     */
    public static Map<String, Double> compararTiemposOrdenamiento(List<Estudiante> estudiantes) {
        Map<String, Double> resultados = new LinkedHashMap<>();
        if (estudiantes.isEmpty()) return resultados;

        String[] algoritmos = {"insertion", "selection", "shell", "quick", "merge", "heap", "radix"};
        for (String alg : algoritmos) {
            Ordenamiento strategy = switch (alg) {
                case "insertion" -> new InsertionSort();
                case "selection" -> new SelectionSort();
                case "shell" -> new ShellSort();
                case "quick" -> new QuickSort();
                case "merge" -> new MergeSort();
                case "heap" -> new HeapSort();
                case "radix" -> new RadixSort();
                default -> null;
            };
            if (strategy != null) {
                List<Estudiante> copia = new ArrayList<>(estudiantes);
                long start = System.nanoTime();
                strategy.sort(copia, "id");
                long end = System.nanoTime();
                double ms = (end - start) / 1_000_000.0;
                resultados.put(alg, ms);
            }
        }
        return resultados;
    }

    /**
     * Obtiene métricas del grafo del campus:
     * número de edificios, pasillos y costo del MST.
     */
    public static Map<String, Object> metricasRutas(CampusGrafo grafo) {
        Map<String, Object> m = new LinkedHashMap<>();
        if (grafo.isEmpty()) return m;

        int vertices = grafo.getVertices().size();
        int aristas = grafo.getAdjacencyList().values().stream()
                        .mapToInt(List::size).sum() / 2;
        double costoMST = AlgoritmosdeCampus.kruskalCosto(grafo);

        m.put("vertices", vertices);
        m.put("aristas", aristas);
        m.put("costoMST", costoMST);
        return m;
    }

    /**
     * Devuelve el tamaño de las estructuras principales.
     */
    public static Map<String, Integer> tamanosEstructuras(ManejoEstudiantes manejoEst,
                                                          MinHeapIncidencias heap,
                                                          CampusGrafo grafo) {
        Map<String, Integer> t = new LinkedHashMap<>();
        t.put("estudiantes", manejoEst.getTodos().size());
        t.put("incidencias", heap.getAll().size());
        t.put("edificios", grafo.getVertices().size());
        return t;
    }
}
