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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public ViewHolder mViewHolder = new ViewHolder();

    Button btnConexao, btn_Led4;


    public static final int SOLICITA_ATIVACAO = 1;
    public static final int SOLICITA_CONEXAO = 2;
    public static final int MESSAGE_READ = 3;

    ConnectedThread connectedThread;

    Handler mHandler;
    StringBuilder dadosBluetooth = new StringBuilder();

    BluetoothAdapter meuBluetoothAdapter = null;
    BluetoothDevice meuDevice = null;
    BluetoothSocket meuSocket = null;

    boolean conexao = false;

    private static String MAC = null;

    UUID MEU_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.btn_Sky = (Button) findViewById(R.id.btn_Sky);
        this.mViewHolder.btn_Sky.setOnClickListener(this);

        this.mViewHolder.btn_Tv = (Button) findViewById(R.id.btn_Tv);
        this.mViewHolder.btn_Tv.setOnClickListener(this);

        this.mViewHolder.btn_Quarto = (Button) findViewById(R.id.btn_Quarto);
        this.mViewHolder.btn_Quarto.setOnClickListener(this);

        this.mViewHolder.btn_Sala = (Button) findViewById(R.id.btn_Sala);
        this.mViewHolder.btn_Sala.setOnClickListener(this);

        btnConexao = (Button)findViewById(R.id.btnConexao);
        btn_Led4 = (Button)findViewById(R.id.btn_Led4);

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnConexao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    //desconectar
                    try{
                        meuSocket.close();
                        conexao = false;
                        btnConexao.setText("Conectar");
                        Toast.makeText(getApplicationContext(), "Bluetooth foi desconectado", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //conectar
                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);
                }
            }
        });

        btn_Led4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    connectedThread.enviar("led4");

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
                            //Log.d("Recebidos", dadosFinais);

                            if (dadosFinais.contains("led4on")){
                                btn_Led4.setText("Led Ligado");
                            }else if (dadosFinais.contains("led4off")) {
                                btn_Led4.setText("Led Deslidago");
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_Sky){
            //Lógica de navegação

            //setContentView(R.layout.activity_sky); //Vai direto para o arquivo de layout sem passar pela Activity

            Intent intent = new Intent(getApplicationContext(), SkyActivity.class); //Vai para o arquivo de layout vinculado a Activity que está sendo chamada.
            startActivity(intent);
        }

        if (id == R.id.btn_Tv){
            //Lógica de navegação

            //setContentView(R.layout.activity_sky); //Vai direto para o arquivo de layout sem passar pela Activity

            Intent intent = new Intent(getApplicationContext(), TvActivity.class); //Vai para o arquivo de layout vinculado a Activity que está sendo chamada.
            startActivity(intent);
        }

        if (id == R.id.btn_Quarto){
            //Lógica de navegação

            //setContentView(R.layout.activity_sky); //Vai direto para o arquivo de layout sem passar pela Activity

            Intent intent = new Intent(getApplicationContext(), QuartoActivity.class); //Vai para o arquivo de layout vinculado a Activity que está sendo chamada.
            startActivity(intent);
        }

        if (id == R.id.btn_Sala){
            //Lógica de navegação

            //setContentView(R.layout.activity_sky); //Vai direto para o arquivo de layout sem passar pela Activity

            Intent intent = new Intent(getApplicationContext(), SalaActivity.class); //Vai para o arquivo de layout vinculado a Activity que está sendo chamada.
            startActivity(intent);
        }

    }

    public static class ViewHolder{
        Button btn_Sky, btn_Tv, btn_Quarto, btn_Sala;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case SOLICITA_ATIVACAO:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), " O bluetooth foi ativado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " O bluetooth  não foi ativado, o app foi encerrado", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case SOLICITA_CONEXAO:
                if (resultCode == Activity.RESULT_OK) {
                    MAC = Objects.requireNonNull(data.getExtras()).getString(ListaDispositivos.ENRERECO_MAC);

                    //Toast.makeText(getApplicationContext(), " MAC FINAL" + MAC, Toast.LENGTH_LONG).show();
                    meuDevice = meuBluetoothAdapter.getRemoteDevice(MAC);

                    try {
                        meuSocket = meuDevice.createRfcommSocketToServiceRecord(MEU_UUID);

                        meuSocket.connect();

                        conexao = true;

                        connectedThread = new ConnectedThread(meuSocket);
                        connectedThread.start();

                        btnConexao.setText("Desconectar");
                        Toast.makeText(getApplicationContext(), "Você foi conectado com: " + MAC, Toast.LENGTH_LONG).show();

                    }catch (IOException erro){

                        conexao = false;
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro:" + erro, Toast.LENGTH_LONG).show();

                    }

                } else{
                    Toast.makeText(getApplicationContext(), " Falha ao obter o endereço MAC", Toast.LENGTH_LONG).show();
                }
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
