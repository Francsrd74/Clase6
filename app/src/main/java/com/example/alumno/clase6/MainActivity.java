package com.example.alumno.clase6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txv = (TextView)findViewById(R.id.txtView);

        Handler h = new Handler(this);

        MiHilo hilo = new MiHilo(h);

        Thread th = new Thread(hilo,"Primero");

        th.setPriority(Thread.MIN_PRIORITY);

        th.start();

        HiloImagen hilo2 = new HiloImagen(h,"http://www.lslutnfra.com/alumnos/practicas/ubuntu-logo.png", false);

        Thread th2 = new Thread(hilo2,"Primero");

        th2.setPriority(Thread.MIN_PRIORITY);

        th2.start();

    }


    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1){
            TextView txv = (TextView)findViewById(R.id.txtView);
            txv.setText((String)msg.obj);
        }else if (msg.arg1 == 2){
            ImageView img = (ImageView)findViewById(R.id.imgView);

            byte[] buffer = (byte[])msg.obj;
            Bitmap bmp = BitmapFactory.decodeByteArray(buffer,0,buffer.length);
            img.setImageBitmap(bmp);
        }
        else if(msg.arg1 == 3){
            ProgressBar txv = (ProgressBar)findViewById(R.id.progressBar);
            txv.setMax(msg.arg2);
            txv.setProgress((int)msg.obj);

        }


        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
