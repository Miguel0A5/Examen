
package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class Campus {

    private CampusGrafo grafo = new CampusGrafo();

    public void ejecutar() {
        Scanner entrada = new Scanner(System.in);
        int opcion = -1;
        cargarGrafoDemo();
        do {
            System.out.println("\n--- MENÚ CAMPUS ---");
            System.out.println("Hay un ejemplo precargado para realizar las pruebas con EdificioA, B, C y D");
            System.out.println("1.- BFS");
            System.out.println("2.- DFS");
            System.out.println("3.- Dijkstra (ruta más corta)");
            System.out.println("4.- MST (Kruskal)");
            System.out.println("5.- Volver");

            try {
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        ejecutarBFS(entrada);
                        break;                      
                    case 2:
                        ejecutarDFS(entrada);
                        break;
                    case 3: 
                        ejecutarDijkstra(entrada);
                        break;
                    case 4:
                        AlgoritmosdeCampus.kruskal(grafo);
                        break;
                    case 5: 
                        System.out.println("Regresando...");
                        break;
                    default: 
                        System.out.println("Opción no válida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // --- Cargar un grafo de ejemplo ---
    private void cargarGrafoDemo() {
        grafo = new CampusGrafo();
        grafo.addAristasCampusGrafo("EdificioA", "EdificioB", 4);
        grafo.addAristasCampusGrafo("EdificioA", "EdificioC", 2);
        grafo.addAristasCampusGrafo("EdificioB", "EdificioC", 5);
        grafo.addAristasCampusGrafo("EdificioB", "EdificioD", 10);
        grafo.addAristasCampusGrafo("EdificioC", "EdificioD", 3);
        System.out.println("Grafo de ejemplo cargado.");
    }

    // --- Ejecutar BFS con validación ---
    private void ejecutarBFS(Scanner sc) {
        System.out.print("Edificio inicial: ");
        String start = sc.nextLine();
        List<String> recorrido = AlgoritmosdeCampus.bfs(grafo, start);

        if (recorrido.isEmpty()) {
            System.out.println("No se encontró el edificio '" + start +
                    "'. Edificios disponibles: " + grafo.getVertices());
        } else {
            System.out.println("Recorrido BFS: " + recorrido);
        }
    }

    // --- Ejecutar DFS con validación ---
    private void ejecutarDFS(Scanner sc) {
        System.out.print("Edificio inicial: ");
        String start = sc.nextLine();
        List<String> recorrido = AlgoritmosdeCampus.dfs(grafo, start);

        if (recorrido.isEmpty()) {
            System.out.println("No se encontró el edificio '" + start +
                    "'. Edificios disponibles: " + grafo.getVertices());
        } else {
            System.out.println("Recorrido DFS: " + recorrido);
        }
    }

    // --- Ejecutar Dijkstra con validación ---
    private void ejecutarDijkstra(Scanner sc) {
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();

        if (!grafo.getVertices().contains(origen) || !grafo.getVertices().contains(destino)) {
            System.out.println("Uno o ambos edificios no existen. Edificios disponibles: "
                    + grafo.getVertices());
            return;
        }
        AlgoritmosdeCampus.dijkstra(grafo, origen, destino);
    }
}

