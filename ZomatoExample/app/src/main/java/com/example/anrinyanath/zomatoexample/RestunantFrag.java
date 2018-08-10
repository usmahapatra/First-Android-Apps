package com.example.anrinyanath.zomatoexample;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
public class RestunantFrag extends Fragment {

    Button getRes;
    RecyclerView resList;
    LocationManager manager;//for getting phone locations
    ArrayList<Resturant> resturants;
    ResAdapter resAdapter;
    MyTask myTask;
    LinearLayoutManager lManager;
    double latitude=0;
    double longitude=0;
    String resLat;
    String resLong;
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            Toast.makeText(getActivity(), "Long = "+longitude, Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Lat = "+latitude, Toast.LENGTH_SHORT).show();
            Log.d("lat",""+latitude);
            Log.d("long",""+longitude);
            manager.removeUpdates(listener);
            if(latitude!=0&&longitude!=0) {
                myTask.execute("https://developers.zomato.com/api/v2.1/geocode?lat="+latitude+"&lon="+longitude);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText(getActivity(), "Please enable gps", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public class MyTask extends AsyncTask<String, Void, StringBuilder> {

        URL myUrl;
        HttpURLConnection con;
        InputStream i;
        InputStreamReader ir;
        BufferedReader br;
        String s;
        StringBuilder sb;

        @Override
        protected StringBuilder doInBackground(String... strings) {
            try {
                myUrl=new URL(strings[0]);
                con= (HttpURLConnection) myUrl.openConnection();
                //authentication steps
                con.setRequestProperty("user-key","41ba7eee0ddcc772e0ff7be3ce32e840");
                con.setRequestProperty("Accept","application/json");
                //end
                i=con.getInputStream();
                ir=new InputStreamReader(i);
                br=new BufferedReader(ir);
                sb=new StringBuilder();
                s=br.readLine();
                while (s!=null){
                    sb.append(s);
                    s=br.readLine();
                }
            } catch (MalformedURLException e) {
                Log.d("abcMalError",""+e);
            } catch (IOException e) {
                Log.d("abcIOException",""+e);
            }
            return sb;
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            super.onPostExecute(stringBuilder);
            //String str=stringBuilder.toString();
            //Toast.makeText(getActivity(), ""+stringBuilder, Toast.LENGTH_SHORT).show();
            try {
                JSONObject obj=new JSONObject(stringBuilder.toString());
                JSONArray arr=obj.getJSONArray("nearby_restaurants");
                for(int i=0;i<arr.length();i++){
                    JSONObject k=arr.getJSONObject(i);
                    JSONObject j=k.getJSONObject("restaurant");
                    String resName=j.getString("name");
                    //Toast.makeText(getActivity(), resName, Toast.LENGTH_SHORT).show();
                    JSONObject q=j.getJSONObject("location");
                    String resAddress=q.getString("address");
                    String resLocality=q.getString("locality");
                    resLat=q.getString("latitude");
                    resLong=q.getString("longitude");
                    String offerText="";
                    JSONArray offerArr=j.getJSONArray("offers");
                    for(int s=0;s<offerArr.length();s++){
                        JSONObject off=offerArr.getJSONObject(i);
                        JSONObject offer=off.getJSONObject("offer");
                        offerText=offer.getString("offer_text");
                    }
                    String photoUrl=j.getString("thumb");
                    JSONObject urate=j.getJSONObject("user_rating");
                    String userRate=urate.getString("aggregate_rating");
                    //Toast.makeText(getActivity(), resAddress, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), resName, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), resAddress, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), resLocality, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), resLat, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), resLong, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), offerText, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), photoUrl, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), userRate, Toast.LENGTH_SHORT).show();
                    Resturant res=new Resturant(resName,resAddress,resLocality,photoUrl,resLat,resLong,userRate,offerText);
                    resturants.add(res);
                    resAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                Log.d("abcExceptionis : ",""+e);
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.row,parent,false);
            ViewHolder vh= new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Resturant rs=resturants.get(position);
            Glide.with(getActivity()).load(rs.getImageurl()).into(holder.imageView);
            holder.nameText.setText(rs.getName());
            holder.addText.setText(rs.getAddress());
            holder.offerText.setText(rs.getOffers());
            holder.ratingBar.setRating(Float.valueOf(rs.getRating()));
        }

        @Override
        public int getItemCount() {
            return resturants.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView nameText,addText,offerText;
            RatingBar ratingBar;
            CardView cv;
            public ViewHolder(View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.imageView1);
                nameText=itemView.findViewById(R.id.textView1);
                addText=itemView.findViewById(R.id.textView2);
                offerText=itemView.findViewById(R.id.textView3);
                ratingBar=itemView.findViewById(R.id.ratingBar1);
                cv=itemView.findViewById(R.id.cardView1);
                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sp=getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("latitude",resLat);
                        ed.putString("longitude",resLong);
                        ed.apply();
                        HomeActivity homeActivity= (HomeActivity) getActivity();
                        homeActivity.startMap();
                    }
                });
            }
        }
    }


    public RestunantFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_restunant, container, false);
        getRes = v.findViewById(R.id.get_restaurents);
        resList = v.findViewById(R.id.rest_list);
        myTask = new MyTask();
        lManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        resAdapter = new ResAdapter();
        resList.setAdapter(resAdapter);
        resList.setLayoutManager(lManager);
        resturants=new ArrayList<Resturant>();
        getRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //Toast.makeText(getActivity(), "give location permission", Toast.LENGTH_SHORT).show();
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, listener);
            }

        });
        return v;
    }
    public boolean checkInternet(){
        ConnectivityManager manager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.isConnected()==true){
            return true;
        }
        return false;
    }

}
