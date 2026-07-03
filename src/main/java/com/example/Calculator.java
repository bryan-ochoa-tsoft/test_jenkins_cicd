package com.example;

/**
 * Clase utilitaria para operaciones matemáticas básicas.
 */
public class Calculator {

    /**
     * Suma dos números enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return la suma de a y b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Resta dos números enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return la resta de a menos b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dos números enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return el producto de a y b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divide dos números enteros.
     *
     * @param a dividendo
     * @param b divisor
     * @return el cociente de a entre b
     * @throws IllegalArgumentException si b es cero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir entre cero");
        }
        return a / b;
    }
}