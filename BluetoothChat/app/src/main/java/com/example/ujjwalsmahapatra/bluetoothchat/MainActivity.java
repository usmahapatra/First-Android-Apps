package com.example.ujjwalsmahapatra.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice[] btArray;
    SendReceive sendReceive;

    Button listen,listDevices,send;
    ListView listView;
    EditText writeMsg;
    TextView msg_box,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();


            listen = (Button) findViewById(R.id.buttonListen);
            send = (Button) findViewById(R.id.button3);
            listDevices = (Button) findViewById(R.id.buttonListDevices);
            writeMsg = (EditText) findViewById(R.id.editTextWriteMsg);
            listView = (ListView) findViewById(R.id.listView);
            msg_box = (TextView) findViewById(R.id.textViewMsg);
            status = (TextView) findViewById(R.id.textviewStatus);



            if(bluetoothAdapter!=null){
                //this device  supports bluetooth
                if(!bluetoothAdapter.isEnabled())
                {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, 1);
                }
            }




        implementListeners();
    }

    private void implementListeners() {
        listDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> bt=bluetoothAdapter.getBondedDevices();

                String[] string=new  String[bt.size()];
                int i=0;
                if(bt.size()>0)
                {
                    for(BluetoothDevice device:bt){
                        btArray[i]=device;
                        string[i]=device.getName();
                        i++;
                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,string);

                    listView.setAdapter(arrayAdapter);
                }
            }
        });

       listen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ServerClass serverClass= new ServerClass();
               serverClass.start();
           }
       });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ClientClass clientClass=new ClientClass(btArray[i]);
               clientClass.start();
               status.setText("Connecting");
           }
       });
       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String string=(writeMsg.getText().toString());

               sendReceive.write(string.getBytes());
           }
       });
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch(message.what)
            {
                case 1:
                    status.setText("Listening");
                    break;
                case 2:
                    status.setText("Connecting");
                    break;
                case 3:
                    status.setText("Connecting");
                    break;
                case 4:
                    status.setText("Connected");
                    break;
                case 5:
                    status.setText("Connection Failed");
                    break;
                case 6:
                    //We will write itr later
                    //written now for message received
                    byte[] readBuff=(byte[])message.obj;
                    String tempMsg=new String(readBuff,0,message.arg1);
                    msg_box.setText(tempMsg);
                    break;
            }
            return true;
        }
    });

    //Creating a Server:

    public class ServerClass extends Thread{
        BluetoothServerSocket serverSocket;
        public  ServerClass(){
            try {
                serverSocket=bluetoothAdapter.listenUsingRfcommWithServiceRecord("Bt_chat", UUID.fromString("d7b78368-fb40-450e-b584-5bdbfa5d1f41"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run()
        {
            BluetoothSocket socket=null;
            while(socket==null){
                try {
                    Message message=Message.obtain();
                    message.what=2;
                    handler.sendMessage(message);
                    socket=serverSocket.accept();
                } catch (IOException e) {
                   e.printStackTrace();
                   Message message=Message.obtain();
                   message.what=4;
                   handler.sendMessage(message);
                }
            }
            if (socket!=null){


                Message message=Message.obtain();
                message.what=3;
                handler.sendMessage(message);

                //Do something for Sen/Receive:

                sendReceive=new SendReceive(socket);
                sendReceive.start();
            }

        }

    }
    public class ClientClass extends Thread{
         BluetoothDevice device;
         BluetoothSocket socket;

         public ClientClass(BluetoothDevice device1){
             device=device1;

             try {
                 socket=device.createRfcommSocketToServiceRecord(UUID.fromString("d7b78368-fb40-450e-b584-5bdbfa5d1f41"));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         public void run(){
             try {
                 socket.connect();
                 Message message=Message.obtain();
                 message.what=3;
                 handler.sendMessage(message);

                 sendReceive=new SendReceive(socket);
                 sendReceive.start();
             } catch (IOException e) {
                 e.printStackTrace();
                 Message message=Message.obtain();
                 message.what=4;
                 handler.sendMessage(message);
             }
         }

    }

    public class SendReceive extends Thread{
        final BluetoothSocket bluetoothSocket;
         final InputStream inputStream;
        final OutputStream outputStream;
        public SendReceive (BluetoothSocket socket){
            bluetoothSocket=socket;
            InputStream tempIn=null;
            OutputStream tempOut=null;

            try {
                tempIn=bluetoothSocket.getInputStream();
                tempOut=bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputStream=tempIn;
            outputStream=tempOut;
        }
        public void run(){
            byte[] buffer= new byte[1024];
            int bytes;

            while (true){
                try {
                    bytes=inputStream.read(buffer);
                    handler.obtainMessage(6,bytes,-1,buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void write(byte[] bytes){
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
