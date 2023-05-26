// 1 - Pacote
package br.com.iterasys;
// 2 - Importação de Bibliotecas


// 3 - Classe
public class Encomenda {
    // 3.1 Atributos

    // 3.2 Funções e Métodos
    public static int calcularBarrasDeChocolatesPorCaixa(int barras){
        byte quantidadePorCaixa = 12;
        int caixas = barras / quantidadePorCaixa;
        int unidades = barras % quantidadePorCaixa;
        System.out.println("Quantidade de Caixas: " + caixas);
        System.out.println("Unidades: " + unidades);
        return caixas;

    } // fim do calcularBarrasDeChocolatesPorCaixa
} // fim da classe Encomenda