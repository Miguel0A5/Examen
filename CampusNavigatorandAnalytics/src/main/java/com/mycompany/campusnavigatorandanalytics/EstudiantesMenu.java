package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class EstudiantesMenu {
    
    private final ManejoEstudiantes manejo1;
    // Recibe la instancia compartida
    public EstudiantesMenu(ManejoEstudiantes manejo1) {
        this.manejo1 = manejo1;
    }

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n--- MENÚ ESTUDIANTES ---");
            System.out.println("1.- Alta de estudiante");
            System.out.println("2.- Baja de estudiante");
            System.out.println("3.- Búsqueda por ID");
            System.out.println("4.- Búsqueda por nombre");
            System.out.println("5.- Ordenar (elige algoritmo + campo)");
            System.out.println("6.- Volver");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        alta(sc);
                        break;
                    case 2:
                        baja(sc);
                        break;
                    case 3:
                        buscarPorId(sc);
                        break;
                    case 4:
                        buscarPorNombre(sc);
                        break;
                    case 5:
                        System.out.println("Escriba literalmente lo que se pone entre los paréntesis para ejecutar la opción");
                        ordenar(sc);
                        break;
                    case 6:
                        System.out.println("Regresando...");
                        break;
                    default:
                        System.out.println("Valor no válido");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private void alta(Scanner sc) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Carrera: ");
            String carrera = sc.nextLine();
            System.out.print("Promedio: ");
            double promedio = Double.parseDouble(sc.nextLine());

            manejo1.alta(new Estudiante(id, nombre, carrera, promedio));
            System.out.println("Estudiante agregado.");
        } catch (NumberFormatException e) {
            System.out.println("Datos inválidos.");
        }
    }

    private void baja(Scanner sc) {
        System.out.print("ID a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        manejo1.baja(id);
    }

    private void buscarPorId(Scanner sc) {
        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        Estudiante e = manejo1.buscarPorId(id);
        System.out.println((e != null) ? e : "No encontrado.");
    }

    private void buscarPorNombre(Scanner sc) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine();
        List<Estudiante> encontrados = manejo1.buscarPorNombre(nombre);
        if (encontrados.isEmpty()) System.out.println("No encontrado.");
        else encontrados.forEach(System.out::println);
    }

    private void ordenar(Scanner sc) {
        System.out.println("Campo a ordenar (id / nombre / promedio): ");
        String campo = sc.nextLine();
        System.out.println("Algoritmo (insertion / selection / shell / quick / merge / heap / radix): ");
        String alg = sc.nextLine().toLowerCase();

        Ordenamiento strategy = switch (alg) {
            case "insertion" -> new InsertionSort();
            case "selection" -> new SelectionSort();
            case "shell"     -> new ShellSort();
            case "quick"     -> new QuickSort();
            case "merge"     -> new MergeSort();
            case "heap"      -> new HeapSort();
            case "radix"     -> new RadixSort();
            default          -> null;
        };

        if (strategy == null) {
            System.out.println("Algoritmo no reconocido.");
            return;
        }
        manejo1.ordenar(strategy, campo);
    }
}
