package com.example.ujjwalsmahapatra.fragmentofsecondactivitytofirstactivityfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;;
    TextView textView;
    public static final int requestCode=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button1);
        textView=(TextView)findViewById(R.id.text1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Second.class);
                startActivityForResult(i,requestCode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
            //No.1 child
            if(resultCode==RESULT_OK)
            {
                Bundle bundle=data.getExtras();
                String val=bundle.getString("value");
                textView.setText(val+"");
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
