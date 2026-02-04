package p02ejerciciosbasicos.obligatorios

data class Libro(val nombre: String, val autor: String, val fecha: String, val disponibilidad: Boolean)

val libros = mutableListOf<Libro>()

fun main() {

    libros.add(Libro("Libro5", "Alicia", "2006", false))
    libros.add(Libro("Libro1", "Pepe", "2002", true))
    libros.add(Libro("Libro3", "Bob", "2004", false))
    libros.add(Libro("Libro4", "Pepe", "2005", true))
    libros.add(Libro("Libro2", "Bob", "2003", false))

    while (true){
        println("\n*** Biblioteca ***")
        println("1. Buscar libro por autor")
        println("2. Buscar libro por rango de fecha")
        println("3. Buscar libro por nombre")
        println("4. Libros disponibles")
        println("5. Ver estadisticas")
        println("6. Añadir libro")
        println("7. Todos los libros (por fecha)")
        println("8. Salir")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> buscarPorAutor()
            2 -> buscarPorFecha()
            3 -> busquedaPorTitulo()
            4 -> librosDisponibles()
            5 -> calcularEstadisticas()
            6 -> addLibro()
            7 -> librosPorFecha()
            8 -> break
            else -> print("opción no valida")
        }
    }
}

fun buscarPorAutor(){
    println("Introduce el nombre del autor: ")
    val autor = readlnOrNull().toString().lowercase().trim()
    val librosAutor = libros.filter { it.autor.lowercase().contains(autor) }

    if (librosAutor.isEmpty()){
        println("No hay libros registrados con el nombre del autor introducido: $autor")
    }

    librosAutor.forEach {
        println("${it.nombre}, ${it.autor}, ${it.fecha}")
    }
}

fun buscarPorFecha(){
    println("Introduce la fecha inicial: ")
    val fecha1 = readLine().toString()
    println("Introduce la fecha final: ")
    val fecha2 = readLine().toString()

    if (fecha1.toIntOrNull() == null || fecha2.toIntOrNull() == null){
        println("Fecha invalida")
        return
    }

    val librosFecha = libros.filter { it.fecha in fecha1..fecha2 }
    librosFecha.forEach {
        println("${it.nombre}, ${it.autor}, ${it.fecha}")
    }
}

fun librosDisponibles(){
    println("Libros disponibles: ")
    var librosDisponibles = 0
    libros.forEach{
        if(it.disponibilidad){
            println("${it.nombre}, ${it.fecha}")
            librosDisponibles++
        }
    }
    println("Número de libros disponibles: $librosDisponibles")
}

fun calcularEstadisticas(){
    val numeroLibros = libros.size
    println("Número de libros en la biblioteca: $numeroLibros")

    val fechas = libros.map { it.fecha.toInt() }
    val promedio = fechas.average()
    println("Año promedio de publicación: ${promedio.toInt()}")

    val autorPorLibro = libros.groupBy { it.autor }
    autorPorLibro.forEach { (autor, listaLibros) ->
        println("El $autor tiene ${listaLibros.size} libros")
    }
}

fun busquedaPorTitulo(){
    println("Introduce la titulo: ")
    val titulo = readLine().toString().lowercase().trim()
    val librosTitulo = libros.filter { it.nombre.lowercase().contains(titulo) }
    librosTitulo.forEach {
        println("${it.nombre}, ${it.autor}, ${it.fecha}")
    }
}

fun librosPorFecha(){
    libros.sortBy { it.fecha }
    libros.forEach{
        println("${it.nombre}, ${it.autor}, ${it.fecha}")
    }
}

fun addLibro(){
    println("Introduce el nombre del libro: ")
    val nombre = readLine().toString()
    println("Introduce el autor/a del libro: ")
    val autor = readLine().toString()
    println("Introduce la fecha del libro: ")
    val fecha = readLine().toString()

    if (fecha.toIntOrNull() == null){
        println("Fecha invalida")
        return
    }

    libros.add(Libro(nombre, autor, fecha, true))
}