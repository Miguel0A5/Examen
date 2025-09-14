package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class SelectionSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (compare(list.get(j), list.get(minIdx), campo) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(list, i, minIdx);
        }
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
