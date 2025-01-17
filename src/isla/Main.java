package isla;

import java.io.BufferedWriter;

public class Main {
    static boolean vidaEquilibrada = true;
    static BufferedWriter bufferCSV = Salida.iniciarCSV();
    static int momento = 1;

    public static void main(String[] args)  {
        Creacion.iniciarListasDeSeres();
        while (momento < Ajustes.maxTiempo && vidaEquilibrada) {
            Thread estadistica = new Thread(Salida::hacerEstadistica);
            Thread vida = new Thread(() -> {
                vidaEquilibrada = Vida.recrearla();
            });
            Tablero.colocarLosSeres();
            estadistica.start(); // manejo de archivo CVS
            vida.start();        // manejo lógico de la vida
            Ajustes.transcurreEsteMomento();
            try {
                estadistica.join();  // Se supone el manejo de archivo demorará más
                vida.join();         // que los cálculos lógicos
            } catch (InterruptedException e) {
                e.printStackTrace();  }
            momento++;
        } // while
        Salida.accionesDeCierre();
    } // main

} // class