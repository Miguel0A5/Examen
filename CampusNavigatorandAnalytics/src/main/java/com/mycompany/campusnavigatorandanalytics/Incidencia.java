package com.mycompany.campusnavigatorandanalytics;


public class Incidencia {
    private int id;
    private String titulo;
    private int prioridad; // 1 = más urgente
    private long timestamp; // guardado en milisegundos

    public Incidencia(int id, String titulo, int prioridad) {
        this.id = id;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getPrioridad() { return prioridad; }
    public long getTimestamp() { return timestamp; }

    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Prioridad: " + prioridad +
               ", Timestamp: " + timestamp;
    }
}
