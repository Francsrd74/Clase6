package com.example.alumno.clase6;

        import android.os.Handler;
        import android.util.Log;
        import android.os.Message;

        import java.io.IOException;

/**
 * Created by alumno on 28/04/2016.
 */
public class HiloImagen implements Runnable {


    Handler handler;
    String url;
    Boolean status;

    public HiloImagen(Handler handler, String url, Boolean status) {
        this.handler = handler;
        this.url = url;
        this.status = status;
    }


    @Override
    public void run() {

        if(Thread.interrupted())
            return;

        HttpManager httpM = new HttpManager(url,handler);

        Message d = new Message();

        if(status) {

            d.arg1 = 1;

            try {
                d.obj = httpM.getStrDataByGET();
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }
        }
        else
        {
            d.arg1 = 2;

            try {
                d.obj = httpM.getBytesDataByGET();
            } catch (IOException e) {
                Log.d("Error", e.getMessage());
            }
        }
            handler.sendMessage(d);
            //txtView.setText("chau");
        }


}