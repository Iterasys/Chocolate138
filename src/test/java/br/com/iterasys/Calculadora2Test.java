// 1 - Pacote
package br.com.iterasys;
// 2 - Bibliotecas

import juntos.Calculadora2;
import org.testng.Assert;
import org.testng.annotations.Test;

// 3 - Classe
public class Calculadora2Test {
    // 3.1 - Atributos

    // 3.2 - Métodos e Funções
    @Test
    public void testeSomar(){
        // Configura - Arrange
        double num1 = 5;
        double num2 = 7;

        double resultadoEsperado = 12;

        // Executa - Act
        double resultadoAtual = Calculadora2.somar(num1, num2);

        // Valida - Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }



}
