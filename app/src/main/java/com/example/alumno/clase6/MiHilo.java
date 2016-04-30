package com.example.alumno.clase6;

import android.os.Handler;
import android.widget.TextView;
import android.os.Message;

/**
 * Created by alumno on 28/04/2016.
 */
public class MiHilo implements Runnable {


    Handler handler;

    public MiHilo(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {

        if(Thread.interrupted())
            return;

        for (int i = 0; i <= 100; i++) {

            Message d = new Message();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            d.arg1 = 1;
            d.obj = "Contador: " + i;

            handler.sendMessage(d);
            //txtView.setText("chau");

        }
    }

}
