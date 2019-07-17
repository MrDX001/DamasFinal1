package checkers;

import CheckersUI.Menu;

/**
 *
 * @author Santi
 */
public class Cronometro implements Runnable {

    public static int onoff = 0;
    Thread hilo;
    boolean cronometroActivo;

    public void iniciarCronometro() {
        cronometroActivo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    public void empezar() {
        if (onoff == 0) {
            onoff = 1;
            iniciarCronometro();
        }

    }

    public void run() {
        int minutos = 0, segundos = 0;
        String min = "", seg = "";
        try{
            while (cronometroActivo) {
                Thread.sleep(1000);

                segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                //y los segundos vuelven a 0
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                }

                //Esto solamente es estetica para que siempre este en formato
                //00:00
                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = String.valueOf(minutos);
                }
                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = String.valueOf(segundos);
                }

                //Colocamos en la etiqueta la informacion

                Menu.time.setText(min + ":" + seg);
            }
        }catch(Exception e){}

}
}
