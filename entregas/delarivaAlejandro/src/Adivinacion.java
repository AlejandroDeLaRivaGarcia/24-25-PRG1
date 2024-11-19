import java.util.Random;
import java.util.Scanner;

class Adivinacion {
    public static void main(String[] args) {
        final int NUMERO_INTENTOS = 10;
        final int NUMERO_MINIMO = 1;
        final int NUMERO_MAXIMO = 100;
        boolean estaJugando = true;
        int turno = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int numeroOrdenador = random.nextInt(NUMERO_MAXIMO - NUMERO_MINIMO + 1) + NUMERO_MINIMO;
        int ultimoIntento = -1;
        boolean hizoTrampa = false;

        System.out.println("Adivina un número entre el " + NUMERO_MINIMO + " y el " + NUMERO_MAXIMO + "");
        System.out.println("Tienes " + NUMERO_INTENTOS + " intentos para adivinarlo");

        while (estaJugando) {
            turno++;
            System.out.print("Intento " + turno + ": Ingrese un número: ");
            int numeroUsuario = scanner.nextInt();

            if (numeroUsuario == numeroOrdenador) {
                System.out.println("¡Felicidades! Has adivinado el número en el intento " + turno + ".");
                estaJugando = false; 
            } else {
                if (numeroUsuario < numeroOrdenador) {
                    System.out.println("El número es mayor.");
                } else {
                    System.out.println("El número es menor.");
                }

                int diferencia = Math.abs(numeroOrdenador - numeroUsuario);

                if (diferencia <= 5) {
                    System.out.println("Caliente");
                } else if (diferencia <= 10) {
                    System.out.println("Tibio");
                } else {
                    System.out.println("Frío");
                }

                if (diferencia <= 5 && ultimoIntento != -1 && Math.abs(ultimoIntento - numeroOrdenador) <= 5) {
                    int cambio = random.nextInt(5) + 1;
                    numeroOrdenador = Math.max(NUMERO_MINIMO, Math.min(NUMERO_MAXIMO, numeroOrdenador + (random.nextBoolean() ? cambio : -cambio)));
                    System.out.println("Algo raro ocurrió...");
                    hizoTrampa = true;
                }

                ultimoIntento = numeroUsuario;

                if (turno >= NUMERO_INTENTOS) {
                    estaJugando = false;  
                }
            }
        }

        if (turno >= NUMERO_INTENTOS && ultimoIntento != numeroOrdenador) {
            System.out.println("Se acabaron los intentos. El número era " + numeroOrdenador + ".");
        }

        if (hizoTrampa) {
            System.out.println("¡El ordenador hizo trampa!");
        }

        scanner.close();
    }
}