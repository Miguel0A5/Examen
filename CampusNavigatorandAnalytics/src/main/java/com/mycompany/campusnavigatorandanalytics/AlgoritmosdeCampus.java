package com.mycompany.campusnavigatorandanalytics;
import java.util.*;

public class AlgoritmosdeCampus {

    // --- BFS: recorrido en anchura ---
    public static List<String> bfs(CampusGrafo g, String start) {
        List<String> order = new ArrayList<>();
        if (!g.getVertices().contains(start)) return order; // valida existencia
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            String u = q.poll();
            order.add(u);
            // ✅ Evita NullPointer si u no tiene vecinos
            for (AristasCampusGrafo e : g.getAdjacencyList()
                                         .getOrDefault(u, Collections.emptyList())) {
                if (visited.add(e.destino)) q.add(e.destino);
            }
        }
        return order;
    }

    // --- DFS: recorrido en profundidad ---
    public static List<String> dfs(CampusGrafo g, String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsRec(g, start, visited, order);
        return order;
    }

    // método auxiliar recursivo para DFS
    private static void dfsRec(CampusGrafo g, String v, Set<String> visited, List<String> order) {
        if (!visited.add(v)) return;
        order.add(v);
        // ✅ Evita NullPointer si v no existe o no tiene vecinos
        for (AristasCampusGrafo e : g.getAdjacencyList()
                                     .getOrDefault(v, Collections.emptyList())) {
            dfsRec(g, e.destino, visited, order);
        }
    }

    // --- Dijkstra: ruta más corta entre dos edificios ---
    public static void dijkstra(CampusGrafo g, String origen, String destino) {
        if (!g.getVertices().contains(origen) || !g.getVertices().contains(destino)) {
            System.out.println("Uno o ambos vértices no existen.");
            return;
        }

        // creación de estructuras de distancias y predecesores
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String v : g.getVertices()) dist.put(v, Double.POSITIVE_INFINITY);
        dist.put(origen, 0.0);

        // cola de prioridad por menor distancia (compatible Java 8+)
        PriorityQueue<AbstractMap.SimpleEntry<String, Double>> pq =
            new PriorityQueue<>(Comparator.comparing(AbstractMap.SimpleEntry::getValue));
        pq.add(new AbstractMap.SimpleEntry<>(origen, 0.0));

        // lógica principal de relajación de aristas
        while (!pq.isEmpty()) {
            String u = pq.poll().getKey();
            // ✅ Evita NullPointer si u no tiene vecinos
            for (AristasCampusGrafo e : g.getAdjacencyList()
                                         .getOrDefault(u, Collections.emptyList())) {
                double nd = dist.get(u) + e.peso;
                if (nd < dist.get(e.destino)) {
                    dist.put(e.destino, nd);
                    prev.put(e.destino, u);
                    pq.add(new AbstractMap.SimpleEntry<>(e.destino, nd));
                }
            }
        }

        // validación de ruta existente
        if (dist.get(destino) == Double.POSITIVE_INFINITY) {
            System.out.println("No hay ruta entre " + origen + " y " + destino);
            return;
        }

        // reconstrucción del camino óptimo
        List<String> path = new ArrayList<>();
        for (String at = destino; at != null; at = prev.get(at)) {
            path.add(at);
            if (at.equals(origen)) break;
        }
        Collections.reverse(path);

        System.out.println("Ruta más corta: " + path);
        System.out.println("Costo total: " + dist.get(destino));
    }

    // --- Kruskal: árbol de expansión mínima (MST) ---
    public static void kruskal(CampusGrafo g) {
        // creación de lista de aristas sin duplicados
        List<AristasCampusGrafoMST> AristasCampusGrafos = new ArrayList<>();
        for (String u : g.getVertices()) {
            for (AristasCampusGrafo e : g.getAdjacencyList()
                                         .getOrDefault(u, Collections.emptyList())) {
                if (u.compareTo(e.destino) < 0) { // evita doble registro
                    AristasCampusGrafos.add(new AristasCampusGrafoMST(u, e.destino, e.peso));
                }
            }
        }
        AristasCampusGrafos.sort(Comparator.comparingDouble(a -> a.peso)); // orden por peso

        // estructura Union-Find para controlar ciclos
        UnionFind uf = new UnionFind(g.getVertices());
        double totalCost = 0;
        List<AristasCampusGrafoMST> mst = new ArrayList<>();

        // selección de aristas válidas para MST
        for (AristasCampusGrafoMST e : AristasCampusGrafos) {
            if (uf.union(e.u, e.v)) {
                mst.add(e);
                totalCost += e.peso;
            }
        }

        // verificación de conectividad
        if (mst.size() < g.getVertices().size() - 1) {
            System.out.println("El grafo es disconexo; MST parcial.");
        }
        System.out.println("Aristas del MST:");
        mst.forEach(System.out::println);
        System.out.println("Costo total: " + totalCost);
    }

    // --- Clases internas para Kruskal ---
    private static class AristasCampusGrafoMST {
        String u, v;
        double peso;
        AristasCampusGrafoMST(String u, String v, double p) { this.u=u; this.v=v; this.peso=p; }
        public String toString() { return u + " - " + v + " : " + peso; }
    }

    // estructura Union-Find: detecta ciclos en Kruskal
    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();
        UnionFind(Set<String> verts) { for (String v : verts) parent.put(v, v); }
        String find(String x) {
            if (!parent.get(x).equals(x)) parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }
        boolean union(String a, String b) {
            String ra = find(a), rb = find(b);
            if (ra.equals(rb)) return false; // ya conectados
            parent.put(ra, rb);
            return true;
        }
    }
}

