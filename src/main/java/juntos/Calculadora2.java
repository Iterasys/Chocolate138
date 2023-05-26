package juntos;

public class Calculadora2 {

    public static double somar(double num1, double num2){
        double soma = num1 + num2;
        System.out.println("Soma de " + num1 + " + " + num2 + " = " + soma);
        return soma;
    }

    public static double subtrair(double num1, double num2){
        double resultado = num1 - num2;
        System.out.println("Subtração de " + num1 + " - " + num2 + " = " + resultado);
        return resultado;
    }

    public static double multiplicar(double num1, double num2){
        double resultado = num1 * num2;
        System.out.println("Multiplicação de " + num1 + " * " + num2 + " = " + resultado);
        return resultado;
    }

    public static double dividir(double num1, double num2){
        if(num2 == 0.0) {
            System.out.println("Erro: divisão por zero não é permitida.");
            return Double.NaN; // Retorne NaN para indicar uma operação inválida
        }
        double resultado = num1 / num2;
        System.out.println("Divisão de " + num1 + " / " + num2 + " = " + resultado);
        return resultado;
    }

}
