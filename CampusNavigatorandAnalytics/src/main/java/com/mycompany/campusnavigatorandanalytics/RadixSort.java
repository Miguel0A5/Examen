package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class RadixSort implements Ordenamiento {
    @Override
    public void sort(List<Estudiante> list, String campo) {
        if (!campo.equalsIgnoreCase("id")) {
            System.out.println("Radix solo se aplica al campo id.");
            return;
        }
        int max = list.stream().mapToInt(Estudiante::getId).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(list, exp);
        }
    }

    private void countingSort(List<Estudiante> list, int exp) {
        int n = list.size();
        List<Estudiante> output = new ArrayList<>(Collections.nCopies(n, (Estudiante) null));
        int[] count = new int[10];

        for (Estudiante e : list) count[(e.getId() / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            Estudiante e = list.get(i);
            int idx = (e.getId() / exp) % 10;
            output.set(count[idx] - 1, e);
            count[idx]--;
        }
        for (int i = 0; i < n; i++) list.set(i, output.get(i));
    }
}
