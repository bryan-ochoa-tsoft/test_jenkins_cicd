package com.example;

/**
 * Clase principal de la aplicación.
 */
public class App {

    /**
     * Método main de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("¡Hola! Esta es una aplicación Maven básica para Jenkins y SonarQube.");
        System.out.println("Versión: 1.0.0");

        Calculator calculator = new Calculator();
        int result = calculator.add(5, 3);
        System.out.println("5 + 3 = " + result);
    }
}