package FiltrarNombresCincoLetras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltrarNombresCincoLetras {

    public static void main(String[] args) {
        // Rutas de los archivos de entrada y salida
        String archivoEntrada = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\ej2\\FiltrarNombresCincoLetras\\nombres.txt";  // Archivo con nombres y apellidos
        String archivoSalida = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\ej2\\FiltrarNombresCincoLetras\\nombrescincoletras.txt";  // Archivo de salida con nombres de cinco letras

        try (
            // Abrir archivo de entrada y archivo de salida
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))
        ) {
            String linea;
            // Leer cada línea del archivo de entrada
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en palabras (nombres y apellidos)
                String[] palabras = linea.split("\\s+");

                // Comprobar si hay al menos una palabra en la línea (nombre)
                if (palabras.length > 0) {
                    String nombre = palabras[0];  // La primera palabra es el nombre

                    // Verificar si el nombre tiene exactamente cinco letras
                    if (nombre.length() == 5) {
                        // Escribir el nombre de cinco letras en el archivo de salida
                        escritor.write(nombre);
                        escritor.newLine();  // Saltar a la siguiente línea después de escribir el nombre
                    }
                }
            }

            System.out.println("Nombres de cinco letras escritos en: " + archivoSalida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
