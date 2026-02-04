package p02ejerciciosbasicos.obligatorios

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

data class ResultadosTexto(var numeroCaracteres: Int, var numeroPalabras: Int, var numeroLineas: Int, var palabraMasFrecuente: String, var longitudPalabraPromedio: Double){
    override fun toString(): String {
        return "Número total de caracteres: $numeroCaracteres\n" +
                "Número total de palabras: $numeroPalabras\n" +
                "Número total de lineas: $numeroLineas\n" +
                "Palabra mas frecuente: $palabraMasFrecuente\n" +
                "Promedio de longitud de palabras: $longitudPalabraPromedio"
    }
}

fun main(){
    val ruta = "src/p01ejerciciosbasicos/resources"
    val archivo = "texto.txt"

    val resultado = analizarTexto(ruta + File.separator + archivo)
    println(resultado)
}

fun normalizarTexto(texto: String): String{
    return texto.replace("\r", "").replace("\n", "").replace(".", "").replace(",", "")
}

fun contarCaracteres(texto: String): Int{
    var contadorCaracteres = 0
    for (i in texto.length-1 downTo 0){
        contadorCaracteres++
    }
    return contadorCaracteres
}

fun contarPalabras(texto: String): Int{
    var contadorPalabras = 0
    val palabras = texto.split(' ')
    palabras.forEach {
        contadorPalabras++
    }
    return contadorPalabras
}

fun contarLineas(): Int{
    var contadorLineas = 0
    contadorLineas++
    return contadorLineas
}

fun encontrarPalabraMasFrecuente(texto: String, palabraActual: String): String{
    val palabras = texto.split(' ')
    var palabraMasFrecuente = palabraActual
    palabras.filter { it.length > palabraMasFrecuente.length }.forEach {
        palabraMasFrecuente = it
    }
    return palabraMasFrecuente
}

fun longitudPromedioPalabra(texto: String, promedioActual: Double): Double{
    var longitudPromedio = 0.0

    val palabras = texto.split(' ')
    val valores = palabras.map { it.length.toDouble() }
    longitudPromedio = valores.average()
    longitudPromedio = (promedioActual + longitudPromedio)/2
    return longitudPromedio
}

fun analizarTexto(rutaArchivo: String): ResultadosTexto{
    var linea = ""

    var numeroCaracteres = 0
    var numeroPalabras = 0
    var numeroLineas = 0
    var palabraMasFrecuente = ""
    var palabraPromedio = 0.0

    try {
        val br = BufferedReader(FileReader(rutaArchivo))

        while (br.readLine().also { linea = it } != null) {
            val lineaFormateada = normalizarTexto(linea)
            numeroCaracteres += contarCaracteres(lineaFormateada)
            numeroPalabras += contarPalabras(lineaFormateada)
            numeroLineas += contarLineas()
            palabraMasFrecuente = encontrarPalabraMasFrecuente(lineaFormateada, palabraMasFrecuente)
            palabraPromedio = longitudPromedioPalabra(lineaFormateada, palabraPromedio)
        }
        br.close()
    }catch (e: NullPointerException){
        println("Texto analizado")
    }catch (e:Exception){
        println("Error" + e.message)
    }
    return ResultadosTexto(numeroCaracteres, numeroPalabras, numeroLineas, palabraMasFrecuente, palabraPromedio)
}