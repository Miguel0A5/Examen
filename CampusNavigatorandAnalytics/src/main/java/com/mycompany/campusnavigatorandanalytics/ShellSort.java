package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class ShellSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        int n = list.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Estudiante temp = list.get(i);
                int j = i;
                while (j >= gap && compare(list.get(j - gap), temp, campo) > 0) {
                    list.set(j, list.get(j - gap));
                    j -= gap;
                }
                list.set(j, temp);
            }
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

