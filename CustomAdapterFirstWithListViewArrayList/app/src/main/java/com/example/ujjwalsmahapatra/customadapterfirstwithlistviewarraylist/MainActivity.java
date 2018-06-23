package com.example.ujjwalsmahapatra.customadapterfirstwithlistviewarraylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    ListView listView1;
    ArrayList<Employee> arrayList1;
    //No need to provide datatype for the custom adapter:
    MyAdapter m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialisation

        button1=(Button)findViewById(R.id.button1);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        listView1=(ListView)findViewById(R.id.listView1);

        //ArrayList initialization(Normally I forget to Initialize the ArrayList):

        arrayList1=new ArrayList<Employee>();

        //Adapter Initialization
        m=new MyAdapter();

        //link from arrayList to the arrayAdapter is not needed because my adapter is defined inside the class itself:


        //Link listView to the adapter

        listView1.setAdapter(m);

        //buttonclick

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee e1=new Employee();
                e1.setEmpName(editText1.getText().toString());
                e1.setEmpSal(editText2.getText().toString());
                e1.setEmpDesignation(editText3.getText().toString());

                //add to the arraylist

                arrayList1.add(e1);
                m.notifyDataSetChanged();





                //clear the editTexts

                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                //put the cursor in editText1:
                editText1.requestFocus();

            }
        });

    }
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList1.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList1.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //get the Item
            final Employee item=arrayList1.get(i);

            //load the xml into a view object

            View v=getLayoutInflater().inflate(R.layout.activity_main2,null);

            TextView textV1=(TextView) v.findViewById(R.id.textView1);
            TextView textV2=(TextView) v.findViewById(R.id.textView2);
            TextView textV3=(TextView) v.findViewById(R.id.textView3);
            TextView textV=(TextView) v.findViewById(R.id.textView);

           final CheckBox checkBox1=(CheckBox) v.findViewById(R.id.checkBox1);

            //assign some data to the view elements
            textV.setText((i+1)+"");
            textV1.setText(item.getEmpName());
            textV2.setText(item.getEmpSal());
            textV3.setText(item.getEmpDesignation());

            //ListView if clicked and checkbox is checked: delete the item:

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    for(int j=arrayList1.size();j>=0;j--) {
                        if (checkBox1.isChecked()) {
                            Toast.makeText(MainActivity.this, item.getEmpName()+"\nChecked Item \n is DELETED", Toast.LENGTH_LONG).show();
                            arrayList1.remove(item);

                        }
                    }
                        m.notifyDataSetChanged();


                }

            });

              // return row.xml
            return v;
        }
    }
}
