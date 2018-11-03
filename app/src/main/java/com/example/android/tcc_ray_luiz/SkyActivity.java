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

public class SkyActivity extends AppCompatActivity{

    ImageButton btn_SkyOn, btn_SkyGuia, btn_SkyVoltar,btn_SkySair, btn_SkyCima, btn_SkyDireita, btn_SkyEsquerda,
                btn_SkyBaixo, btn_SkyVermelho, btn_SkyVerde, btn_SkyAmarelo, btn_SkyAzul, btn_SkyVolMais, btn_SkyVolMenos,
                btn_SkyChMais, btn_SkyChMenos, btn_SkyUm, btn_SkyDois,btn_SkyTres, btn_SkyQuatro,btn_SkyCinco, btn_SkySeis,
                btn_SkySete, btn_SkyOito, btn_SkyNove, btn_SkyZero;


    public static final int SOLICITA_ATIVACAO = 1;
    public static final int MESSAGE_READ = 3;

    ConnectedThread connectedThread;

    Handler mHandler;
    StringBuilder dadosBluetooth = new StringBuilder();

    BluetoothAdapter meuBluetoothAdapter = null;

    boolean conexao = false;

    UUID MEU_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sky);

        btn_SkyOn = (ImageButton) findViewById(R.id.btn_SkyOn);
        btn_SkyGuia = (ImageButton) findViewById(R.id.btn_SkyGuia);
        btn_SkyVoltar = (ImageButton) findViewById(R.id.btn_SkyVoltar);
        btn_SkySair = (ImageButton) findViewById(R.id.btn_SkySair);
        btn_SkyCima = (ImageButton) findViewById(R.id.btn_SkyCima);
        btn_SkyDireita = (ImageButton) findViewById(R.id.btn_SkyDireita);
        btn_SkyEsquerda = (ImageButton) findViewById(R.id.btn_SkyEsquerda);
        btn_SkyBaixo = (ImageButton) findViewById(R.id.btn_SkyBaixo);
        btn_SkyVermelho = (ImageButton) findViewById(R.id.btn_SkyVermelho);
        btn_SkyVerde = (ImageButton) findViewById(R.id.btn_SkyVerde);
        btn_SkyAmarelo = (ImageButton) findViewById(R.id.btn_SkyAmarelo);
        btn_SkyAzul = (ImageButton) findViewById(R.id.btn_SkyAzul);
        btn_SkyVolMais = (ImageButton) findViewById(R.id.btn_SkyVolMais);
        btn_SkyVolMenos = (ImageButton) findViewById(R.id.btn_SkyVolMenos);
        btn_SkyChMais = (ImageButton) findViewById(R.id.btn_SkyChMais);
        btn_SkyChMenos = (ImageButton) findViewById(R.id.btn_SkyChMenos);
        btn_SkyUm = (ImageButton) findViewById(R.id.btn_SkyUm);
        btn_SkyDois = (ImageButton) findViewById(R.id.btn_SkyDois);
        btn_SkyTres = (ImageButton) findViewById(R.id.btn_SkyTres);
        btn_SkyQuatro = (ImageButton) findViewById(R.id.btn_SkyQuatro);
        btn_SkyCinco = (ImageButton) findViewById(R.id.btn_SkyCinco);
        btn_SkySeis = (ImageButton) findViewById(R.id.btn_SkySeis);
        btn_SkySete = (ImageButton) findViewById(R.id.btn_SkySete);
        btn_SkyOito = (ImageButton) findViewById(R.id.btn_SkyOito);
        btn_SkyNove = (ImageButton) findViewById(R.id.btn_SkyNove);
        btn_SkyZero = (ImageButton) findViewById(R.id.btn_SkyZero);


        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(meuBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        } else if (!meuBluetoothAdapter.isEnabled()) {
            Intent ativaBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativaBluetooth,SOLICITA_ATIVACAO);
        }

        btn_SkyOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyOn");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyGuia");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyVoltar");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkySair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkySair");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyCima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyCima");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyDireita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyDireita");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyEsquerda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyEsquerda");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyBaixo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyBaixo");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyVermelho");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyVerde");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyAmarelo");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyAzul");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyVolMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyVolMais");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyVolMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyVolMenos");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyChMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyChMais");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyChMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyChMenos");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyUm");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyDois");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyTres");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyQuatro");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });


        btn_SkyCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyCinco");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkySeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkySeis");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkySete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkySete");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyOito");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyNove");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_SkyZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("SkyZero");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
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
                        }
                        dadosBluetooth.delete(0,dadosBluetooth.length());
                    }
                }
            }
        };

    }

    /*@Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btncomodos){
            //Lógica de navegação

            //setContentView(R.layout.activity_sky); //Vai direto para o arquivo de layout sem passar pela Activity

            Intent intent = new Intent(getApplicationContext(), SkyActivity.class); //Vai para o arquivo de layout vinculado a Activity que está sendo chamada.
            startActivity(intent);
        }
    }*/

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
