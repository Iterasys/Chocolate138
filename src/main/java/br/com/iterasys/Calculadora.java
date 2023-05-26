package br.com.iterasys;

public class Calculadora {

    public static int somarInteiros(int num1, int num2){
        int soma = num1 + num2;
        System.out.println("Soma de " + num1 + " + " + num2 + " = " + soma);
        return soma;
    }

    public static int subtrairInteiros(int num1, int num2){
        int resultado = num1 - num2;
        System.out.println("Subtração de " + num1 + " - " + num2 + " = " + resultado);
        return resultado;
    }

    public static int multiplicarInteiros(int num1, int num2){
        int resultado = num1 * num2;
        System.out.println("Multiplicação de " + num1 + " * " + num2 + " = " + resultado);
        return resultado;
    }

    public static int dividirInteiros(int num1, int num2){
        int resultado = num1 / num2;
        System.out.println("Divisão de " + num1 + " / " + num2 + " = " + resultado);
        return resultado;
    }

}
