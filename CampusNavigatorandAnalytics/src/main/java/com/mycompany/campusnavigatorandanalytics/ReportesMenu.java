/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class ReportesMenu {

    private final ManejoEstudiantes manejoEst;
    private final MinHeapIncidencias heap;
    private final CampusGrafo grafo;

    public ReportesMenu(ManejoEstudiantes manejoEst,
                        MinHeapIncidencias heap,
                        CampusGrafo grafo) {
        this.manejoEst = manejoEst;
        this.heap = heap;
        this.grafo = grafo;
    }

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ REPORTES ---");
            System.out.println("1.- Comparar tiempos de ordenamiento (Estudiantes)");
            System.out.println("2.- Métricas de rutas (Campus)");
            System.out.println("3.- Tamaños de estructuras");
            System.out.println("4.- Volver");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        compararTiempos();
                        break;
                    case 2:
                        metricasRutas();
                        break;
                    case 3:
                        tamanosEstructuras();
                        break;
                    case 4:
                        System.out.println("Regresando...");
                        break;
                    default:
                        System.out.println("Valor no válido");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida.");
            }
        } while (opcion != 4);
    }

    private void compararTiempos() {
        List<Estudiante> estudiantes = manejoEst.getTodos();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados para comparar tiempos.");
            return;
        }
        Map<String, Double> tiempos = Reportes.compararTiemposOrdenamiento(estudiantes);
        tiempos.forEach((alg, ms) -> System.out.printf("%s: %.3f ms%n", alg, ms));
    }

    private void metricasRutas() {
        Map<String, Object> m = Reportes.metricasRutas(grafo);
        if (m.isEmpty()) {
            System.out.println("El grafo está vacío.");
            return;
        }
        System.out.println("Número de edificios: " + m.get("vertices"));
        System.out.println("Número de pasillos: " + m.get("aristas"));
        System.out.println("Costo total MST: " + m.get("costoMST"));
    }

    private void tamanosEstructuras() {
        Map<String, Integer> t = Reportes.tamanosEstructuras(manejoEst, heap, grafo);
        System.out.println("Total de estudiantes: " + t.get("estudiantes"));
        System.out.println("Total de incidencias activas: " + t.get("incidencias"));
        System.out.println("Total de edificios: " + t.get("edificios"));
    }
}
