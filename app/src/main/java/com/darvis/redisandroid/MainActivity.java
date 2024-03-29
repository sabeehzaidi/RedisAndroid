package com.darvis.redisandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

import nl.melp.redis.Redis;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTaskRunner().execute();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            Redis r = null;
            byte[] result = null;
            try {
                r = new Redis(new Socket("192.168.100.20", 6379)); //IP Address and Port
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                r.call("SET", "foo", "123");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                result = r.call("GET", "foo"); // will print '123'
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new String(result);
        }


        @Override
        protected void onPostExecute(String result) {
            Log.d("DARVIS", "RESULT: " + result);
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }
    }
}
