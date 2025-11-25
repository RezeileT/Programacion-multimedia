package p01ejerciciosbasicos.obligatorios

data class Contactos(var nombre:String, var email:String, var telefono:String, var favorito: Boolean){
    override fun toString(): String {
        return "Nombre: $nombre\n" +
                "Email: $email\n" +
                "Telefono: $telefono"
    }
}

var contactos = mutableListOf<Contactos>()

fun main(){
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
    val nombre = validarNombre(readLine().toString())
    println("Introduce el emal: ")
    val email = validarEmail(readLine().toString())
    println("Introduce el telefono: ")
    val telefono = validarTelefono(readLine().toString())

    contactos.add(Contactos(nombre.toString(), email.toString(), telefono.toString(), false))
}

fun buscarPorNombre(){
    println("\n*** Buscar por nombre ***")
    println("Introduce un nombre para filtrar: ")
    val nombre = validarNombre(readLine().toString())
    println("Contactos con el nombre: $nombre")
    contactos.filter { it.nombre == nombre.toString() }.forEach {
        println(it)
    }
}

fun obtenerOrdenados(){
    println("\n*** Obtener ordenados ***")

}