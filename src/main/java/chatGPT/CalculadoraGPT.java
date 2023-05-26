package chatGPT;
public class CalculadoraGPT {
    // Método para adição
    public static double adicao(double a, double b) {
        return a + b;
    }

    // Método para subtração
    public static double subtracao(double a, double b) {
        return a - b;
    }

    // Método para multiplicação
    public static double multiplicacao(double a, double b) {
        return a * b;
    }

    // Método para divisão
    public static double divisao(double a, double b) {
        if(b != 0) {
            return a / b;
        } else {
            System.out.println("Erro: divisão por zero não é permitida.");
            return Double.NaN;
        }
    }
}

