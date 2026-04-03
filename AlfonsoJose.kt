import java.util.PriorityQueue
import kotlin.comparisons.compareBy
import java.io.File

fun AlfonsoJose(matriz: Array<Array<Int>>): Int{
    data class Nodo(val i: Int, val j: Int, val nivel: Int)
    val minHeap = PriorityQueue<Nodo>(compareBy { it.nivel })
    val filas = matriz.size
    val columnas = if (filas > 0) matriz[0].size else return 0
    val visitados = Array(filas) { BooleanArray(columnas) }

    //inicializamos el heap con los bordes de la matriz
    for (i in 0 until filas) {
        minHeap.add(Nodo(i, 0, matriz[i][0]))
        visitados[i][0] = true
        if (columnas > 1) {
            minHeap.add(Nodo(i, columnas - 1, matriz[i][columnas - 1]))
            visitados[i][columnas - 1] = true
        }
    }
    for (j in 0 until columnas) {
        minHeap.add(Nodo(0, j, matriz[0][j]))
        visitados[0][j] = true
        if (filas > 1) {
            minHeap.add(Nodo(filas - 1, j, matriz[filas - 1][j]))
            visitados[filas - 1][j] = true
        }
    }
    var aguaAcumulada = 0
    while (minHeap.isNotEmpty()) {
        val celda = minHeap.poll()
        val vecinos = listOf(
            if (celda.i > 0) Nodo(celda.i - 1, celda.j, matriz[celda.i - 1][celda.j]) else null,
            if (celda.i < filas - 1) Nodo(celda.i + 1, celda.j, matriz[celda.i + 1][celda.j]) else null,
            if (celda.j > 0) Nodo(celda.i, celda.j - 1, matriz[celda.i][celda.j - 1]) else null,
            if (celda.j < columnas - 1) Nodo(celda.i, celda.j + 1, matriz[celda.i][celda.j + 1]) else null
        ).filterNotNull().filter { !visitados[it.i][it.j] }

        for (vecino in vecinos) {
            visitados[vecino.i][vecino.j] = true
            val altura = celda.nivel
            val nuevaAltura = maxOf(altura, vecino.nivel)
            if (vecino.nivel < altura) {
                aguaAcumulada += altura - vecino.nivel
            }
            minHeap.add(Nodo(vecino.i, vecino.j, nuevaAltura))
        }
    }
    return aguaAcumulada
}


fun main(args: Array<String>) {
    var lineas = File(args[0]).readLines()
    val matriz = lineas.map { linea ->
        linea.split(" ").map { it.trim().toInt() }.toTypedArray()
    }.toTypedArray()
    val resultado = AlfonsoJose(matriz)
    println(resultado)
}