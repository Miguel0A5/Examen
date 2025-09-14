/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class ManejoEstudiantes {
    private final AVLTree avl = new AVLTree();
    private final List<Estudiante> lista = new ArrayList<>();

    public void alta(Estudiante e) {
        avl.insert(e);
        lista.add(e);
    }

    public void baja(int id) {
        Estudiante e = avl.search(id);
        if (e != null) {
            avl.delete(id);
            lista.removeIf(s -> s.getId() == id);
            System.out.println("Estudiante eliminado.");
        } else {
            System.out.println("ID no encontrado.");
        }
    }

    public Estudiante buscarPorId(int id) {
        return avl.search(id);
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> result = new ArrayList<>();
        for (Estudiante e : lista) {
            if (e.getNombre().equalsIgnoreCase(nombre)) result.add(e);
        }
        return result;
    }

    public void ordenar(Ordenamiento strategy, String campo) {
        List<Estudiante> copia = new ArrayList<>(lista);
        strategy.sort(copia, campo);
        copia.forEach(System.out::println);
    }

    public List<Estudiante> getTodos() {
        return new ArrayList<>(lista);
    }
}
