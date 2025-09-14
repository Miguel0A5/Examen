package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class QuickSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        quickSort(list, 0, list.size() - 1, campo);
    }

    private void quickSort(List<Estudiante> list, int low, int high, String campo) {
        if (low < high) {
            int pi = partition(list, low, high, campo);
            quickSort(list, low, pi - 1, campo);
            quickSort(list, pi + 1, high, campo);
        }
    }

    private int partition(List<Estudiante> list, int low, int high, String campo) {
        Estudiante pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot, campo) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    private int compare(Estudiante a, Estudiante b, String campo) {
        return switch (campo.toLowerCase()) {
            case "id" -> Integer.compare(a.getId(), b.getId());
            case "nombre" -> a.getNombre().compareToIgnoreCase(b.getNombre());
            case "promedio" -> Double.compare(a.getPromedio(), b.getPromedio());
            default -> 0;
        };
    }
}
