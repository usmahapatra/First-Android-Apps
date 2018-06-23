package com.example.ujjwalsmahapatra.storereadfromcloudfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1;
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase f;
        final DatabaseReference ref;
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        b1=(Button) findViewById(R.id.button1);
        lv=(ListView)findViewById(R.id.listView1);

        f=FirebaseDatabase.getInstance();// f is pointing to our project
        ref=f.getReference("chats");//ref is pointing
        //listview related stuff
        al=new ArrayList<String >();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(adapter);

        //listen to the cloud database

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                al.clear();
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    String name=(String) child.child("name").getValue();
                    String message=(String) child.child("message").getValue();
                    //give name, message to the arrayList
                    al.add(name+"\n"+message);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString();
                String message=et2.getText().toString();
//now push the above two variables into the cloud database using ref variable
                //ref.child("name").setValue(name);//insert the data to the cloud
                //ref.child("dream").setValue(dream);//insert the data to the cloud
//
                HashMap<String,String> map1=new HashMap<String, String>();
                map1.put("name",name);
                map1.put("message",message);

                ref.push().setValue(map1);
                et1.setText("");
                et2.setText("");
                et1.requestFocus();

                //Toast.makeText(this,"inserted",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
