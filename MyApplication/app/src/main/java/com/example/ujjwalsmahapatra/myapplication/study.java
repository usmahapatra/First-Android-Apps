package com.nscpl.remedi_mobile.doctor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.firebase.crash.FirebaseCrash;
import com.neurosynaptic.remedi_nova.R;
import com.nscpl.remedi_mobile.bleParameters.ble.MyApplication;
import com.nscpl.remedi_mobile.nurse.activities.NurseScreen;
import com.nscpl.remedi_mobile.nurse.appointmentList.AppointmentList;
import com.nscpl.remedi_mobile.nurse.common.Config;
import com.nscpl.remedi_mobile.nurse.common.DoctorDetails;
import com.nscpl.remedi_mobile.nurse.common.FileOperations;
import com.nscpl.remedi_mobile.nurse.common.ForgotPassword;
import com.nscpl.remedi_mobile.nurse.common.LoginDetails;
import com.nscpl.remedi_mobile.nurse.common.MyDatabase;
import com.nscpl.remedi_mobile.nurse.common.NurseDetails;
import com.nscpl.remedi_mobile.nurse.common.Utilss;
import com.nscpl.remedi_mobile.nurse.consultation.pastRecords.MyTask;
import com.nscpl.remedi_mobile.nurse.searchPatient.MacInc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

public class LoginScreen extends Activity {

