package p01ejerciciosbasicos.obligatorios

import java.util.Locale
import java.util.Locale.getDefault

data class Contactos(var nombre:String, var email:String, var telefono:String, var favorito: Boolean){
    override fun toString(): String {
        return "Nombre: $nombre\n" +
                "Email: $email\n" +
                "Telefono: $telefono"
    }
}

var contactos = mutableListOf<Contactos>()

fun main(){
    contactos.add(Contactos("Maria","maria@gmail.com","123456789",true))
    contactos.add(Contactos("Bob","bob@gmail.com","9876584321",false))
    contactos.add(Contactos("Pedro","pedro@gmail.com","789456123",false))

    menuInteractivo()
}


fun menuInteractivo(){
    while (true) {
        println("\n*** Contactos ***")
        println("1. Crear contacto")
        println("2. Buscar por nombre")
        println("3. Lista de contactos ordenados")
        println("4. Lista de favoritos")
        println("5. Marcar como favorito")
        println("6. Eliminar contacto")
        println("7. Crear contacto")
        println("8. Salir")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> crearContacto()
            2 -> buscarPorNombre()
            3 -> obtenerOrdenados()
            4 -> ""
            5 -> ""
            6 -> ""
            7 -> ""
            8 -> break
            else -> println("Opción inválida")
        }
    }
}

fun validarNombre(nombre: String): Result<String>{
    if (nombre.isBlank()){
        return Result.failure(Exception("El nombre no puede ser vacio"))
    }
    if (nombre.length !in 3..15){
        return Result.failure(Exception("El nombre debe ser menor de 16 caracteres y mayor a 3 caracteres"))
    }
    return Result.success(nombre)
}

fun validarEmail(email: String): Result<String>{
    if (email.isBlank()){
        return Result.failure(Exception("El email no puede ser vacio"))
    }
    if (!email.contains("@")){
        return Result.failure(Exception("El email debe contener un @"))
    }
    return Result.success(email)
}

fun validarTelefono(telefono: String): Result<String>{
    if (telefono.isBlank()){
        return Result.failure(Exception("El telefono no puede ser vacio"))
    }
    if (telefono.toIntOrNull() == null){
        return Result.failure(Exception("El telefono no puede tener caracteres que no sean numéricos"))
    }
    if (telefono.length !in 9..15){
        return Result.failure(Exception("El telefono debe tener entre 9 y 15 números"))
    }
    return Result.success(telefono)
}

fun crearContacto(){
    println("\n*** Crear contato ***")
    println("Introduce un nombre: ")
    val nombre = validarNombre(readlnOrNull().toString())
    println("Introduce el emal: ")
    val email = validarEmail(readlnOrNull().toString())
    println("Introduce el telefono: ")
    val telefono = validarTelefono(readlnOrNull().toString())

    contactos.add(Contactos(nombre.toString(), email.toString(), telefono.toString(), false))
}

fun buscarPorNombre(){
    println("\n*** Buscar por nombre ***")
    print("Introduce un nombre para filtrar: ")
    val nombre = readlnOrNull().toString()
    validarNombre(nombre)
    println("Contactos con el nombre: $nombre")
    val contactosFiltrados = contactos.filter{ it.nombre.lowercase(getDefault()).contains(nombre.lowercase(getDefault()))}
    if (contactosFiltrados.isEmpty()) {
        println("No se han encontrado contactos con ese nombre.")
    } else {
        println("-------------")
        contactosFiltrados.forEach { println(it) }
    }
}

fun obtenerOrdenados(){
    println("\n*** Obtener ordenados ***")
    contactos.sortedBy { it.nombre }.forEach {
        println("-------------")
        println(it)
    }
}

