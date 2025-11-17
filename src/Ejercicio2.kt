class Ejercicio2 {
    fun main(){
        println("*** Calculadora ***")

        //Pedimos el primer número
        print("Primer número: ")
        val a = readLine()?.toDoubleOrNull() ?: return

        //Pedimos la operación
        println("Operación +, -, *, /")
        val operacion = readLine() ?: return

        //Pedimos el segundo número
        print("Segundo número: ")
        val b = readLine()?.toDoubleOrNull() ?: return

    }
}