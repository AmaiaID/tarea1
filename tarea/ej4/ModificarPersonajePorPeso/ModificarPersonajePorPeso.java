package ModificarPersonajePorPeso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ModificarPersonajePorPeso {

    public static void main(String[] args) throws IOException {
        final int long_registro = 126;  // Longitud del registro (ajustada para este caso)
        Scanner sc = new Scanner(System.in);  // Para leer la entrada del usuario

        try {
            // Definir el archivo Marvel.dat
            File fichero = new File("C:\\Users\\Pc\\git\\repository\\tarea\\ej4\\Marvel.dat");
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");
            
            String dniFichero, nombreFichero;
            int pesoActual, nuevoPeso, posicion;
            char[] aux = new char[15];  // Array para manejar cadenas de texto

            // Solicitar el DNI del personaje al usuario
            System.out.println("Introduzca el DNI del personaje:");
            String dniConsola = sc.nextLine();

            // Calcular la posición en el archivo
            posicion = 0;  // El archivo será recorrido completamente

            boolean personajeEncontrado = false;  // Para verificar si encontramos el personaje

            // Recorrer todo el archivo
            while (posicion < file.length()) {
                file.seek(posicion);  // Moverse a la posición del personaje actual
                
                // Leer el ID (sin usarlo, pero avanzamos en el archivo)
                file.readInt();
                
                // Leer el DNI (10 caracteres)
                aux = new char[10];
                for (int i = 0; i < 10; i++) {
                    aux[i] = file.readChar();
                }
                dniFichero = new String(aux).trim();

                // Comparar con el DNI ingresado por el usuario
                if (dniFichero.equals(dniConsola)) {
                    personajeEncontrado = true;
                    
                    // Leer el nombre del personaje (15 caracteres)
                    aux = new char[15];
                    for (int i = 0; i < 15; i++) {
                        aux[i] = file.readChar();
                    }
                    nombreFichero = new String(aux).trim();

                    // Saltar los otros campos no necesarios para esta operación
                    file.skipBytes(60);  // Saltamos identidad (20 caracteres) + tipo (10 caracteres) + 4 bytes de salto

                    // Leer el peso actual
                    pesoActual = file.readInt();
                    
                    // Saltar la altura
                    file.skipBytes(4);

                    // Solicitar el nuevo peso
                    System.out.println("Introduzca el nuevo peso del personaje:");
                    nuevoPeso = sc.nextInt();

                    // Calcular diferencia de peso y mostrar resultado
                    int diferenciaPeso = nuevoPeso - pesoActual;
                    if (diferenciaPeso > 0) {
                        System.out.println(nombreFichero + " ha engordado " + diferenciaPeso + " kilos.");
                    } else if (diferenciaPeso < 0) {
                        System.out.println(nombreFichero + " ha adelgazado " + Math.abs(diferenciaPeso) + " kilos.");
                    } else {
                        System.out.println(nombreFichero + " se mantiene en su peso.");
                    }

                    // Actualizar el peso en el archivo
                    file.seek(posicion + 104);  // Posición exacta del campo "peso"
                    file.writeInt(nuevoPeso);
                    
                    break;  // Salir del bucle una vez que encontramos y modificamos el personaje
                }

                // Ir al siguiente registro
                posicion += long_registro;  // Saltar al siguiente registro
            }

            if (!personajeEncontrado) {
                System.out.println("El personaje con DNI " + dniConsola + " no fue encontrado.");
            }

            file.close();  // Cerrar el archivo al final

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }

        sc.close();  // Cerrar el escáner
    }
}
