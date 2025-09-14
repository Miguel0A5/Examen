/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class InsertionSort implements Ordenamiento {

    @Override
    public void sort(List<Estudiante> list, String campo) {
        for (int i = 1; i < list.size(); i++) {
            Estudiante key = list.get(i);
            int j = i - 1;
            while (j >= 0 && compare(list.get(j), key, campo) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
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
