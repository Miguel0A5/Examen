package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class HeapSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        PriorityQueue<Estudiante> pq = new PriorityQueue<>((a,b)->compare(a,b,campo));
        pq.addAll(list);
        list.clear();
        while (!pq.isEmpty()) list.add(pq.poll());
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
