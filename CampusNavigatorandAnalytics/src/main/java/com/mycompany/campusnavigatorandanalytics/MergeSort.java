package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class MergeSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        List<Estudiante> left = new ArrayList<>(list.subList(0, mid));
        List<Estudiante> right = new ArrayList<>(list.subList(mid, list.size()));
        sort(left, campo);
        sort(right, campo);
        merge(list, left, right, campo);
    }

    private void merge(List<Estudiante> list, List<Estudiante> left, List<Estudiante> right, String campo) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (compare(left.get(i), right.get(j), campo) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
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
