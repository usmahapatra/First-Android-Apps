package com.example.ujjwalsmahapatra.ujjwalmaxminerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myDatabase;
    TextView tv1;
    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.textView);
        e1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        myDatabase= FirebaseDatabase.getInstance().getReference("Message");
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null)
            tv1.setText(dataSnapshot.getValue().toString());
                else tv1.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                tv1.setText("Cancelled");
            }
        });
    }

        public void sendMessage(){
        myDatabase.setValue(e1.getText().toString());
        e1.setText("");
        }


}
