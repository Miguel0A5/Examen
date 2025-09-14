
CampusNavigatorAndAnalytics – Módulo Campus
===========================================

Introducción
------------
Este proyecto en Java crea una aplicación de consola para simular la navegación en un campus universitario.
El objetivo es representar edificios y pasillos como un grafo ponderado, para poder:

- Recorrer el campus (BFS y DFS).
- Calcular rutas más cortas entre edificios (Dijkstra).
- Obtener un árbol de expansión mínima (Kruskal).

El código está organizado de forma modular y orientada a objetos, con un menú principal y un submenú especializado en el campus.

Estructura de menús
-------------------
Menú Principal (MenuPrincipal)
------------------------------
Este menú es la entrada de la aplicación. Ofrece cinco opciones:

1. Campus – Acceso al módulo de navegación en grafos.
2. Estudiantes – Alta, baja, búsqueda y ordenamiento (aún por implementar).
3. Incidencias – Gestión de incidencias con colas de prioridad (pendiente).
4. Reportes – Generar métricas y comparativas (pendiente).
5. Salir – Terminar el programa.

Cuando el usuario elige la opción 1, se crea una instancia de Campus y se llama a su método ejecutar() para abrir el submenú Campus.

Submenú Campus (Campus)
-----------------------
El submenú es la interfaz dedicada a grafos. Su funcionamiento es el siguiente:

- Precarga automática de un grafo de ejemplo, que representa cuatro edificios: EdificioA, EdificioB, EdificioC y EdificioD, con conexiones y pesos que simulan distancias o tiempos.
- Muestra las opciones:

    1.- BFS
    2.- DFS
    3.- Dijkstra (ruta más corta)
    4.- MST (Kruskal)
    5.- Volver

- Permite recorrer el grafo con BFS o DFS, calcular la ruta más corta con Dijkstra o encontrar el árbol de expansión mínima con Kruskal.
- La opción 5 imprime “Regresando...” y finaliza el bucle, volviendo al menú principal.

El submenú valida entradas erróneas y, cuando un vértice no existe, muestra los edificios disponibles para orientar al usuario.

Clases principales
------------------
- Campus: Gestiona el submenú de grafos. Precarga el grafo de ejemplo y llama a los algoritmos.
- CampusGrafo: Implementa el grafo como lista de adyacencia y opcionalmente una matriz de adyacencia. Incluye métodos para añadir vértices y aristas.
- AristasCampusGrafo: Representa una arista (pasillo) con su destino y peso.
- AlgoritmosdeCampus: Contiene los algoritmos de grafos: BFS, DFS, Dijkstra y Kruskal, todos validados para evitar NullPointerException.

Detalle de algoritmos
---------------------
- BFS y DFS: recorren el grafo mostrando el orden de visita. Complejidad O(V + E).
- Dijkstra: halla la ruta más corta entre dos edificios e imprime el camino y el costo. Complejidad O((V + E) log V) con cola de prioridad.
- Kruskal: calcula el Árbol de Expansión Mínima, mostrando aristas y costo total. Detecta si el grafo es disconexo. Complejidad O(E log E).

Progreso actual
---------------
- Módulo Campus completamente funcional, con:
  * Precarga de grafo de ejemplo.
  * BFS, DFS, Dijkstra y Kruskal.
  * Validaciones de entrada y mensajes de ayuda.
- Menú Principal ya enlaza con este módulo.
- Módulos de Estudiantes, Incidencias y Reportes están definidos en el menú pero aún no desarrollados.

Resumen de dudas resueltas
--------------------------
Durante el desarrollo surgieron varios problemas clave, que quedaron solucionados de la siguientes formas:

- Por qué salía NullPointerException en DFS/BFS: Se debía a que, cuando el usuario introducía un edificio inexistente, Map.get() devolvía null. Se corrigió usando getOrDefault(..., Collections.emptyList()) y validaciones de entrada.
- Por qué a veces aparecían caracteres raros como MEN : Era un problema de codificación (UTF-8 vs Cp1252), no del código Java.
- Qué hace cada opción del menú: Se explicó que la opción 5 no calcula nada, solo sale del bucle; la opción 4 llama a kruskal() y calcula la conexión mínima entre todos los edificios.
- Cómo funciona el cálculo de Kruskal: Se detalló que el algoritmo ordena las aristas por peso, usa UnionFind para evitar ciclos, selecciona las aristas más baratas y acumula el costo total.
- Cómo asegurar compatibilidad con Java 8: Se reemplazó Map.entry() por AbstractMap.SimpleEntry en Dijkstra.
