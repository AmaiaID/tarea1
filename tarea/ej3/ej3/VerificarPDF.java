package ej3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VerificarPDF {

    public static void main(String[] args) {
        // Ruta del archivo que queremos verificar
        String rutaArchivo = "C:\\Users\\Pc\\Desktop\\AMAIA\\tarea\\ej3\\archivo (1).pdf";

        // Secuencia de bytes que deben aparecer al principio de un archivo PDF
        byte[] cabeceraEsperada = {37, 80, 68, 70};  // Equivalente a '%PDF'

        try {
            // Abrir el archivo en modo binario
            InputStream inputStream = new FileInputStream(rutaArchivo);

            // Leer los primeros 4 bytes del archivo
            byte[] cabeceraLeida = new byte[4];
            int bytesLeidos = inputStream.read(cabeceraLeida);

            // Comprobar si se han leído 4 bytes
            if (bytesLeidos == 4) {
                // Comparar los bytes leídos con la cabecera esperada
                boolean esPDFValido = true;
                for (int i = 0; i < 4; i++) {
                    if (cabeceraLeida[i] != cabeceraEsperada[i]) {
                        esPDFValido = false;
                        break;
                    }
                }

                // Informar si es un PDF válido o no
                if (esPDFValido) {
                    System.out.println("El archivo es un PDF válido.");
                } else {
                    System.out.println("El archivo no es un PDF válido.");
                }
            } else {
                System.out.println("El archivo es demasiado pequeño para ser un PDF válido.");
            }

            // Cerrar el flujo
            inputStream.close();

        } catch (IOException e) {
            // Manejo de posibles errores de I/O
            System.out.println("Ocurrió un error al intentar leer el archivo.");
            e.printStackTrace();
        }
    }
}
