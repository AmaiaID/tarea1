package ej1sinbuffer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ej1sinbuffer {
    
    public static void main(String[] args) {
        // Ruta del archivo de entrada y salida
        String archivoEntrada = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\ej1sinbuffer\\ej1sinbuffer\\entrada.txt";
        String archivoSalida = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\ej1sinbuffer\\ej1sinbuffer\\salida.txt";

        try {
            // Leer el archivo de entrada usando FileReader
            FileReader fr = new FileReader(archivoEntrada);
            StringBuilder contenido = new StringBuilder();
            int c;

            // Leer carácter por carácter y formar la línea completa
            while ((c = fr.read()) != -1) {
                contenido.append((char) c);  // Convertir el entero a carácter
            }
            fr.close();  // Cerrar el archivo de entrada

            // Invertir las palabras individualmente
            String lineaInvertida = ej1sinbuffer(contenido.toString().trim());

            // Escribir la línea invertida en el archivo de salida usando FileWriter
            FileWriter fw = new FileWriter(archivoSalida);
            fw.write(lineaInvertida);
            fw.close();  // Cerrar el archivo de salida

            System.out.println("Línea invertida escrita en: " + archivoSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para invertir cada palabra de la línea
    public static String ej1sinbuffer(String linea) {
        // Dividir la línea en palabras usando espacios como delimitador
        String[] palabras = linea.split("\\s+");

        // Variable para almacenar el resultado final
        String resultado = "";

        // Invertir cada palabra
        for (String palabra : palabras) {
            String palabraInvertida = new StringBuilder(palabra).reverse().toString();
            resultado += palabraInvertida + " ";
        }

        // Retornar la cadena resultante, quitando el último espacio en blanco
        return resultado.trim();
    }
}