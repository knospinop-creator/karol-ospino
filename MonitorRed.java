import java.util.Scanner;

public class MonitorRed {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = solicitarCantidadMediciones(scanner);
        double[] tiempos = new double[n];
        
        registrarTiempos(scanner, tiempos, n);
        mostrarResultados(tiempos, n);
        
        scanner.close();
    }

    public static int solicitarCantidadMediciones(Scanner scanner) {
        int n = 0;
        do {
            System.out.print("Ingrese la cantidad de mediciones (entre 3 y 20): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n < 3 || n > 20) {
                    System.out.println("Error: La cantidad debe estar entre 3 y 20.");
                }
            } else {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.next();
            }
        } while (n < 3 || n > 20);
        return n;
    }

    public static void registrarTiempos(Scanner scanner, double[] tiempos, int n) {
        for (int i = 0; i < n; i++) {
            do {
                System.out.print("Ingrese el tiempo de respuesta #" + (i + 1) + " (entre 20 y 500 ms): ");
                if (scanner.hasNextDouble()) {
                    tiempos[i] = scanner.nextDouble();
                    if (tiempos[i] < 20 || tiempos[i] > 500) {
                        System.out.println("Error: El tiempo debe estar entre 20 y 500 ms.");
                    }
                } else {
                    System.out.println("Error: Ingrese un valor numérico válido.");
                    scanner.next();
                    tiempos[i] = 0;
                }
            } while (tiempos[i] < 20 || tiempos[i] > 500);
        }
    }

    public static int contarEntre20y200(double[] tiempos, int n) {
        int contador = 0;
        for (int i = 0; i < n; i++) {
            if (tiempos[i] >= 20 && tiempos[i] <= 200) {
                contador++;
            }
        }
        return contador;
    }

    public static int contarSuperior200(double[] tiempos, int n) {
        int contador = 0;
        for (int i = 0; i < n; i++) {
            if (tiempos[i] > 200) {
                contador++;
            }
        }
        return contador;
    }

    public static double calcularSumaTotal(double[] tiempos, int n) {
        double suma = 0;
        for (int i = 0; i < n; i++) {
            suma += tiempos[i];
        }
        return suma;
    }

    public static double calcularPromedio(double[] tiempos, int n) {
        return calcularSumaTotal(tiempos, n) / n;
    }

    public static double obtenerMenorTiempo(double[] tiempos, int n) {
        double menor = tiempos[0];
        for (int i = 1; i < n; i++) {
            if (tiempos[i] < menor) {
                menor = tiempos[i];
            }
        }
        return menor;
    }

    public static int obtenerPosicionPrimerMenor(double[] tiempos, int n) {
        double menor = obtenerMenorTiempo(tiempos, n);
        for (int i = 0; i < n; i++) {
            if (tiempos[i] == menor) {
                return i + 1;
            }
        }
        return 1;
    }

    public static int contarInferiorPromedio(double[] tiempos, int n) {
        double promedio = calcularPromedio(tiempos, n);
        int contador = 0;
        for (int i = 0; i < n; i++) {
            if (tiempos[i] < promedio) {
                contador++;
            }
        }
        return contador;
    }

    public static void mostrarResultados(double[] tiempos, int n) {
        System.out.println("\n========================================");
        System.out.println("       RESULTADOS DEL MONITOREO DE RED  ");
        System.out.println("========================================");
        System.out.println("1. Cantidad entre 20 y 200 ms: " + contarEntre20y200(tiempos, n));
        System.out.println("2. Cantidad superior a 200 ms: " + contarSuperior200(tiempos, n));
        System.out.println("3. Tiempo total acumulado: " + calcularSumaTotal(tiempos, n) + " ms");
        System.out.println("4. Promedio de tiempo de respuesta: " + String.format("%.2f", calcularPromedio(tiempos, n)) + " ms");
        System.out.println("5. Menor tiempo registrado: " + obtenerMenorTiempo(tiempos, n) + " ms");
        System.out.println("6. Cantidad con tiempo inferior al promedio: " + contarInferiorPromedio(tiempos, n));
        System.out.println("7. Número de la primera medición con el menor tiempo: #" + obtenerPosicionPrimerMenor(tiempos, n));
        System.out.println("8. Listado numerado de tiempos:");
        for (int i = 0; i < n; i++) {
            System.out.println("   - Medición " + (i + 1) + ": " + tiempos[i] + " ms");
        }
        System.out.println("========================================");
    }
}
