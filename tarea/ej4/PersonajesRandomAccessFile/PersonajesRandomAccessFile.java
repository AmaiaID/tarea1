package PersonajesRandomAccessFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersonajesRandomAccessFile {

    public static void main(String[] args) throws IOException {
        // Ruta específica del archivo
        File fichero = new File("C:\\Users\\Pc\\git\\repository\\tarea\\ej4\\Marvel.dat");

        // Si el archivo existe, elimínalo antes de crear uno nuevo
        if (fichero.exists()) {
            if (!fichero.delete()) {
                System.out.println("No se pudo eliminar el archivo existente.");
                return;
            }
        }

        // Crear un archivo de acceso aleatorio en modo lectura/escritura
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Datos de los personajes
        int[] ids = {1, 2, 3, 4, 5, 6, 7};
        String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
        String[] nombres = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
        String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
        String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
        int[] pesos = {76, 84, 66, 136, 78, 102, 70};
        int[] alturas = {178, 183, 156, 152, 177, 182, 188};

        // Variables para almacenar las cadenas
        StringBuffer bufferDni;
        StringBuffer bufferNombre;
        StringBuffer bufferIdentidad;
        StringBuffer bufferTipo;

        int cuantos = ids.length;  // Número de personajes

        // Escribir los datos en el archivo
        for (int i = 0; i < cuantos; i++) {
            int posicion = i * 126;  // Calcular la posición en el archivo
            file.seek(posicion);  // Moverse a la posición calculada

            // Escribir el ID
            file.writeInt(ids[i]);

            // Escribir el DNI (10 caracteres)
            bufferDni = new StringBuffer(dnis[i]);
            bufferDni.setLength(10);  // Asegurar que el DNI siempre tenga 10 caracteres
            file.writeChars(bufferDni.toString());

            // Escribir el nombre (15 caracteres)
            bufferNombre = new StringBuffer(nombres[i]);
            bufferNombre.setLength(15);  // Asegurar que el nombre siempre tenga 15 caracteres
            file.writeChars(bufferNombre.toString());

            // Escribir la identidad secreta (20 caracteres)
            bufferIdentidad = new StringBuffer(identidades[i]);
            bufferIdentidad.setLength(20);  // Asegurar que la identidad tenga 20 caracteres
            file.writeChars(bufferIdentidad.toString());

            // Escribir el tipo (héroe o villano) (10 caracteres)
            bufferTipo = new StringBuffer(tipos[i]);
            bufferTipo.setLength(10);  // Asegurar que el tipo tenga 10 caracteres
            file.writeChars(bufferTipo.toString());

            // Escribir el peso y la altura
            file.writeInt(pesos[i]);
            file.writeInt(alturas[i]);
        }

        file.close();
        System.out.println("La carga de los personajes ha terminado correctamente.");

        // Imprimir la ruta completa del archivo generado
        System.out.println("Archivo creado en: " + fichero.getAbsolutePath());
    }
}


