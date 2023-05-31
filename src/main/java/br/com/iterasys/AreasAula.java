package br.com.iterasys;

public class AreasAula {
    public static double calcularQuadrado(double lado){ return Math.pow(lado, 2); }
    public static double calcularRetangulo(double largura, double comprimento){ return largura * comprimento; }
    public static double calcularTriangulo(double largura, double comprimento){ return largura * comprimento / 2; }
    public static double calcularCirculo(double raio){ return Math.PI * Math.pow(raio, 2); }

}
