# Mundo-Cubo

Se desea desarrollar un algoritmo en Kotlin para calcular la cantidad de cubos de agua necesarios para llenar la "Ciudad Perdida de la Atlántida" construida en el juego Mundo Cubo. La ciudad se modela como un tablero rectangular de nxm celdas, donde cada celda contiene una torre de cubos de una altura determinada.

## Integrantes:

- Cristina Puyosa 23-10395
- Jean Sifontes 22-10387

---

## Descripción

Se implementó una solución utilizando una Cola de Prioridad y una variación de Dijkstra sobre un grafo implícito.

El grafo implícito G = (V, E) siendo cada celda de la matriz un vértice y cada vecino su adyacente.

El proceso consiste en rodear inicialmente el perímetro de la ciudad agregando todas las celdas de los bordes al Min-Heap, ya que podemos asumir que fuera de los límites de la ciudad el nivel es cero y el agua en los bordes se derramará. A partir de allí, el algoritmo extrae la celda con el nivel más bajo (sea el nivel de los bloques sólidos o del agua ya estancada sobre ellos). Por cada celda vecina adyacente que no haya sido visitada, se evalúa si su altura es menor a la celda actual; en ese caso, se acumula la diferencia como agua retenida. Finalmente, el vecino se añade al heap asumiendo la nueva altura máxima entre su base original y el nivel del agua acumulada.

## Análisis de las funciones

| Función         | Input               | Output | Complejidad                            | Estructura de Datos                                                                                                            |
| :-------------- | :------------------ | :----- | :------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------- |
| **AlfonsoJose** | `Array<Array<Int>>` | `Int`  | $O(V \log V)$ (Donde $V = n \times m$) | **PriorityQueue (Min-Heap):** Extrae la celda de menor elevación.<br>**Matriz (BooleanArray):** Registra las celdas visitadas. |
| **main**        | `Array<String>`     | `Unit` | $O(V)$                                 | **File** Lee el archivo .txt y lo procesa en una matriz bidimensional.                                                         |

### AlfonsoJose:

Recibe una matriz bidimensional de enteros donde cada posición representa la altura de la torre correspondiente. Inicializa el Min-Heap con los bordes de la matriz y comienza a explorar hacia el interior, actualizando la cota del agua e incrementando el contador de agua acumulada por cada cubos de agua encontrados. Retorna la cantidad total de agua almacenada.

### main:

Función principal encargada de procesar la entrada de datos. Lee el archivo pasado por los argumentos de la línea de comandos, separa sus líneas para construir la matriz bidimensional de enteros requerida, ejecuta el algoritmo "sAlfonsoJose" y procede a imprimir por la salida estándar el resultado de los cubos de agua necesarios.
