
package com.mycompany.campusnavigatorandanalytics;
import java.util.*;


public class MenuPrincipal {

    public void ejecutar() {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ Principal ---");
            System.out.println("1.- Campus = Cargar grafo / BFS / DFS / Dijkstra / MST");
            System.out.println("2.- Estudiantes = Alta / Baja / Buscar / Ordenar (elige algoritmo + campo)");
            System.out.println("3.- Incidencias = Insertar / Cambiar prioridad / Atender siguiente / Listar");
            System.out.println("4.- Reportes = Tiempos de ordenamiento y comparativa / Métricas de rutas / Tamaños de estructuras");
            System.out.println("5.- Salir");

            try {
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        Campus campus1 = new Campus();
                        campus1.ejecutar();
                        break;

                    case 2:
                        EstudiantesMenu em1 = new EstudiantesMenu();
                        em1.ejecutar();
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        System.out.println("Hasta la próxima....");
                        break;

                    default:
                        System.out.println("Valor no válido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 5);

        entrada.close();
    }
}
