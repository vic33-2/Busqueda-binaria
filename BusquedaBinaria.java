import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BusquedaBinaria {
    // Iniciamos con la construcción de un main

    public static void main(String[] args) {
        String archivoEntrada = "numeros1.txt";
        String archivoSalida = "numerosBusquedaBinaria_ordenados.txt";

        int[] numeros = leerArchivo(archivoEntrada);

        if (numeros != null) {
            System.out.println("numeros originales:");
            imprimirArray(numeros);

            // ordena los datos
            Arrays.sort(numeros);

            System.out.println("\n numeros ordenados:");
            imprimirArray(numeros);

             //guarda archivo nuevo
            escribirArchivo(numeros, archivoSalida);
            System.out.println("\n Archivo generado exitosamente: " + archivoSalida);

            //busqueda binaria
            Scanner sc = new Scanner(System.in);
            System.out.print("\n Ingresa un numero a buscar: ");
            int numeroBuscado = sc.nextInt();

            int resultado = busquedaBinaria(numeros, numeroBuscado);

            if (resultado != -1) {
                System.out.println("El nimero " + numeroBuscado + " Se encuentra en la posición (índice): " + resultado);
            } else {
                System.out.println("El numero " + numeroBuscado + " No existe en la lista.");
            }

            sc.close();
        } else {
            System.out.println("No se pudo leer el archivo.");
        }
    }
    //leer archivos
    public static int[] leerArchivo(String nombreArchivo) { 
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int count = 0;

            // contar numeros
            while ((linea = br.readLine()) != null) {
                count++;
            }
            int[] numeros = new int[count];
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(nombreArchivo));
            int i = 0;
            while ((linea = br2.readLine()) != null) {
                numeros[i] = Integer.parseInt(linea.trim());
                i++;
            }
            br2.close();

            return numeros;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    //busqueda binaria
    public static int busquedaBinaria(int[] arr, int dato) {
        int inicio = 0;
        int fin = arr.length - 1;

        while (inicio <= fin) {
            int mitad = inicio + (fin - inicio) / 2;  

            if (arr[mitad] == dato) {
                return mitad;
            } else if (arr[mitad] < dato) {
                inicio = mitad + 1;
            } else {
                fin = mitad - 1;
            }
        }
        return -1; 
    }

    //imprime arreglo
    public static void imprimirArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //guarda numeros ordenados en nuevo archivo
    public static void escribirArchivo(int[] arr, String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int num : arr) {
                bw.write(num + "");
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}