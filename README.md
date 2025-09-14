
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


Módulo Estudiantes – Árbol AVL y Ordenamientos
==============================================

El segundo gran avance del proyecto ha sido el desarrollo completo del **Módulo Estudiantes**, que permite administrar un registro de alumnos con estructura de árbol auto-balanceado (AVL) y múltiples algoritmos de ordenamiento.

Estructura y funcionamiento
----------------------------
El módulo se ubica en el paquete `com.mycompany.campusnavigatorandanalytics` y consta de las siguientes clases:

* **Estudiante**
  Modelo de datos con atributos `id` (int), `nombre` (String), `carrera` (String) y `promedio` (double).
  Incluye getters, setters y `toString()` para una salida legible.

* **AVLTree**
  Árbol de búsqueda auto-balanceado que permite inserción, eliminación y búsqueda por id en tiempo O(log n).
  Cada nodo almacena un objeto `Estudiante` y se rebalancea mediante rotaciones simples o dobles según sea necesario.

* **ManejoEstudiantes**
  Actúa como capa de servicio: inserta y elimina estudiantes en el AVL, realiza búsquedas por id, permite búsqueda lineal por nombre y mantiene una lista auxiliar para aplicar los distintos algoritmos de ordenamiento.

* **Ordenamiento (interfaz)**
  Define el método `sort(List<Estudiante> list, String campo)` que deben implementar todos los algoritmos de ordenamiento.

* **Algoritmos de ordenamiento**
  Implementados cada uno en su propia clase:
  `InsertionSort`, `SelectionSort`, `ShellSort`, `QuickSort`, `MergeSort`, `HeapSort` y `RadixSort`.
  Todos cumplen con la interfaz `Ordenamiento` y permiten ordenar por `id`, `nombre` o `promedio`, excepto `RadixSort`, que solo se aplica a `id`.

* **EstudiantesMenu**
  Es el submenú que interactúa con el usuario. Gestiona las operaciones de alta, baja, búsqueda y ordenamiento.
  Mantiene el estilo de `switch : break;` usado en todo el proyecto para facilitar la lectura.

Submenú Estudiantes
-------------------
Cuando el usuario selecciona la opción *Estudiantes* en el menú principal, se abre el siguiente submenú:

--- MENÚ ESTUDIANTES ---
1.- Alta de estudiante
2.- Baja de estudiante
3.- Búsqueda por ID
4.- Búsqueda por nombre
5.- Ordenar (elige algoritmo + campo)
6.- Volver

* **Alta**: solicita id, nombre, carrera y promedio, e inserta el estudiante en el AVL y en la lista.
* **Baja**: pide el id y elimina el registro si existe.
* **Búsqueda por ID**: consulta el AVL en tiempo logarítmico.
* **Búsqueda por nombre**: búsqueda lineal en la lista de estudiantes.
* **Ordenar**: permite elegir el campo (`id`, `nombre` o `promedio`) y el algoritmo de ordenamiento deseado (`insertion`, `selection`, `shell`, `quick`, `merge`, `heap`, `radix`).

La opción 6 imprime “Regresando…” y vuelve al menú principal.

Detalle de los algoritmos de ordenamiento
------------------------------------------
Todos los algoritmos trabajan sobre una copia de la lista de estudiantes:

| Algoritmo      | Complejidad promedio | Características principales |
|----------------|-----------------------|------------------------------|
| **InsertionSort** | O(n²) | Simple, adecuado para listas pequeñas. |
| **SelectionSort** | O(n²) | Selecciona el mínimo en cada pasada. |
| **ShellSort** | O(n log² n) aprox. | Mejora de Insertion, usa intervalos decrecientes. |
| **QuickSort** | O(n log n) promedio | Divide y conquista; muy eficiente en la práctica. |
| **MergeSort** | O(n log n) | Estable, buen rendimiento en datos grandes. |
| **HeapSort** | O(n log n) | Basado en un heap binario, garantiza O(n log n). |
| **RadixSort** | O(n k) | Ordena enteros por dígitos, aplicado solo a `id`. |

El patrón **Strategy** permite intercambiar de manera transparente el algoritmo de ordenamiento sin modificar el resto del código.

Validaciones y robustez
-----------------------
* Se valida cada entrada para evitar excepciones (por ejemplo `NumberFormatException`).
* Si se intenta ordenar por `RadixSort` un campo distinto de `id`, se muestra un aviso y no se ejecuta.
* En búsquedas por nombre o id se avisa claramente si no se encuentra el estudiante.

Integración con el Menú Principal
----------------------------------
En `MenuPrincipal` se añadió la llamada:

case 2:
    EstudiantesMenu menuE = new EstudiantesMenu();
    menuE.ejecutar();
    break;

Esto enlaza el módulo Estudiantes con la misma lógica de navegación que el módulo Campus.

Progreso alcanzado
-------------------
Con este trabajo el proyecto ahora cuenta con:
* Gestión de estudiantes con inserción, eliminación y búsqueda eficiente mediante AVL.
* Siete algoritmos de ordenamiento intercambiables para ordenar por diferentes campos.
* Un submenú robusto y claro que mantiene la coherencia visual y lógica del menú principal.