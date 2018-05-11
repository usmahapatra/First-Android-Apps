package com.example.ujjwalsmahapatra.listviewinfragmenttoanotheractivityfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.ujjwalsmahapatra.listviewinfragmenttoanotheractivityfragment.R.layout.content_row;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String[] array={"Delhi","Mumbai","Bangalore"};

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        listView=(ListView) v.findViewById(R.id.listView1);
        arrayAdapter =new ArrayAdapter<String>(getActivity(), content_row,array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity m=(MainActivity) getActivity();
                m.catchData(array[i]);
            }
        });
        return v;
    }

}
