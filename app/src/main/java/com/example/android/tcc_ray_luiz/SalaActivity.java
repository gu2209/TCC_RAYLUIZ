package com.example.android.tcc_ray_luiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class SalaActivity extends AppCompatActivity{

    ImageButton btn_SalaSala, btn_SalaCorredor;


    public static final int SOLICITA_ATIVACAO = 1;
    public static final int MESSAGE_READ = 3;

    ConnectedThread connectedThread;

    Handler mHandler;
    StringBuilder dadosBluetooth = new StringBuilder();

    BluetoothAdapter meuBluetoothAdapter = null;

    boolean conexao = true;

    UUID MEU_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        btn_SalaSala = (ImageButton) findViewById(R.id.btn_SalaSala);
        btn_SalaCorredor = (ImageButton) findViewById(R.id.btn_SalaCorredor);

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btn_SalaSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SalaSala");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SalaCorredor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    connectedThread.enviar("SalaCorredor");

            }
        });

        mHandler = new  Handler(){
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == MESSAGE_READ){

                    String recebidos = (String) msg.obj;
                    dadosBluetooth.append(recebidos);
                    int fimInformacao = dadosBluetooth.indexOf("}");

                    if (fimInformacao > 0){
                        String dadosCompletos = dadosBluetooth.substring(0,fimInformacao);

                        int tamInformacao = dadosCompletos.length();

                        if (dadosBluetooth.charAt(0) == '{'){

                            String dadosFinais = dadosBluetooth.substring(1,tamInformacao);
                            Log.d("Recebidos", dadosFinais);

                            if (dadosFinais.contains("SalaSalaOn")){
                                btn_SalaSala.setBackgroundResource(R.drawable.lampadaon);
                            }else if (dadosFinais.contains("SalaSalaOff")) {
                                btn_SalaSala.setBackgroundResource(R.drawable.lampadaoff);
                            }

                            if (dadosFinais.contains("SalaCorredorOn")){
                                btn_SalaCorredor.setBackgroundResource(R.drawable.lampadaon);
                            }else if (dadosFinais.contains("SalaCorredorOff")) {
                                btn_SalaCorredor.setBackgroundResource(R.drawable.lampadaoff);
                            }
                        }

                        dadosBluetooth.delete(0,dadosBluetooth.length());
                    }
                }
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(meuBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        } else if (!meuBluetoothAdapter.isEnabled()) {
            Intent ativaBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativaBluetooth,SOLICITA_ATIVACAO);
        }

    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    bytes = mmInStream.read(buffer);

                    String dadosBt = new String(buffer,0, bytes);

                    //Send the obtained bytes to the UI activity.
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, dadosBt).sendToTarget();

                } catch (IOException e) {
                    break;
                }
            }
        }

        public void enviar (String dadosEnviar) {
            byte[] msgBuffer = dadosEnviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) { }
        }
    }
}
