import java.io.IOException;
import java.io.RandomAccessFile;

public class PersonajesRandomAccessFile {

    public static void main(String[] args) {
        // Datos proporcionados
        int[] ids = {1, 2, 3, 4, 5, 6, 7};
        String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
        String[] noms = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
        String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
        String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
        int[] pesos = {76, 84, 66, 136, 78, 102, 70};
        int[] alturas = {178, 183, 156, 152, 177, 182, 188};

        // Nombre del archivo
        String nombreArchivo = "C:\\Users\\Pc\\git\\repository\\tarea\\ej4\\Marvel.dat";

        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            // Escribir los datos en el archivo
            for (int i = 0; i < ids.length; i++) {
                // Escribir el ID
                raf.writeInt(ids[i]);
                
                // Escribir el DNI (debe tener longitud fija, por ejemplo, 9 caracteres)
                raf.writeUTF(dnis[i]);

                // Escribir el nombre (debe tener longitud fija, por ejemplo, 15 caracteres)
                escribirCadenaFija(raf, noms[i], 15);

                // Escribir la identidad (longitud fija de 20 caracteres)
                escribirCadenaFija(raf, identidades[i], 20);

                // Escribir el tipo (longitud fija de 10 caracteres)
                escribirCadenaFija(raf, tipos[i], 10);

                // Escribir el peso
                raf.writeInt(pesos[i]);

                // Escribir la altura
                raf.writeInt(alturas[i]);
            }

            System.out.println("Carga de datos realizada .");

        } catch (IOException e) {
            System.out.println("error durante la carga de datos.");
            e.printStackTrace();
        }
    }

    // Método para escribir una cadena de longitud fija en el archivo
    private static void escribirCadenaFija(RandomAccessFile raf, String cadena, int longitudMax) throws IOException {
        StringBuilder sb = new StringBuilder(cadena);

        // Rellenar con espacios en blanco si la cadena es más corta que la longitud máxima
        while (sb.length() < longitudMax) {
            sb.append(' ');
        }

        // Limitar la cadena a la longitud máxima
        sb.setLength(longitudMax);

        // Escribir la cadena ajustada en el archivo
        raf.writeUTF(sb.toString());
    }
}
