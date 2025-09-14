package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class IncidenciasMenu {

    private final MinHeapIncidencias heap;

    // Recibe el heap compartido
    public IncidenciasMenu(MinHeapIncidencias heap) {
        this.heap = heap;
    }

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ INCIDENCIAS ---");
            System.out.println("1.- Insertar incidencia");
            System.out.println("2.- Cambiar prioridad");
            System.out.println("3.- Atender siguiente (pop)");
            System.out.println("4.- Ver siguiente (peek)");
            System.out.println("5.- Listar todas");
            System.out.println("6.- Volver");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        insertar(sc);
                        break;
                    case 2:
                        cambiarPrioridad(sc);
                        break;
                    case 3:
                        atender();
                        break;
                    case 4:
                        verSiguiente();
                        break;
                    case 5:
                        listar();
                        break;
                    case 6:
                        System.out.println("Regresando...");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private void insertar(Scanner sc) {
        try {
            System.out.println("Ingrese la ID únicamente con números:");
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.println("Ingrese un número para establecer la prioridad (1 = Alta, 10 = Baja):");
            System.out.print("Prioridad: ");
            int prioridad = Integer.parseInt(sc.nextLine());

            heap.push(new Incidencia(id, titulo, prioridad));
            System.out.println("Incidencia agregada.");
        } catch (NumberFormatException e) {
            System.out.println("Datos inválidos. Intente de nuevo.");
        }
    }

    private void cambiarPrioridad(Scanner sc) {
        try {
            System.out.print("ID de incidencia: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nueva prioridad: ");
            int nueva = Integer.parseInt(sc.nextLine());

            boolean ok = heap.changePriority(id, nueva);
            System.out.println(ok ? "Prioridad actualizada." : "ID no encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Datos inválidos.");
        }
    }

    private void atender() {
        Incidencia inc = heap.pop();
        System.out.println((inc != null) ? "Atendiendo: " + inc : "No hay incidencias.");
    }

    private void verSiguiente() {
        Incidencia inc = heap.peek();
        System.out.println((inc != null) ? "Siguiente: " + inc : "No hay incidencias.");
    }

    private void listar() {
        List<Incidencia> lista = heap.getAll();
        if (lista.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}

