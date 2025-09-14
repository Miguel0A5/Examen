/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusnavigatorandanalytics;


public class Estudiante {
    private int id;
    private String nombre;
    private String carrera;
    private double promedio;

    public Estudiante(int id, String nombre, String carrera, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public double getPromedio() { return promedio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setPromedio(double promedio) { this.promedio = promedio; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Carrera: " + carrera + ", Promedio: " + promedio;
    }
}
