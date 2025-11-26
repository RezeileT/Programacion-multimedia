package p01ejerciciosbasicos.obligatorios

import java.util.Locale.getDefault

data class Contacto(var nombre:String, var email:String, var telefono:String, var favorito: Boolean){
    override fun toString(): String {
        return "Nombre: $nombre\n" +
                "Email: $email\n" +
                "Telefono: $telefono"
    }
}

var contactos = mutableListOf<Contacto>()

fun main(){
    contactos.add(Contacto("Maria","maria@gmail.com","123456789",true))
    contactos.add(Contacto("Bob","bob@gmail.com","9876584321",false))
    contactos.add(Contacto("Pedro","pedro@gmail.com","789456123",false))

    menuInteractivo()
}


fun menuInteractivo(){
    while (true) {
        println("\n*** Contactos ***")
        println("1. Crear contacto")
        println("2. Buscar por nombre")
        println("3. Lista de contactos (ordenados por nombre)")
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
            4 -> obtenerFavoritos()
            5 -> marcarFavorito()
            6 -> eliminarContacto()
            7 -> crearContacto()
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
        return Result.failure(Exception("El telefono debe tener entre 9 y 15 digitos"))
    }
    return Result.success(telefono)
}

fun buscarPorNombre(): List<Contacto>?{
    println("\n*** Buscar por nombre ***")
    print("Introduce un nombre para filtrar: ")
    val nombre = readlnOrNull().toString()
    if (validarNombre(nombre).isFailure){
        println(validarNombre(nombre).exceptionOrNull()?.message)
        return null
    }
    println("Contactos con el nombre: $nombre")
    val contactosFiltrados = contactos.filter{ it.nombre.lowercase(getDefault()).contains(nombre.lowercase(getDefault()))}
    if (contactosFiltrados.isEmpty()) {
        println("No se han encontrado contactos con ese nombre.")
    } else {
        println("-------------")
        contactosFiltrados.forEach { println(it) }
    }
    return contactosFiltrados
}

fun obtenerOrdenados(){
    println("\n*** Lista de contactos ***")
    contactos.sortedBy { it.nombre }.forEach {
        println("-------------")
        println(it)
    }
}

fun obtenerFavoritos(){
    println("\n*** Obtener favoritos ***")
    contactos.filter{ it.favorito }.forEach {
        println("-------------")
        println(it)
    }
}

fun marcarFavorito(){
    println("\n*** Marcar favoritos ***")
    obtenerOrdenados()
    println("Introduce el correo del contacto que quieras pasar a favorito: ")
    val email = readlnOrNull().toString()
    if (validarEmail(email).isFailure){
        println(validarEmail(email).exceptionOrNull()?.message)
        return
    }
    contactos.filter { it.email.lowercase(getDefault()).contains(email.lowercase(getDefault())) }.forEach {
        it.favorito = true;
    }
}

fun eliminarContacto(){
    println("\n*** Eliminar contato ***")
    obtenerOrdenados()
    println("\nIntroduce el correo del contacto que quieras eliminar: ")
    val email = readlnOrNull().toString()
    if (validarEmail(email).isFailure){
        println(validarEmail(email).exceptionOrNull()?.message)
        return
    }
    contactos.filter { it.email.lowercase(getDefault()).contains(email.lowercase(getDefault())) }.forEach {
        contactos.remove(it)
    }
}

fun crearContacto(){
    println("\n*** Crear contato ***")
    print("Introduce un nombre: ")
    val nombre = readlnOrNull().toString()
    if (validarNombre(nombre).isFailure){
        println(validarNombre(nombre).exceptionOrNull()?.message)
        return
    }
    print("Introduce el correo del contacto: ")
    val email = readlnOrNull().toString()
    if (validarEmail(email).isFailure){
        println(validarEmail(email).exceptionOrNull()?.message)
        return
    }
    print("Introduce el telefono: ")
    val telefono = readlnOrNull().toString()
    if (validarTelefono(telefono).isFailure){
        println(validarTelefono(telefono).exceptionOrNull()?.message)
        return
    }
    contactos.add(Contacto(nombre, email, telefono, false))
    println("Contacto añadido")
}