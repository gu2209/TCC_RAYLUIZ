package com.example.android.tcc_ray_luiz;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class TvActivity extends AppCompatActivity{

    ImageButton btn_TvOn,btn_TvOff, btn_TvGuia, btn_TvVoltar,btn_TvSair, btn_TvCima, btn_TvDireita, btn_TvEsquerda,
            btn_TvBaixo, btn_TvVermelho, btn_TvVerde, btn_TvAmarelo, btn_TvAzul, btn_TvVolMais, btn_TvVolMenos,
            btn_TvChMais, btn_TvChMenos, btn_TvUm, btn_TvDois,btn_TvTres, btn_TvQuatro,btn_TvCinco, btn_TvSeis,
            btn_TvSete, btn_TvOito, btn_TvNove, btn_TvZero;


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
        setContentView(R.layout.activity_tv);

        btn_TvOn = (ImageButton) findViewById(R.id.btn_TvOn);
        btn_TvOff = (ImageButton) findViewById(R.id.btn_TvOff);
        btn_TvGuia = (ImageButton) findViewById(R.id.btn_TvGuia);
        btn_TvVoltar = (ImageButton) findViewById(R.id.btn_TvVoltar);
        btn_TvSair = (ImageButton) findViewById(R.id.btn_TvSair);
        btn_TvCima = (ImageButton) findViewById(R.id.btn_TvCima);
        btn_TvDireita = (ImageButton) findViewById(R.id.btn_TvDireita);
        btn_TvEsquerda = (ImageButton) findViewById(R.id.btn_TvEsquerda);
        btn_TvBaixo = (ImageButton) findViewById(R.id.btn_TvBaixo);
        btn_TvVermelho = (ImageButton) findViewById(R.id.btn_TvVermelho);
        btn_TvVerde = (ImageButton) findViewById(R.id.btn_TvVerde);
        btn_TvAmarelo = (ImageButton) findViewById(R.id.btn_TvAmarelo);
        btn_TvAzul = (ImageButton) findViewById(R.id.btn_TvAzul);
        btn_TvVolMais = (ImageButton) findViewById(R.id.btn_TvVolMais);
        btn_TvVolMenos = (ImageButton) findViewById(R.id.btn_TvVolMenos);
        btn_TvChMais = (ImageButton) findViewById(R.id.btn_TvChMais);
        btn_TvChMenos = (ImageButton) findViewById(R.id.btn_TvChMenos);
        btn_TvUm = (ImageButton) findViewById(R.id.btn_TvUm);
        btn_TvDois = (ImageButton) findViewById(R.id.btn_TvDois);
        btn_TvTres = (ImageButton) findViewById(R.id.btn_TvTres);
        btn_TvQuatro = (ImageButton) findViewById(R.id.btn_TvQuatro);
        btn_TvCinco = (ImageButton) findViewById(R.id.btn_TvCinco);
        btn_TvSeis = (ImageButton) findViewById(R.id.btn_TvSeis);
        btn_TvSete = (ImageButton) findViewById(R.id.btn_TvSete);
        btn_TvOito = (ImageButton) findViewById(R.id.btn_TvOito);
        btn_TvNove = (ImageButton) findViewById(R.id.btn_TvNove);
        btn_TvZero = (ImageButton) findViewById(R.id.btn_TvZero);


        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btn_TvOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvOn");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvOff");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvGuia");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvVoltar");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvSair");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvCima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvCima");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvDireita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvDireita");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvEsquerda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvEsquerda");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvBaixo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvBaixo");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvVermelho");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvVerde");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvAmarelo");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvAzul");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvVolMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvVolMais");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvVolMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvVolMenos");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvChMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvChMais");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvChMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvChMenos");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvUm");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvDois");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvTres");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvQuatro");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });


        btn_TvCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvCinco");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvSeis");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvSete");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvOito");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvNove");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn_TvZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("TvZero");

                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não está conectado", Toast.LENGTH_LONG).show();

                }
            }
        });

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

            mmOutStream = tmpOut;
        }

        public void enviar (String dadosEnviar) {
            byte[] msgBuffer = dadosEnviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) { }
        }
    }
}
