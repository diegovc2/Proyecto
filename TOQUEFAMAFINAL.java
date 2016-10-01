
import java.io.*;
import java.util.Random;

public class ToqueFamaFinal {

    public int record = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        ToqueFamaFinal bamos = new ToqueFamaFinal();
        bamos.juego();
    }

    public void juego() throws IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int[] numero_azar = new int[4];
        int[][] cifra_jugador = new int[15][4];
        String respuesta = "";

        ingreso(numero_azar, cifra_jugador, record);

    }

    public void ingreso(int[] numero_azar, int[][] cifra_jugador, int record) {
        while (true) {
            try {
                String respuesta = "";

                int teclado;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                do {
                    generar_aleatorio(numero_azar);
                } while (verificar_duplicados(numero_azar) != true);

                System.out.println("El numero al azar es : " + String.valueOf(numero_azar[0]) + String.valueOf(numero_azar[1]) + String.valueOf(numero_azar[2]) + String.valueOf(numero_azar[3]));

                System.out.println("RECORD ACTUAL= " + record);

                for (int fila = 0; fila <= cifra_jugador.length; fila++) {

                    System.out.println("Ingrese los 4 digitos distintos\nLe quedan " + (cifra_jugador.length - fila + 1) + " intentos:");
                    teclado = Integer.parseInt(br.readLine());

                    if (String.valueOf(teclado).length() == 4) {
                        for (int x = 0; x <= 3; x++) {
                            cifra_jugador[fila][x] = Integer.parseInt(String.valueOf(teclado).substring(x, (x + 1)));
                        }

                        if (verificar_duplicados_jugador(cifra_jugador, fila) != true) {
                            System.out.println("Error : Debe ingresar 4 digitos distintos \n Ha perdido un intento.");
                        } else if (comprobar(numero_azar, cifra_jugador, fila) == true) {
                            System.out.println("FELICITACIONES! Has ganado a los " + (fila + 1) + " intentos!");

                            if (record == 0 || record > (fila + 1)) {
                                record = (1 + fila);
                                System.out.println("NUEVO RECORD!!! " + record);
                            }
                            System.out.println("Quiere volver a jugar? [S/N]");
                            respuesta = br.readLine();
                            if (respuesta.equals("n")) {
                                System.exit(1);
                            }
                            

                            break;
                        }

                    } else {
                        System.out.println("Error : Debe ingresar 4 digitos \n Favor vuelva a intentar.");
                    }
                    if (fila == cifra_jugador.length) {
                        System.out.println("SE ACABARON LOS INTENTOS");
                        System.out.println("Quiere volver a jugar? [S/N]");
                            respuesta = br.readLine();
                            if (respuesta.equals("n")) {
                                System.exit(1);
                            }
                    }
                    
                }
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
    }

    public static void generar_aleatorio(int[] numero_azar) {
        Random rnd = new Random();
        for (int x = 0; x <= 3; x++) {
            numero_azar[x] = rnd.nextInt(9);  //genera un nÃºmero aleatorio entre 0 y 9 
        }
    }

    public static boolean verificar_duplicados(int[] numero_azar) {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i != j) {
                    if (numero_azar[i] == numero_azar[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean verificar_duplicados_jugador(int[][] numero, int fila) {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i != j) {
                    if (numero[fila][i] == numero[fila][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean comprobar(int[] numero_azar, int[][] cifra_jugador, int fila) {
        int toque = 0;
        int fama = 0;
        boolean result = false;

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i != j) {
                    if (numero_azar[i] == cifra_jugador[fila][j]) {
                        //System.out.println("TOQUE posicion azar " + (i+1) + " posicion jugador " + (j+1)+ " numero azar " + numero_azar[i] + " cifra jugador " + cifra_jugador[j]);
                        toque++;
                    }
                } else if (numero_azar[i] == cifra_jugador[fila][j]) {
                    //System.out.println("FAMA  posicion azar " + (i+1) + " posicion jugador " + (j+1) + " numero azar " + numero_azar[i] + " cifra jugador " + cifra_jugador[j]);
                    fama++;
                }
            }
        }
        System.out.println("Su resultado es fama : " + fama + " toque : " + toque);

        if (fama == 4) {
            result = true;
        }

        return result;

    }
}
