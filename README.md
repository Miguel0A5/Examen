
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

Módulo Incidencias – Gestión de incidentes con MinHeap
=======================================================

El tercer módulo del proyecto permite registrar, priorizar y atender incidencias en el campus mediante una **cola de prioridad implementada con un MinHeap**.  
Su objetivo es asegurar que las incidencias más urgentes se atiendan primero, manteniendo tiempos de operación eficientes.

Estructura y funcionamiento
----------------------------
El módulo se compone de las siguientes clases:

* **Incidencia**
  Representa una incidencia con los campos:
  - `id` (int): identificador único de la incidencia.
  - `titulo` (String): descripción breve del problema.
  - `prioridad` (int): número que indica urgencia (1 = más alta, 10 = más baja).
  - `timestamp` (long): marca de tiempo de creación.
  Incluye getters, setters y `toString()`.

* **MinHeapIncidencias**
  Estructura de datos que implementa un **min-heap**, donde el nodo raíz siempre es la incidencia con menor número de prioridad (es decir, la más urgente).
  Principales operaciones y su complejidad:
  - `push` – Inserta una incidencia en O(log n).
  - `pop` – Extrae la incidencia más urgente en O(log n).
  - `peek` – Consulta la incidencia más urgente sin retirarla en O(1).
  - `buildHeap` – Construye un heap a partir de una lista en O(n).
  - `changePriority` – Cambia la prioridad de una incidencia y re-heapifica en O(log n).
  El diseño garantiza que siempre se atienda primero el caso más urgente.

* **IncidenciasMenu**
  Es la interfaz de usuario para este módulo.
  Permite insertar incidencias, cambiar prioridades, atender (pop), ver la siguiente (peek) y listar todas.
  Mantiene el estilo `switch : break;` usado en los otros módulos para una navegación coherente.

Submenú Incidencias
-------------------
Al elegir la opción **3** en el menú principal, se abre el siguiente submenú:

--- MENÚ INCIDENCIAS ---
1.- Insertar incidencia
2.- Cambiar prioridad
3.- Atender siguiente (pop)
4.- Ver siguiente (peek)
5.- Listar todas
6.- Volver

Las operaciones funcionan de la siguiente manera:

* **Insertar incidencia** – Pide una ID numérica, el título del problema y un número de prioridad de 1 a 10 (1 = alta, 10 = baja).
  La ID permite identificar la incidencia de manera única para posteriores cambios o seguimiento.
* **Cambiar prioridad** – Solicita la ID y la nueva prioridad. Reubica el elemento en el heap sin necesidad de eliminarlo y volver a insertarlo.
* **Atender siguiente (pop)** – Extrae y muestra la incidencia más urgente, eliminándola del heap.
* **Ver siguiente (peek)** – Muestra la incidencia más urgente sin retirarla.
* **Listar todas** – Muestra todas las incidencias registradas en el heap (el orden interno no es necesariamente de menor a mayor prioridad, ya que un heap solo garantiza la raíz como mínimo).
* **Volver** – Regresa al menú principal.

Decisión de diseño: MinHeap
----------------------------
Se eligió un **MinHeap** porque su raíz es siempre el elemento de **menor valor numérico**, lo cual se traduce en **mayor urgencia**.
Esto permite atender las incidencias más críticas de forma natural y eficiente.

Validaciones y experiencia de usuario
-------------------------------------
* Se validan las entradas para evitar `NumberFormatException`.
* Durante la inserción se recuerda al usuario que la **ID debe ser solo numérica** y que la **prioridad va de 1 (alta) a 10 (baja)**.
* En los cambios de prioridad, si la ID no existe, el sistema lo notifica claramente.

Integración con el menú principal
----------------------------------
El `MenuPrincipal` invoca este módulo así:

case 3:
    IncidenciasMenu menuI = new IncidenciasMenu();
    menuI.ejecutar();
    break;

De esta manera, el flujo y la apariencia se mantienen consistentes con los módulos **Campus** y **Estudiantes**.

Progreso alcanzado
-------------------
Con la implementación del módulo Incidencias, el proyecto ahora cuenta con:
* Un sistema de registro de incidencias totalmente funcional.
* Priorización automática basada en un **MinHeap**.
* Operaciones de inserción, extracción, consulta y re-priorización con las complejidades requeridas.
* Interfaz de consola coherente y robusta.

Cuarta parte del README – Integración final de menús y datos compartidos
====================================================================

Integración de módulos y persistencia de datos
----------------------------------------------
En la fase final del proyecto se unificó el manejo de datos entre todos los módulos.  
Hasta este punto, cada submenú (Campus, Estudiantes, Incidencias) creaba sus propias estructuras internas, lo que impedía que la información se compartiera entre módulos o se pudiera consultar de manera global en Reportes.

Para resolverlo, se modificó el MenuPrincipal de modo que:

* Mantiene únicas instancias globales:
  - ManejoEstudiantes manejoEstudiantes
  - MinHeapIncidencias heapIncidencias
  - CampusGrafo campusGrafo
* Pasa estas instancias a cada submenú en sus constructores.  
  Por ejemplo:
  Campus campus1 = new Campus(campusGrafo);
  EstudiantesMenu em1 = new EstudiantesMenu(manejoEstudiantes);
  IncidenciasMenu menuI = new IncidenciasMenu(heapIncidencias);
  ReportesMenu reportes = new ReportesMenu(manejoEstudiantes, heapIncidencias, campusGrafo);

De este modo todos los módulos trabajan sobre el mismo conjunto de datos.

Ajustes de constructores en los submenús
-----------------------------------------
Para permitir esta integración se modificaron las clases:

* Campus: ahora recibe CampusGrafo por parámetro, evitando crear un grafo nuevo en cada sesión.
* EstudiantesMenu: ahora recibe ManejoEstudiantes, de modo que las altas, bajas y búsquedas se hacen sobre la misma estructura que verá el módulo Reportes.
* IncidenciasMenu: ahora recibe MinHeapIncidencias, garantizando que las incidencias insertadas se mantengan y sean visibles desde Reportes.

Estos cambios aseguran que la información sea persistente durante toda la ejecución del programa, sin perder datos al navegar entre menús.

Convenciones para Scanner
-------------------------
Para una mejor organización se definió una convención de nombres en los objetos Scanner:
* En el MenuPrincipal se mantiene el nombre entrada, ya que es el punto de inicio de toda la aplicación.
* En los submenús (Campus, Estudiantes, Incidencias, Reportes) se utiliza el nombre sc.  
  Esta decisión permite diferenciar claramente la fuente principal de entrada (entrada) de los lectores locales (sc) en cada módulo.

Resultado final
---------------
Con estos ajustes:

* El módulo Reportes puede acceder a los datos globales para:
  - Medir tiempos de ordenamiento de estudiantes.
  - Obtener métricas de rutas y costo del árbol de expansión mínima del campus.
  - Conocer el tamaño actual de cada estructura de datos.
* Todos los módulos (Campus, Estudiantes, Incidencias y Reportes) operan sobre estructuras de datos únicas y compartidas, asegurando coherencia y persistencia.