    private static final String TAG = LoginScreen.class.getSimpleName();
    Button login;
    EditText username, password, domain_name;
    MyDatabase database;
    String offset, offsetSubString;
    static String uname, pswd, dName;
    String loginTime,domainexpiry, PATIENT_PHOTO_PATH, DOCTOR_SIGN_PATH, PATIENT_PASTRECORD_PATH, NURSE_PHOTO_PATH, DOCTOR_PHOTO_PATH,phonelength;
    String pass, name, qualify, maleOrFemale, DateOfBirth, specialization, mobile, domain, usertype, user_name, profileImage;
    String id, country, fee, sign, createdAt, isDeleted, doctorId, block, state, village, email, honorific, forAndroid, registrationNumber, district,
            organization, designation, domainName, domainId, videomode, createdby, dateformat;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String MYPREFERENCES_OFFINE = "MyPrefOffline";
    SharedPreferences preferencesOffline;
    SharedPreferences sharedpreferences;
    String url, masterUrl, url1;
    String imagepath, signpath;
    Context context = this;
    String deviceid;
    Utilss utilss = new Utilss();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_login_screen);

        TextView logo = (TextView) findViewById(R.id.logo_remedi);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "font/roboto_light.ttf");
        logo.setTypeface(font1);

        TextView logo2 = (TextView) findViewById(R.id.logo_nova);
        logo2.setTypeface(font1);

        domain_name = (EditText) findViewById(R.id.domain);

        TextView forgot_pass = (TextView) findViewById(R.id.forgot_pass);


        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginScreen.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            TelephonyManager tm = (TelephonyManager)
                    getSystemService(this.TELEPHONY_SERVICE);
            deviceid = tm.getDeviceId();
            Log.d("device Id==", " " + deviceid);

        }


        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseCrash.log(TAG + ":forgot_pass clickd");
                Intent in = new Intent(LoginScreen.this, ForgotPassword.class);
                startActivity(in);
            }
        });


        login = (Button) findViewById(R.id.loginn);
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        url = Config.getInstance(this).getProperty("COMMON_OPERATIONS_URL");
        masterUrl = Config.getInstance(this).getProperty("MASTER_OPERATIONS_URL");
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        preferencesOffline = getSharedPreferences(MYPREFERENCES_OFFINE, Context.MODE_PRIVATE);
        database = new MyDatabase(LoginScreen.this);
        database.open();

        //******Code for getting offset***************

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        offset = timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5);
        Log.d("offset", "" + offset);
        offsetSubString = offset.substring(1);
        Log.d("sub", "" + offsetSubString);


        database.open();
        dName = database.checkAnyUser();
        Log.d("dname===", " " + dName);
        if (!dName.equals("false")) {
            domain_name.setVisibility(View.GONE);
        }

        //******Login Button OnClick***************

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseCrash.log(TAG + ":login clickd");

                if ((ContextCompat.checkSelfPermission(LoginScreen.this, Manifest.permission.READ_PHONE_STATE)) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LoginScreen.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                } else {
                    TelephonyManager tm = (TelephonyManager)
                            getSystemService(LoginScreen.this.TELEPHONY_SERVICE);
                    deviceid = tm.getDeviceId();
                    MyApplication.thisDeviceId = MacInc.md5(deviceid);

                    Log.d("device Id==", " " + deviceid);

                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(LoginScreen.this.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {

                        uname = username.getText().toString().trim();
                        pswd = password.getText().toString();
                        FirebaseCrash.log(TAG + ":uname=" + uname);
                        FirebaseCrash.log(TAG + ":pass=" + pswd);
                        if (dName.equals("false"))
                            dName = domain_name.getText().toString();
                        Log.d("dname value==", "" + dName);

                        FirebaseCrash.log(TAG + ":dname=" + dName);
                        if (uname.trim().equals("") && pswd.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enter_usernamepwd, Toast.LENGTH_SHORT).show();
                        } else if (uname.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterusername, Toast.LENGTH_SHORT).show();
                        } else if (pswd.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterpassword, Toast.LENGTH_SHORT).show();
                        } else if (dName.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterdomainname, Toast.LENGTH_SHORT).show();
                        } else {
                            if (dName != null) {
                                if (Config.getInstance(LoginScreen.this).getProperty("COMMON_OPERATIONS_URL").equals(".remedi.co.in/RemediPRMS/RemediNovaAPI.do")) {
                                    url1 = Config.getInstance(LoginScreen.this).getProperty("HTTP_URL") + dName + Config.getInstance(LoginScreen.this).getProperty("COMMON_OPERATIONS_URL");
                                    Log.d("came in ", " changing url");
                                    masterUrl = Config.getInstance(LoginScreen.this).getProperty("HTTP_URL") + dName + Config.getInstance(LoginScreen.this).getProperty("MASTER_OPERATIONS_URL");
                                    Config.getInstance(LoginScreen.this).setPropertyToAll(dName);
                                } else {
                                    url1 = Config.getInstance(LoginScreen.this).getProperty("COMMON_OPERATIONS_URL");
                                    masterUrl = Config.getInstance(LoginScreen.this).getProperty("MASTER_OPERATIONS_URL");
                                }
                                sendLoginDataToServer(dName.trim());

                            }
                        }
                    } else {
                        if (dName.equals("false"))
                            dName = domain_name.getText().toString();
                        uname = username.getText().toString().trim();
                        String  passwordkey = password.getText().toString();
                        pswd=Utilss.hashCal(passwordkey+Config.getInstance(LoginScreen.this).getProperty("SECRET_KEY"));


                        if (uname.trim().equals("") && pswd.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enter_usernamepwd, Toast.LENGTH_SHORT).show();
                        } else if (uname.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterusername, Toast.LENGTH_SHORT).show();
                        } else if (pswd.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterpassword, Toast.LENGTH_SHORT).show();
                        } else if (dName.trim().equals("")) {
                            Toast.makeText(context, R.string.please_enterdomainname, Toast.LENGTH_SHORT).show();
                        } else if (dName != null) {

                            database.open();
                    /*String UserPresent = database.checkLogin(uname, pswd);
                    Log.d("UserLogin", "" + UserPresent);
                    if (UserPresent != null) {*/
                            Boolean checkUserName = database.checkUserName(uname);
                            if (checkUserName) {

                                String usertype = database.checkusertype(uname);
                                Log.d("usertypeFirstTimeReg", "" + usertype);
                                boolean data = database.checkLocalRegistration(uname, usertype);
                                if (data) {
                                    String userPassword = database.findPassword(uname);

                                    if (pswd.equalsIgnoreCase(userPassword)) {
                                        MyApplication.usertype = usertype;
                                        MyApplication.thisDeviceUsername = uname;
                                        //  String usertype=database.checkusertype(uname);
                                        database.updateLoginDetail(uname);
                                        database.insertLogintimeDetails(uname, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

                                        if (usertype.equalsIgnoreCase("Nurse")) {
                                            NurseDetails n = database.queryNurseDetails(uname);
                                            final String nurseId, username, dob, name, honorific, logintime, domainname, domainid, nursepic, gender, mobileNO;
                                            nurseId = n.getNurseId();
                                            username = n.getUserName();
                                            dob = n.getDob();
                                            name = n.getName();
                                            honorific = n.getHonorific();
                                            logintime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                                            domain = n.getDomain();
                                            domainid = n.getDomain();
                                            nursepic = n.getProfile();
                                            dateformat = n.getDateformat();
                                            Log.d("dateform offline login", "" + dateformat);
                                            gender = n.getGender();
                                            mobileNO = n.getMobile();
                                            domainId = database.checkDomainId(uname);

                                            LoginDetails l = new LoginDetails();
                                            l.setUsername(uname);
                                            l.setUsertype(usertype);
                                            l.setPassword(pswd);
                                            l.setSessionId("loggedin");
                                            l.setLoginCount("1");
                                            l.setInvalidCount("");
                                            l.setDomainId(domainId);
                                            l.setIsEnabled("1");
                                            l.setTimeOffset(offsetSubString);
                                            l.setIsupdated("1");
                                            l.setCurrentconsultation("-1");

                                            database.insertLoginDetail(l);

                                            SharedPreferences sharedPrefs = getSharedPreferences("userSession", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPrefs.edit();
                                            editor.putString("logintime", logintime);
                                            editor.putString("usertype", usertype);
                                            editor.putString("domainid", domainId);
                                            editor.putString("domain", domain);
                                            editor.putString("domainname", domain);
                                            editor.putString("dateformat", dateformat);

                                            if (Config.getInstance(LoginScreen.this).getProperty("COMMON_OPERATIONS_URL").equals(".remedi.co.in/RemediPRMS/RemediNovaAPI.do")) {
                                                Config.getInstance(LoginScreen.this).setPropertyToAll(domain);
                                            }

                                            editor.putString("phonelength",n.getPhonelength());

                                            editor.putString("gender", gender);
                                            editor.putString("mobile", mobileNO);
                                            Log.d("ns id", " " + usertype);

                                            editor.putString("nurseid", nurseId);
                                            Log.d("ns id", " " + nurseId);
                                            editor.putString("userName", username);
                                            editor.putString("name", name);
                                            editor.putString("dob", dob);
                                            editor.putString("horrific", honorific);
                                            editor.putString("nursepic", nursepic);
                                            editor.commit();

                                            SharedPreferences.Editor editor1 = preferencesOffline.edit();
                                            editor1.putString("loginDetail", "loggedin");
                                            editor1.putString("offline", "nurse");
                                            editor1.commit();

                                            Intent in = new Intent(LoginScreen.this, NurseScreen.class);
                                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            in.putExtra("userName", uname);
                                            startActivity(in);
                                            finish();
                                        } else if (usertype.equalsIgnoreCase("Doctor")) {
                                            DoctorDetails d = database.queryDoctorDetails(uname);
                                            final String doctorId, username, dob, name, honorific, logintime, domainname, domainid, doctorpic, gender, mobileNO;
                                            doctorId = d.getDoctorId();
                                            username = d.getUserName();
                                            dob = d.getDob();
                                            dateformat = d.getDateformat();
                                            name = d.getName();
                                            honorific = d.getHonorific();
                                            logintime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                                            domain = d.getDomain();

                                            if (Config.getInstance(LoginScreen.this).getProperty("COMMON_OPERATIONS_URL").equals(".remedi.co.in/RemediPRMS/RemediNovaAPI.do")) {
                                                Config.getInstance(LoginScreen.this).setPropertyToAll(domain);

                                            }
                                            domainId = database.checkDomainId(uname);


                                            domainid = d.getDomain();
                                            doctorpic = d.getProfile();
                                            gender = d.getGender();
                                            mobileNO = d.getMobile();

                                            LoginDetails l = new LoginDetails();
                                            l.setUsername(uname);
                                            l.setUsertype(usertype);
                                            l.setPassword(pswd);
                                            l.setSessionId("loggedin");
                                            l.setLoginCount("1");
                                            l.setInvalidCount("");
                                            l.setDomainId(domainId);
                                            l.setIsEnabled("1");
                                            l.setTimeOffset(offsetSubString);
                                            l.setIsupdated("1");
                                            l.setCurrentconsultation("-1");

                                            database.insertLoginDetail(l);

                                            SharedPreferences sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPrefs.edit();

                                            editor.putString("country", country);
                                            editor.putString("fee", fee);
                                            editor.putString("sign", sign);
                                            editor.putString("createdAt", createdAt);
                                            editor.putString("isDeleted", isDeleted);
                                            editor.putString("doctorId", doctorId);
                                            editor.putString("block", block);
                                            editor.putString("state", state);
                                            editor.putString("village", village);
                                            editor.putString("email", email);
                                            editor.putString("honorific", honorific);
                                            editor.putString("forAndroid", forAndroid);
                                            editor.putString("registrationNumber", registrationNumber);
                                            editor.putString("district", district);
                                            editor.putString("organization", organization);
                                            editor.putString("designation", designation);
                                            editor.putString("domainName", domain);
                                            editor.putString("domainId", domainId);
                                            editor.putString("name", name);
                                            editor.putString("pass", pass);
                                            editor.putString("qualify", qualify);
                                            editor.putString("maleOrFemale", gender);
                                            Log.d("genderLogin", "" + gender);
                                            editor.putString("DateOfBirth", dob);
                                            editor.putString("specialization", specialization);
                                            editor.putString("mobile", mobileNO);
                                            editor.putString("domain", domain);
                                            editor.putString("usertype", usertype);
                                            editor.putString("DOCTOR_PHOTO_PATH", DOCTOR_PHOTO_PATH);
                                            editor.putString("profileImage", profileImage);
                                            editor.putString("loginTime", loginTime);
                                            editor.putString("userName", uname);
                                            editor.putString("dateformat", dateformat);
                                            editor.putString("phonelength",d.getPhonelength());

                                            editor.putString("NURSE_PHOTO_PATH", NURSE_PHOTO_PATH);
                                            editor.putString("PATIENT_PHOTO_PATH", PATIENT_PHOTO_PATH);
                                            editor.putString("DOCTOR_SIGN_PATH", DOCTOR_SIGN_PATH);
                                            editor.putString("PATIENT_PASTRECORD_PATH", PATIENT_PASTRECORD_PATH);

                                            editor.commit();

                                            SharedPreferences.Editor editor1 = preferencesOffline.edit();
                                            editor1.putString("username", uname);
                                            editor1.putString("OfflineLogin", "offline");
                                            editor1.putString("loginDetail", "loggedin");
                                            editor1.putString("offline", "doctor");
                                            editor1.commit();


                                            Intent in = new Intent(LoginScreen.this, HomeScreen.class);
                                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(in);
                                            //Toast.makeText(LoginScreen.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                                            LoginScreen.this.finish();
                                        }
                                    } else {
                                        Toast.makeText(LoginScreen.this, R.string.incorrect_password, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginScreen.this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(LoginScreen.this, R.string.goOnline_toLogin, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }

        });

    }
    //...................sending login information to server................................................//

    public void sendLoginDataToServer(final String name_domain) {

        Log.d("came", " here" + name_domain);
        uname = username.getText().toString().trim();
        String passwordKey = password.getText().toString();
        pswd=Utilss.hashCal(passwordKey+Config.getInstance(LoginScreen.this).getProperty("SECRET_KEY"));


        final ProgressDialog progressDialog = ProgressDialog.show(LoginScreen.this, getString(R.string.please_wait), getString(R.string.task_inProgress));
        progressDialog.setCancelable(true);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {


                Log.d("Response", response);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    final String res = jsonObject.getString("status").trim();

                    if (res.equalsIgnoreCase("success")) {
                        JSONObject jsonCommonDetail = jsonObject.getJSONObject("commondetail");
                        domainexpiry = jsonCommonDetail.getString("domainExpDate").trim();
                        Date expDate = new SimpleDateFormat("dd/MM/yyyy").parse(domainexpiry);
                        Log.d("expiry date is==>", " " + domainexpiry);
                        if (expDate.before(new Date())) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Domain is Expired", Toast.LENGTH_SHORT).show();
                        }else
                        {

                            final JSONObject jsonprofileDetail = jsonObject.getJSONObject("profiledetail");


                            if (jsonprofileDetail.has("doctorId"))
                                id = jsonprofileDetail.getString("doctorId");

                            country = jsonprofileDetail.optString("country").toString();
                            fee = jsonprofileDetail.optString("fee").toString();
                            sign = jsonprofileDetail.optString("sign").toString();
                            createdAt = jsonprofileDetail.optString("createdAt").toString();
                            isDeleted = jsonprofileDetail.optString("isDeleted").toString();
                            doctorId = jsonprofileDetail.optString("doctorId").toString();
                            block = jsonprofileDetail.optString("block").toString();
                            state = jsonprofileDetail.optString("state").toString();
                            village = jsonprofileDetail.optString("village").toString();
                            email = jsonprofileDetail.optString("email").toString();
                            honorific = jsonprofileDetail.optString("honorific").toString();
                            forAndroid = jsonprofileDetail.optString("forAndroid").toString();
                            registrationNumber = jsonprofileDetail.optString("registrationNumber").toString();
                            district = jsonprofileDetail.optString("district").toString();
                            organization = jsonprofileDetail.optString("organization").toString();
                            designation = jsonprofileDetail.optString("designation").toString();
                            user_name = jsonprofileDetail.optString("userName").toString();
                            Log.d("user_name", "" + user_name);
                            MyApplication.thisDeviceUsername = user_name;

                            pass = jsonprofileDetail.optString("password").toString();
                            name = jsonprofileDetail.optString("name").toString();
                            qualify = jsonprofileDetail.optString("qualification").toString();
                            maleOrFemale = jsonprofileDetail.optString("gender").toString();
                            DateOfBirth = jsonprofileDetail.optString("dob").toString();
                            specialization = jsonprofileDetail.optString("specialization").toString();
                            mobile = jsonprofileDetail.optString("mobile").toString();
                            domain = jsonprofileDetail.optString("domain").toString();
                            profileImage = jsonprofileDetail.getString("profile").toString();
                            createdby = jsonprofileDetail.getString("createdBy").toString();
                            dateformat = jsonprofileDetail.getString("dateformat").toString();


                            int i = dateformat.lastIndexOf('y');
                            if (i == dateformat.length() - 1) {
                                dateformat = dateformat + "yy";
                                dateformat = dateformat.replace("mm", "MM");
                            } else {
                                String s1 = dateformat.substring(0, i + 1);
                                String s2 = dateformat.substring(i + 1);
                                String s3 = s1 + "yy" + s2;
                                dateformat = s3.replace("mm", "MM");
                                Log.d("dateformat=", " " + s3);
                            }

                            Log.d("dateformat=", " " + dateformat);

                            loginTime = jsonCommonDetail.getString("logintime").trim();
                            usertype = jsonCommonDetail.getString("usertype").trim();
                            MyApplication.usertype = usertype;
                            domainName = jsonCommonDetail.getString("domainName");
                            domainId = jsonCommonDetail.getString("domainId");
                            videomode = jsonCommonDetail.getString("videomode");
                            phonelength=jsonCommonDetail.getString("phonelength");


                            JSONArray jsonCurrencyArray =jsonCommonDetail.getJSONArray("currency");

                            JSONObject jsonCurrencyObj=jsonCurrencyArray.getJSONObject(0);
                            String currencysymbolImg=jsonCurrencyObj.getString("symbol");
                            String currencyName=jsonCurrencyObj.getString("currency");
                            String currencyId=jsonCurrencyObj.getString("id");

                            database.insertCurrencyDetail(currencysymbolImg,currencyName);



                            database.insertDomainDetail(videomode);

                            JSONObject jsonPath = jsonObject.getJSONObject("photopath");
                            DOCTOR_PHOTO_PATH = jsonPath.getString("DOCTOR_PHOTO_PATH");
                            PATIENT_PHOTO_PATH = jsonPath.getString("PATIENT_PHOTO_PATH");
                            DOCTOR_SIGN_PATH = jsonPath.getString("DOCTOR_SIGN_PATH");
                            PATIENT_PASTRECORD_PATH = jsonPath.getString("PATIENT_PASTRECORD_PATH");
                            NURSE_PHOTO_PATH = jsonPath.getString("NURSE_PHOTO_PATH");
                            database.updateLogintime(loginTime, user_name);


                            JSONObject locationhierarchy = jsonCommonDetail.getJSONObject("locationhierarchy");
                            int no_of_label = Integer.parseInt(locationhierarchy.getString("labeles"));

                            Log.d("no_of_label in login==", "" + no_of_label);

                            String x[] = new String[no_of_label];

                            for (int s = 0; s < no_of_label; s++) {
                                x[s] = locationhierarchy.getString("label_" + (s + 1));
                                Log.d("location in login", "" + x[s]);
                            }

                            database.updateLocationDetail(x, no_of_label, domainName);


                            Intent in1 = new Intent(LoginScreen.this, MasterService.class);
                            in1.putExtra("domain", domainId);
                            in1.putExtra("masterUrl", masterUrl);
                            startService(in1);


                            if (!database.checkLocalRegistration(user_name, usertype)) {


                                if (usertype.equals("Nurse")) {

                                    new AsyncTask<String, Void, Bitmap>() {
                                        @Override
                                        protected Bitmap doInBackground(String... strings) {
                                            try {

                                                if (strings[0] != null) {

                                                    String S3ImageUrl = jsonprofileDetail.getString("S3ImageUrl").toString();
                                                    S3ImageUrl.replaceAll("\"", "");


                                                    URL url = new URL(S3ImageUrl);
                                                    Log.d("image url", " is " + S3ImageUrl);

                                                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                                    return image;
                                                }

                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }

                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Bitmap bitmap) {

                                            if (bitmap != null)
                                                imagepath = FileOperations.saveToInternalStorage(bitmap, LoginScreen.this);
                                            Log.d("user not there", "registereing");
                                            Log.d("Imagepath while log", "==" + imagepath);
                                            NurseDetails n = new NurseDetails();
                                            String id = null;
                                            try {
                                                id = jsonprofileDetail.getString("nurseId");
                                                n.setNurseId(id);
                                                n.setHonorific(honorific);
                                                n.setName(name);
                                                n.setUserName(user_name);
                                                n.setPassword(pass);
                                                n.setGender(maleOrFemale);
                                                n.setDob(DateOfBirth);
                                                n.setCountry(country);
                                                n.setState(state);
                                                n.setDistrict(district);
                                                n.setBlock(block);
                                                n.setVillage(village);
                                                n.setMobile(mobile);
                                                n.setEmail(email);
                                                n.setProfile(imagepath);
                                                n.setServerprofile(profileImage);
                                                n.setDateformat(dateformat);
                                                Log.d("path to b saved", " " + imagepath);

                                                n.setDomainId(domainId);
                                                Log.d("domainid", " " + domainId);
                                                Log.d("domainname", " " + domainName);

                                                n.setDomain(domainName);
                                                n.setLogintime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                                n.setCreatedAt(createdAt);
                                                n.setCreatedBy(honorific);
                                                n.setPhonelength(phonelength);
                                                database.insertNurse(n);

                                                Log.d("videomode==", " " + videomode);

                                                LoginDetails l = new LoginDetails();
                                                l.setUsername(user_name);
                                                l.setUsertype(usertype);
                                                l.setPassword(pass);
                                                l.setSessionId("loggedin");
                                                l.setLoginCount("1");
                                                l.setInvalidCount("");
                                                l.setLastLogin(n.getLogintime());
                                                l.setDomainId(domainId);
                                                l.setIsEnabled("1");
                                                l.setTimeOffset(offsetSubString);
                                                l.setIsupdated("1");
                                                l.setCurrentconsultation("-1");

                                                database.insertLoginDetail(l);
                                                database.insertLogintimeDetails(user_name, n.getLogintime());
                                                progressDialog.dismiss();


                                                create_usersession(response);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }.execute(profileImage);


                                } else if (usertype.equals("Doctor")) {


                                    new AsyncTask<String, Void, Bitmap[]>() {
                                        @Override
                                        protected Bitmap[] doInBackground(String... strings) {

                                            try {

                                                Bitmap image = null,sign_image=null;

                                                if (strings[0] != null) {

                                                    String S3ImageUrl = jsonprofileDetail.getString("S3ImageUrl").toString();
                                                    S3ImageUrl.replaceAll("\"", " ");

                                                    URL url = new URL(S3ImageUrl);
                                                    Log.d("image url", " is " + S3ImageUrl);
                                                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                                }

                                                if (strings[1] != null) {
                                                    String S3SignUrl = jsonprofileDetail.getString("S3SignUrl").toString();
                                                    S3SignUrl.replaceAll("\"", " ");

                                                    URL url2 = new URL(S3SignUrl);
                                                    Log.d("image url", " is " + S3SignUrl);
                                                    sign_image = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
                                                }


                                                Bitmap[] b = {image, sign_image};

                                                return b;

                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }


                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Bitmap bitmap[]) {
                                            DoctorDetails d = new DoctorDetails();
                                            String id = null;
                                            if (bitmap != null) {
                                                if (bitmap[0] != null)
                                                    imagepath = FileOperations.saveToInternalStorage(bitmap[0], LoginScreen.this);
                                                if (bitmap[1] != null)
                                                    signpath = FileOperations.saveToInternalStorage(bitmap[1], LoginScreen.this);
                                            }
                                            try {

                                                id = jsonprofileDetail.getString("doctorId");
                                                d.setDoctorId(id);
                                                d.setHonorific(honorific);
                                                d.setName(name);
                                                d.setFee(fee);
                                                d.setUserName(user_name);
                                                d.setPassword(pass);
                                                d.setGender(maleOrFemale);
                                                d.setDob(DateOfBirth);
                                                d.setQualification(qualify);
                                                d.setSpecialization(specialization);
                                                d.setDesignation(designation);
                                                d.setSign(signpath);
                                                d.setProfile(imagepath);
                                                d.setServerprofile(profileImage);
                                                d.setServersign(sign);
                                                d.setCountry(country);
                                                d.setState(state);
                                                d.setDistrict(district);
                                                d.setBlock(block);
                                                d.setVillage(village);
                                                d.setMobile(mobile);
                                                d.setEmail(email);
                                                Log.d("path to b saved", " " + imagepath);
                                                d.setRegistrationNumber(registrationNumber);
                                                d.setDomain(domainName);
                                                d.setDomainID(domainId);
                                                d.setLogintime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                                d.setCreatedAt(createdAt);
                                                d.setDateformat(dateformat);
                                                d.setCreatedBy(createdby);
                                                d.setPhonelength(phonelength);

                                                database.insertDoctor(d);

                                                LoginDetails l = new LoginDetails();
                                                l.setUsername(user_name);
                                                l.setUsertype(usertype);
                                                l.setPassword(pass);
                                                l.setSessionId("loggedin");
                                                l.setLoginCount("1");
                                                l.setInvalidCount("");
                                                l.setLastLogin(d.getLogintime());
                                                l.setDomainId(domainId);
                                                l.setIsEnabled("1");
                                                l.setTimeOffset(offsetSubString);
                                                l.setIsupdated("1");
                                                l.setCurrentconsultation("-1");

                                                database.insertLoginDetail(l);
                                                database.insertLogintimeDetails(user_name, d.getLogintime());
                                                //***********************************************
                                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                                editor.putString("country", country);
                                                editor.putString("fee", fee);
                                                editor.putString("sign", signpath);
                                                editor.putString("createdAt", createdAt);
                                                editor.putString("isDeleted", isDeleted);
                                                editor.putString("doctorId", doctorId);
                                                editor.putString("block", block);
                                                editor.putString("state", state);
                                                editor.putString("village", village);
                                                editor.putString("email", email);
                                                editor.putString("honorific", honorific);
                                                editor.putString("forAndroid", forAndroid);
                                                editor.putString("registrationNumber", registrationNumber);
                                                editor.putString("district", district);
                                                editor.putString("organization", organization);
                                                editor.putString("designation", designation);
                                                editor.putString("domainName", domainName);
                                                editor.putString("domainId", domainId);
                                                editor.putString("name", name);
                                                editor.putString("pass", pass);
                                                editor.putString("qualify", qualify);
                                                editor.putString("maleOrFemale", maleOrFemale);
                                                editor.putString("DateOfBirth", DateOfBirth);
                                                editor.putString("specialization", specialization);
                                                editor.putString("mobile", mobile);
                                                editor.putString("domain", domainName);
                                                editor.putString("usertype", usertype);
                                                editor.putString("DOCTOR_PHOTO_PATH", DOCTOR_PHOTO_PATH);
                                                editor.putString("profileImage", imagepath);
                                                editor.putString("loginTime", loginTime);
                                                editor.putString("userName", user_name);
                                                editor.putString("video", videomode);
                                                editor.putString("NURSE_PHOTO_PATH", NURSE_PHOTO_PATH);
                                                editor.putString("PATIENT_PHOTO_PATH", PATIENT_PHOTO_PATH);
                                                editor.putString("DOCTOR_SIGN_PATH", DOCTOR_SIGN_PATH);
                                                editor.putString("dateformat", dateformat);
                                                editor.putString("phonelength", phonelength);

                                                editor.putString("PATIENT_PASTRECORD_PATH", PATIENT_PASTRECORD_PATH);
                                                editor.commit();

                                                progressDialog.dismiss();

                                                Intent in = new Intent(LoginScreen.this, HomeScreen.class);
                                                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                startActivity(in);
                                                // Toast.makeText(LoginScreen.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                                                LoginScreen.this.finish();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }.execute(profileImage, sign);

                                }
                            } else {

                                Log.d("user already registered", " just update");
                                imagepath = "";
                                String S3ImageUrl=null;
                                if (profileImage != null || !profileImage.equals("") || !profileImage.equalsIgnoreCase("null")) {

                                    S3ImageUrl = jsonprofileDetail.getString("S3ImageUrl").toString();
                                    S3ImageUrl.replaceAll("\"", "");
                                }

                                if (usertype.equalsIgnoreCase("Nurse")) {

                                    //*************************************************

                                    Log.d("user already registered", " just update2");
                                    Log.d("image url", " is " + S3ImageUrl);
                                    if (!database.queryNurseDetails(user_name).getServerprofile().equals(profileImage)) {
                                        Glide.with(LoginScreen.this)
                                                .load(S3ImageUrl)
                                                .asBitmap()
                                                .into(new SimpleTarget<Bitmap>(100, 100) {
                                                    @Override
                                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                        Log.d("user already registered", " just update3");
                                                        NurseDetails n = new NurseDetails();
                                                        String id = null;
                                                        try {
                                                            id = jsonprofileDetail.getString("nurseId");
                                                            n.setNurseId(id);
                                                            n.setHonorific(honorific);
                                                            n.setName(name);
                                                            n.setUserName(user_name);
                                                            n.setPassword(pass);
                                                            n.setGender(maleOrFemale);
                                                            n.setDob(DateOfBirth);
                                                            n.setCountry(country);
                                                            n.setState(state);
                                                            n.setDistrict(district);
                                                            n.setBlock(block);
                                                            n.setVillage(village);
                                                            n.setMobile(mobile);
                                                            n.setEmail(email);
                                                            n.setServerprofile(profileImage);
                                                            n.setDateformat(dateformat);
                                                            n.setPhonelength(phonelength);

                                                            if (!database.queryNurseDetails(user_name).getServerprofile().equals(profileImage)) {
                                                                imagepath = FileOperations.saveToInternalStorage(resource, LoginScreen.this);
                                                            }
                                                            Log.d("path to b saved", " " + imagepath);
                                                            n.setProfile(imagepath);
                                                            n.setDomainId(domainId);
                                                            Log.d("domainid", " " + domainId);
                                                            Log.d("domainname", " " + domainName);

                                                            n.setDomain(domainName);
                                                            n.setLogintime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                                            n.setCreatedAt(createdAt);
                                                            n.setCreatedBy(honorific);
                                                            Log.d("user already registered", " loggin in");

                                                            database.updateNurseDetails(n);
                                                            create_usersession(response);
                                                            progressDialog.dismiss();
                                                            database.updateLoginDetail(user_name);
                                                            database.insertLogintimeDetails(user_name, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                                            Log.d("user already registered", " just update4");
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                        //***************************************************
                                                    }
                                                });
                                    }else {
                                        NurseDetails n = new NurseDetails();
                                        String id = null;
                                        try {
                                            id = jsonprofileDetail.getString("nurseId");
                                            n.setNurseId(id);
                                            n.setHonorific(honorific);
                                            n.setName(name);
                                            n.setUserName(user_name);
                                            n.setPassword(pass);
                                            n.setGender(maleOrFemale);
                                            n.setDob(DateOfBirth);
                                            n.setCountry(country);
                                            n.setState(state);
                                            n.setDistrict(district);
                                            n.setBlock(block);
                                            n.setVillage(village);
                                            n.setMobile(mobile);
                                            n.setEmail(email);
                                            n.setServerprofile(profileImage);
                                            n.setDateformat(dateformat);
                                            n.setPhonelength(phonelength);
                                            Log.d("path to b saved", " " + imagepath);
                                            n.setProfile(imagepath);
                                            n.setDomainId(domainId);
                                            Log.d("domainid", " " + domainId);
                                            Log.d("domainname", " " + domainName);

                                            n.setDomain(domainName);
                                            n.setLogintime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                            n.setCreatedAt(createdAt);
                                            n.setCreatedBy(honorific);
                                            Log.d("user already registered", " loggin in");

                                            database.updateNurseDetails(n);
                                            create_usersession(response);
                                            progressDialog.dismiss();
                                            database.updateLoginDetail(user_name);
                                            database.insertLogintimeDetails(user_name, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                            Log.d("user already registered", " just update4");

                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }


                                } else if (usertype.equalsIgnoreCase("Doctor")) {
                                    signpath = "";
                                    final Bitmap[] pimg = new Bitmap[2];
                                    final DoctorDetails d = database.queryDoctorDetails(user_name);
                                    String S3SignUrl=null;
                                    if (sign != null) {

                                        S3SignUrl = jsonprofileDetail.getString("S3SignUrl").toString();
                                        S3SignUrl.replaceAll("\"", "");

                                    }

                                    if (!d.getServerprofile().equals(profileImage) && !d.getServersign().equals(sign)) {

                                        final String finalS3SignUrl = S3SignUrl;

                                        Glide.with(LoginScreen.this)
                                                .load(S3ImageUrl)
                                                .asBitmap()
                                                .into(new SimpleTarget<Bitmap>(100, 100) {
                                                    @Override
                                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                        pimg[0] = resource;
                                                        Glide.with(LoginScreen.this)
                                                                .load(finalS3SignUrl)
                                                                .asBitmap()
                                                                .into(new SimpleTarget<Bitmap>(100, 100) {
                                                                    @Override
                                                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                                        pimg[1] = resource;
                                                                        insertDetails(pimg[0], pimg[1]);
                                                                        progressDialog.dismiss();
                                                                    }
                                                                });
                                                    }
                                                });
                                    } else if (!d.getServerprofile().equals(profileImage)) {

                                        Glide.with(LoginScreen.this)
                                                .load(S3SignUrl)
                                                .asBitmap()
                                                .into(new SimpleTarget<Bitmap>(100, 100) {
                                                    @Override
                                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                        insertDetails(resource, null);
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                    } else if (!d.getServersign().equals(sign)) {
                                        Glide.with(LoginScreen.this)
                                                .load(S3SignUrl)
                                                .asBitmap()
                                                .into(new SimpleTarget<Bitmap>(100, 100) {
                                                    @Override
                                                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                                        insertDetails(null, resource);
                                                        progressDialog.dismiss();
                                                    }
                                                });


                                    } else {

                                        insertDetails(null, null);
                                        progressDialog.dismiss();

                                    }
                                    database.updateLoginDetail(user_name);
                                    database.insertLogintimeDetails(user_name, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                    Intent in = new Intent(LoginScreen.this, HomeScreen.class);
                                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(in);
                                    // Toast.makeText(LoginScreen.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                                    LoginScreen.this.finish();

                                }
                            }
                        }

                    }else {
                        if (domain_name.getVisibility()==View.VISIBLE) {
                            dName = "false";
                            Config.getInstance(LoginScreen.this).resetProperty();
                        }
                        progressDialog.dismiss();
                        JSONObject jsonObject3 = new JSONObject(response);
                        String msg = jsonObject3.getString("msg");
                        if (res.equalsIgnoreCase("failure")) {
                            if (msg.equalsIgnoreCase("User seems to be already logged in.Please try again after some time. ")) {
                                Toast.makeText(LoginScreen.this, R.string.user_seemstobe_already_loggedin, Toast.LENGTH_SHORT).show();
                            } else if(msg.equalsIgnoreCase("user is disabled ")){
                                Toast.makeText(LoginScreen.this, R.string.user_disabled, Toast.LENGTH_SHORT).show();
                            }else if (msg.equalsIgnoreCase("Invalid Domain")){
                                Toast.makeText(LoginScreen.this, R.string.invalid_domain, Toast.LENGTH_SHORT).show();
                            }else if (msg.equalsIgnoreCase("Domain is not registered ")){
                                Toast.makeText(LoginScreen.this, R.string.domain_not_register, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginScreen.this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
                            }
                        }
                        Log.d("res", "" + response);
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginScreen.this, R.string.please_enter_correct_domainname, Toast.LENGTH_LONG).show();
                Log.d("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "loginservices");
                params.put("username", uname);
                //params.put("password", pswd);
                params.put("password", pswd);
                params.put("domainName", dName.trim());
                params.put("offset", offsetSubString);
                params.put("deviceid", MyApplication.thisDeviceId);
                params.put("deviceusername", uname);
                Log.d("domain==", " " + dName.trim());
                Log.d("url==", " " + url1);
                Log.d("username==", " " + uname);
                Log.d("password==", " " + pswd);
                Log.d("offset==", " " + offsetSubString);
                Log.d("device id in login==", " " + MyApplication.thisDeviceId);
                Log.d("device uname in login==", " " + uname);
                Log.d("offset==", " " + offsetSubString);
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void insertDetails(Bitmap profileImg, Bitmap signImg) {

        try {
            DoctorDetails d = new DoctorDetails();
            String id = this.id;
            d.setDoctorId(id);
            d.setHonorific(honorific);
            d.setName(name);
            d.setFee(fee);
            d.setUserName(user_name);
            d.setPassword(pass);
            d.setGender(maleOrFemale);
            d.setDob(DateOfBirth);
            d.setQualification(qualify);
            d.setSpecialization(specialization);
            d.setDesignation(designation);
            if (profileImg != null) {
                imagepath = FileOperations.saveToInternalStorage(profileImg, this);
            }
            if (signImg != null) {
                signpath = FileOperations.saveToInternalStorage(signImg, this);
            }
            d.setSign(signpath);
            d.setProfile(imagepath);
            d.setServerprofile(profileImage);


            d.setServersign(sign);
            d.setCountry(country);
            d.setState(state);
            d.setDistrict(district);
            d.setBlock(block);
            d.setVillage(village);
            d.setMobile(mobile);
            d.setEmail(email);
            Log.d("path to b saved", " " + imagepath);
            d.setRegistrationNumber(registrationNumber);
            d.setDomain(domainName);
            d.setDomainID(domainId);
            d.setLogintime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            d.setCreatedAt(createdAt);
            d.setDateformat(dateformat);
            d.setCreatedBy(createdby);
            d.setPhonelength(phonelength);

            database.updateDocDetails(d);


            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("country", country);
            editor.putString("fee", fee);
            editor.putString("sign", signpath);
            editor.putString("createdAt", createdAt);
            editor.putString("isDeleted", isDeleted);
            editor.putString("doctorId", doctorId);
            editor.putString("block", block);
            editor.putString("state", state);
            editor.putString("village", village);
            editor.putString("email", email);
            editor.putString("honorific", honorific);
            editor.putString("forAndroid", forAndroid);
            editor.putString("registrationNumber", registrationNumber);
            editor.putString("district", district);
            editor.putString("organization", organization);
            editor.putString("designation", designation);
            editor.putString("domainName", domainName);
            editor.putString("domainId", domainId);
            editor.putString("name", name);
            editor.putString("pass", pass);
            editor.putString("qualify", qualify);
            editor.putString("maleOrFemale", maleOrFemale);
            editor.putString("DateOfBirth", DateOfBirth);
            editor.putString("specialization", specialization);
            editor.putString("mobile", mobile);
            editor.putString("domain", domainName);
            editor.putString("usertype", usertype);
            editor.putString("DOCTOR_PHOTO_PATH", DOCTOR_PHOTO_PATH);
            editor.putString("profileImage", imagepath);
            editor.putString("loginTime", loginTime);
            editor.putString("userName", user_name);
            editor.putString("video", videomode);
            editor.putString("NURSE_PHOTO_PATH", NURSE_PHOTO_PATH);
            editor.putString("PATIENT_PHOTO_PATH", PATIENT_PHOTO_PATH);
            editor.putString("DOCTOR_SIGN_PATH", DOCTOR_SIGN_PATH);
            editor.putString("dateformat", dateformat);
            editor.putString("phonelength",phonelength);
            editor.putString("PATIENT_PASTRECORD_PATH", PATIENT_PASTRECORD_PATH);

            editor.commit();

            Intent in = new Intent(LoginScreen.this, HomeScreen.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(in);
            // Toast.makeText(LoginScreen.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
            LoginScreen.this.finish();

            //***************************************************
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void create_usersession(String response) throws JSONException {

        final String nurseId, gender, mobile, username, dob, name, honorific, logintime, domainname, usertype, domainid, nursepic, password1,phonelength;
        JSONObject jsonObject = new JSONObject(response);
        JSONObject jsonObject1 = jsonObject.getJSONObject("profiledetail");
        nurseId = jsonObject1.getString("nurseId");
        username = jsonObject1.getString("userName");
        dob = jsonObject1.getString("dob");
        name = jsonObject1.getString("name");
        gender = jsonObject1.getString("gender");
        mobile = jsonObject1.getString("mobile");
        password1 = jsonObject1.getString("password");
        honorific = jsonObject1.getString("honorific");
        JSONObject jsonObject2 = jsonObject.getJSONObject("commondetail");
        logintime = jsonObject2.getString("logintime");
        domainname = jsonObject2.getString("domainName");
        usertype = jsonObject2.getString("usertype");
        domainid = jsonObject2.getString("domainId");
        phonelength = jsonObject2.getString("phonelength");


        Log.d("domainid==loging=", "" + domainid);

        JSONArray jsonCurrencyArray =jsonObject2.getJSONArray("currency");

        JSONObject jsonCurrencyObj=jsonCurrencyArray.getJSONObject(0);
        String currencysymbolImg=jsonCurrencyObj.getString("symbol");
        String currencyName=jsonCurrencyObj.getString("currency");
        String currencyId=jsonCurrencyObj.getString("id");

        JSONObject jsonObject3 = jsonObject.getJSONObject("photopath");

        nursepic = database.queryNurseDetails(username).getProfile();

        SharedPreferences sharedPrefs = getSharedPreferences("userSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("logintime", logintime);
        editor.putString("usertype", usertype);
        editor.putString("domainname", domainname);
        editor.putString("domainid", domainid);
        editor.putString("domain", domainname);
        editor.putString("gender", gender);
        editor.putString("mobileNo", mobile);
        editor.putString("dateformat", dateformat);
        editor.putString("phonelength", phonelength);

        editor.putString("currencysymbolImg", currencysymbolImg);
        editor.putString("currencyName", currencyName);


        Log.d("ns id", " " + usertype);
        Log.d("genderLogin", " " + gender);

        editor.putString("nurseid", nurseId);
        Log.d("ns id", " " + nurseId);
        editor.putString("userName", username);
        editor.putString("name", name);
        editor.putString("dob", dob);
        editor.putString("horrific", honorific);
        editor.putString("profileImage", imagepath);
        editor.putString("nursepic", nursepic);
        editor.putString("password", password1);
        editor.putString("video", videomode);
        editor.commit();


        Intent in = new Intent(LoginScreen.this, NurseScreen.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        in.putExtra("userName", uname);
        startActivity(in);
        finish();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
        builder.setMessage("Are you sure you want to exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager tm = (TelephonyManager)
                            getSystemService(this.TELEPHONY_SERVICE);
                    deviceid = tm.getDeviceId();
                    Log.d("device Id==", " " + deviceid);
                }
            }
        }
    }
}
