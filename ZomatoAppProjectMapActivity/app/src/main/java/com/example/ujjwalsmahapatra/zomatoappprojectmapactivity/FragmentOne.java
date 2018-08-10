package com.example.ujjwalsmahapatra.zomatoappprojectmapactivity;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    Button b;
    RecyclerView rv;
    ArrayList<Restaurant> al;
    MyAdapter myAdapter;
    LinearLayoutManager manager;
    MyTask myTask;
    double latitude, longitude;
    LocationManager locationManager;
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //we got our locations..
            latitude =location.getLatitude();
            longitude=location.getLongitude();
            Toast.makeText(getActivity(), "lat = "+latitude,Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "lon = "+longitude,Toast.LENGTH_SHORT).show();
            locationManager.removeUpdates(listener);

            myTask.execute("https://developers.zomato.com/api/v2.1/geocode?lat="+latitude+"&lon="+longitude);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {
Toast.makeText(getActivity(),"plz enable gps",Toast.LENGTH_SHORT).show();
        }
    };

    /**********************************VARIABLE DECLARATION UPTO HERE****************************************************************/
    public class MyTask extends AsyncTask<String, Void, StringBuilder> {
        URL url;
        HttpURLConnection con;
        InputStream i;
        InputStreamReader ir;
        BufferedReader br;
        StringBuilder sb;
        String s;


        @Override
        protected StringBuilder doInBackground(String... strings) {
            try {
                url=new URL(strings[0]);
                con= (HttpURLConnection) url.openConnection();
                con.setRequestProperty("user-key","");
                con.setRequestProperty("Accept","application/json");
                i=con.getInputStream();
                ir=new InputStreamReader(i);
                br=new BufferedReader(ir);
                /***************/
                sb=new StringBuilder();
                s=br.readLine();
                while (s!=null){
                    sb.append(s);
                    s=br.readLine();
                }
                return sb;
            } catch (MalformedURLException e) {
                Log.d("first","first");
            } catch (IOException e) {
               Log.d("second","second");
            }
            return null;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            super.onPostExecute(stringBuilder);
            Toast.makeText(getActivity(),stringBuilder,Toast.LENGTH_SHORT).show();
            try {
                JSONObject obj=new JSONObject(String.valueOf(stringBuilder));
                JSONArray arr=obj.getJSONArray("nearby_restaurants");
                for(int i=0;i<arr.length();i++){
                    JSONObject obj2=arr.getJSONObject(i);
                    JSONObject obj3=obj2.getJSONObject("restaurant");
                    String res_name=obj3.getString("name");
                    JSONObject obj4=obj3.getJSONObject("location");
                    String res_address=obj4.getString("address");
                    String res_locality=obj4.getString("locality");
                    String res_image=obj3.getString("thumb");
                    JSONObject obj5=obj3.getJSONObject("user_rating");
                    String res_rating=obj5.getString("aggregate_rating");
                    String res_lat=obj4.getString("latitude");
                    String res_long=obj4.getString("longitude");

                    //create Restaurant object:

                    Restaurant r=new Restaurant(res_name,res_address,res_locality,res_image,res_lat,res_long,res_rating);
                    al.add(r);
                    myAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                Log.d("B41","checkJSON Errors");
            }

        }
    }

    public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=getActivity().getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh =new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Restaurant r=al.get(position);
            holder.t1.setText(r.getRes_name());
            holder.t2.setText(r.getRes_address());
            holder.t3.setText(r.getRes_locality());
holder.rb.setRating(Float.parseFloat(r.getRes_rating()));
Glide.with(getActivity()).load(r.getRes_imageurl()).into(holder.iv);

        }

        @Override
        public int getItemCount() {
            return al.size();
        }
    }


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        b = v.findViewById(R.id.button1);
        rv = v.findViewById(R.id.recyclerView1);
        al = new ArrayList<>();
        myAdapter = new MyAdapter();
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(manager);
        myTask = new MyTask();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will ask location manager for locations
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(getActivity(),"LocationPermission Denied",Toast.LENGTH_SHORT).show();
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 100, listener);
            }
        });
        return v;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        RatingBar rb;
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView3);
            t2=itemView.findViewById(R.id.textView1);
            t3=itemView.findViewById(R.id.textView3);
            iv=itemView.findViewById(R.id.imageView);
            rb=itemView.findViewById(R.id.ratingbar);
        }
    }
}
