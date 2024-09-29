package VisualizarPersonajesPorTipo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class VisualizarPersonajesPorTipo {

    public static void main(String[] args) throws IOException {
        final int long_registro = 126; // Longitud del registro
        Scanner sc = new Scanner(System.in);  // Para leer la entrada del usuario
        try {
            // Ruta del archivo Marvel.dat
            File fichero = new File("C:\\Users\\Pc\\git\\repository\\tarea\\ej4\\Marvel.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");

            String tipoFichero, dniFichero, nombreFichero, identidadFichero, tipoConsola;
            boolean existeTipo = false;  // Verificar si el tipo existe
            int pesoFichero, alturaFichero;
            int contadorPersonajes = 0;  // Contador para personajes encontrados

            // Solicitar el tipo de personaje (héroe o villano)
            System.out.println("Introduce el tipo de personaje que deseas visualizar (heroe o villano):");
            tipoConsola = sc.nextLine().trim().toLowerCase();  // Convertir a minúsculas para comparar

            // Recorrer el archivo
            for (long p = 0; p < file.length(); p += long_registro) {
                file.seek(p);

                // Saltar el ID (4 bytes)
                file.skipBytes(4);

                // Leer el DNI (10 caracteres)
                char[] auxDni = new char[10];
                for (int i = 0; i < 10; i++) {
                    auxDni[i] = file.readChar();
                }
                dniFichero = new String(auxDni).trim();

                // Leer el nombre (15 caracteres)
                char[] auxNombre = new char[15];
                for (int i = 0; i < 15; i++) {
                    auxNombre[i] = file.readChar();
                }
                nombreFichero = new String(auxNombre).trim();

                // Leer la identidad secreta (20 caracteres)
                char[] auxIdentidad = new char[20];
                for (int i = 0; i < 20; i++) {
                    auxIdentidad[i] = file.readChar();
                }
                identidadFichero = new String(auxIdentidad).trim();

                // Leer el tipo (10 caracteres)
                char[] auxTipo = new char[10];
                for (int i = 0; i < 10; i++) {
                    auxTipo[i] = file.readChar();
                }
                tipoFichero = new String(auxTipo).trim().toLowerCase();

                // Si el tipo coincide con el tipo ingresado por el usuario
                if (tipoFichero.equals(tipoConsola)) {
                    existeTipo = true;
                    contadorPersonajes++;

                    // Leer el peso y la altura
                    pesoFichero = file.readInt();
                    alturaFichero = file.readInt();

                    // Mostrar los datos del personaje
                    System.out.println("----- Personaje " + contadorPersonajes + " -----");
                    System.out.println("DNI: " + dniFichero);
                    System.out.println("Nombre: " + nombreFichero);
                    System.out.println("Identidad Secreta: " + identidadFichero);
                    System.out.println("Tipo: " + tipoFichero);
                    System.out.println("Peso: " + pesoFichero + " kg");
                    System.out.println("Altura: " + alturaFichero + " cm");
                    System.out.println();
                }
            }

            // Verificar si se encontró algún personaje del tipo especificado
            if (!existeTipo) {
                System.out.println("El tipo de personaje introducido no existe.");
            } else {
                System.out.println("Total de personajes de tipo '" + tipoConsola + "': " + contadorPersonajes);
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
        sc.close();  // Cerrar el escáner al final
    }
}
