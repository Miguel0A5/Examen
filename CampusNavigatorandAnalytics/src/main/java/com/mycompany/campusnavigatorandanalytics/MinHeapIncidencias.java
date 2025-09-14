
package com.mycompany.campusnavigatorandanalytics;

import java.util.*;

public class MinHeapIncidencias {
    private final List<Incidencia> heap = new ArrayList<>();

    // O(log n)
    public void push(Incidencia inc) {
        heap.add(inc);
        siftUp(heap.size() - 1);
    }

    // O(log n)
    public Incidencia pop() {
        if (heap.isEmpty()) return null;
        Incidencia root = heap.get(0);
        Incidencia last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return root;
    }

    // O(1)
    public Incidencia peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // O(n)
    public void buildHeap(List<Incidencia> items) {
        heap.clear();
        heap.addAll(items);
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // O(log n)
    public boolean changePriority(int id, int nuevaPrioridad) {
        for (int i = 0; i < heap.size(); i++) {
            Incidencia inc = heap.get(i);
            if (inc.getId() == id) {
                inc.setPrioridad(nuevaPrioridad);
                // re-heapify según el nuevo valor
                siftUp(i);
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    public List<Incidencia> getAll() {
        return new ArrayList<>(heap);
    }

    // Métodos internos
    private void siftUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (heap.get(i).getPrioridad() < heap.get(p).getPrioridad()) {
                Collections.swap(heap, i, p);
                i = p;
            } else break;
        }
    }

    private void siftDown(int i) {
        int left, right, smallest;
        while ((left = leftChild(i)) < heap.size()) {
            right = rightChild(i);
            smallest = i;
            if (heap.get(left).getPrioridad() < heap.get(smallest).getPrioridad())
                smallest = left;
            if (right < heap.size() && heap.get(right).getPrioridad() < heap.get(smallest).getPrioridad())
                smallest = right;
            if (smallest != i) {
                Collections.swap(heap, i, smallest);
                i = smallest;
            } else break;
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
}

