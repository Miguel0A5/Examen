package com.mycompany.campusnavigatorandanalytics;


import java.util.*;

public class CampusGrafo {
    private final Map<String, List<AristasCampusGrafo>> adj = new HashMap<>();

    // --- Operaciones básicas ---
    public void addVertex(String v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addAristasCampusGrafo(String origen, String destino, double peso) {
        addVertex(origen);
        addVertex(destino);
        adj.get(origen).add(new AristasCampusGrafo(destino, peso));
        adj.get(destino).add(new AristasCampusGrafo(origen, peso)); // no dirigido
    }

    public Map<String, List<AristasCampusGrafo>> getAdjacencyList() {
        return adj;
    }

    public Set<String> getVertices() {
        return adj.keySet();
    }

    public boolean isEmpty() {
        return adj.isEmpty();
    }

    // Matriz de adyacencia (opcional)
    public double[][] toAdjacencyMatrix() {
        int n = adj.size();
        List<String> verts = new ArrayList<>(adj.keySet());
        double[][] matrix = new double[n][n];
        for (double[] row : matrix) Arrays.fill(row, Double.POSITIVE_INFINITY);

        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
            for (AristasCampusGrafo e : adj.get(verts.get(i))) {
                int j = verts.indexOf(e.destino);
                matrix[i][j] = e.peso;
            }
        }
        return matrix;
    }
}
