package ej1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InvertirPalabras {

    public static void main(String[] args) {
        // Ruta completa del archivo de entrada y salida
        String archivoEntrada = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\src\\ej1\\entrada.txt";  // Ruta completa del archivo de entrada
        String archivoSalida = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\src\\ej1\\salida.txt";    // Ruta completa del archivo de salida

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))) {

            // Leer la línea del archivo de entrada
            String linea = lector.readLine();
            if (linea != null) {
                // Invertir las palabras de la línea
                String lineaInvertida = invertirPalabras(linea);

                // Escribir la línea invertida en el archivo de salida
                escritor.write(lineaInvertida);
                escritor.newLine();  // Añadir salto de línea después de escribir
                System.out.println("Línea invertida escrita en: " + archivoSalida);
            } else {
                System.out.println("El archivo está vacío.");
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error durante la lectura o escritura del archivo.");
            e.printStackTrace();
        }
    }

    // Método para invertir las palabras en la línea
    public static String invertirPalabras(String linea) {
        // Dividir la línea en palabras )
        String[] palabras = linea.split("\\s+");

        // StringBuilder para almacenar la línea invertida
        StringBuilder resultado = new StringBuilder();

        // Invertir cada palabra individualmente
        for (String palabra : palabras) {
            // Invertir la palabra y añadirla al resultado
            String palabraInvertida = new StringBuilder(palabra).reverse().toString();
            resultado.append(palabraInvertida).append(" ");
        }

        // Devolver el resultado, quitando el último espacio en blanco
        return resultado.toString().trim();
    }
}

