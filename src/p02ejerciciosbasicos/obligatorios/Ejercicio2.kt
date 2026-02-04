package p02ejerciciosbasicos.obligatorios

data class Kelvin(val numero: Double)
data class Celsius(val numero: Double)
data class Fahrenheit(val numero: Double)

var historial = ""

fun main() {
    menu()
}

fun celsiusAFahrenheit() {
    println("¿De celsius a fahrenheit (1) o de fahrenheit a celsius (2)?")
    val opcion = readLine()?.toIntOrNull()
    when (opcion) {
        1 -> {
            println("Introduce la temperatura en Celsius:")
            val numeroInput = Celsius(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Fahrenheit(0.0))
            println("Resultado: $resultado °F")
            historial += "\nCelsius a Fahrenheit: ${numeroInput.numero}°C = $resultado°F"
        }
        2 -> {
            println("Introduce la temperatura en Fahrenheit:")
            val numeroInput = Fahrenheit(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Celsius(0.0))
            println("Resultado: $resultado °C")
            historial += "\nFahrenheit a Celsius: ${numeroInput.numero}°F = $resultado°C"
        }
        else -> println("Opcion invalida")
    }
}

fun kelvinACelsius() {
    println("¿De kelvin a celsius (1) o de celsius a kelvin (2)?")
    val opcion = readLine()?.toIntOrNull()
    when (opcion) {
        1 -> {
            println("Introduce la temperatura en Kelvin:")
            val numeroInput = Kelvin(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Celsius(0.0))
            println("Resultado: $resultado °C")
            historial += "\nKelvin a Celsius: ${numeroInput.numero}K = $resultado°C"
        }
        2 -> {
            println("Introduce la temperatura en Celsius:")
            val numeroInput = Celsius(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Kelvin(0.0))
            println("Resultado: $resultado K")
            historial += "\nCelsius a Kelvin: ${numeroInput.numero}°C = $resultado K"
        }
        else -> println("Opcion invalida")
    }
}

fun fahrenheitAKelvin() {
    println("¿De fahrenheit a kelvin (1) o de kelvin a fahrenheit (2)?")
    val opcion = readLine()?.toIntOrNull()
    when (opcion) {
        1 -> {
            println("Introduce la temperatura en Fahrenheit:")
            val numeroInput = Fahrenheit(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Kelvin(0.0))
            println("Resultado: $resultado K")
            historial += "\nFahrenheit a Kelvin: ${numeroInput.numero}°F = $resultado K"
        }
        2 -> {
            println("Introduce la temperatura en Kelvin:")
            val numeroInput = Kelvin(readLine()?.toDoubleOrNull() ?: return println("Entrada no válida"))
            val validation = validarTemperatura(numeroInput)
            if (validation.isFailure) {
                println(validation.exceptionOrNull()?.message)
                return
            }
            val resultado = convertir(numeroInput, Fahrenheit(0.0))
            println("Resultado: $resultado °F")
            historial += "\nKelvin a Fahrenheit: ${numeroInput.numero}K = $resultado °F"
        }
        else -> println("Opcion invalida")
    }
}

fun validarTemperatura(temperatura: Any): Result<Double> {
    return when (temperatura) {
        is Kelvin -> {
            if (temperatura.numero >= 0) {
                Result.success(temperatura.numero)
            } else Result.failure(Exception("La temperatura kelvin no puede ser menor a 0"))
        }
        is Celsius -> {
            if (temperatura.numero >= -273.15) {
                Result.success(temperatura.numero)
            } else Result.failure(Exception("La temperatura celsius no puede ser menor a -273.15"))
        }
        is Fahrenheit -> {
            if (temperatura.numero >= -459.67) {
                Result.success(temperatura.numero)
            } else Result.failure(Exception("La temperatura fahrenheit no puede ser menor a -459.67"))
        }
        else -> Result.failure(Exception("Temperatura no valida"))
    }
}

fun convertir(temperaturaIntroducida: Any, temperaturaATrasformar: Any): Double? {
    return when (temperaturaIntroducida) {
        is Kelvin -> {
            when (temperaturaATrasformar) {
                is Fahrenheit -> temperaturaIntroducida.numero * 9 / 5 - 459.67
                is Celsius -> temperaturaIntroducida.numero - 273.15
                else -> null
            }
        }
        is Celsius -> {
            when (temperaturaATrasformar) {
                is Fahrenheit -> temperaturaIntroducida.numero * 9 / 5 + 32
                is Kelvin -> temperaturaIntroducida.numero + 273.15
                else -> null
            }
        }
        is Fahrenheit -> {
            when (temperaturaATrasformar) {
                is Celsius -> (temperaturaIntroducida.numero - 32) * 5 / 9
                is Kelvin -> (temperaturaIntroducida.numero + 459.67) * 5 / 9
                else -> null
            }
        }
        else -> null
    }
}

fun verHistorial() {
    println("*** Historial ***")
    println(historial)
}

fun menu() {
    while (true) {
        println("\n*** Conversor ***")
        println("1. Convertir de celsius a fahrenheit (o viceversa) ")
        println("2. Convertir de kelvin a celsius (o viceversa) ")
        println("3. Convertir de fahrenheit a kelvin (o viceversa) ")
        println("4. Ver historial")
        println("5. Salir")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> celsiusAFahrenheit()
            2 -> kelvinACelsius()
            3 -> fahrenheitAKelvin()
            4 -> verHistorial()
            5 -> break
            else -> println("Opción inválida")
        }
    }
}
