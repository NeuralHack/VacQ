package com.example.vacq;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UserRegister extends AppCompatActivity {
    EditText userphone, userpass, username, userage;
    String usergender = "";
    String userprofession = "";
    String userdisease = "";
    String userzone="";
    String usercity="";
    Button getLoc,register;
    TextView txt3, txt4;
    FusedLocationProviderClient fusedLocationProviderClient;
    String[] gender = {"Select gender", "Male", "Female"};

    String[] profession = {"Select Profession", "technician", "scientist", "entertainment", "educator", "healthcare", "farmers", "student",
            "homemaker", "artist", "housekeeping", "engineer", "salesman", "administrative", "marketing", "others"};
    String[] disease = {"Select disease", "Asthma", "Chronic Lung Disease", "Heart Disease", "Diabetes", "Hyper Tension", "Cancer",
            "Immuno Compromised State", "Severe Obesity", "Liver disease", "Cystic Fibrosis", "Sickle Cell Disease", "None"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        userphone = findViewById(R.id.userentphone);
        userpass = findViewById(R.id.userentpass);
        username = findViewById(R.id.userentname);
        userage = findViewById(R.id.userentage);

        Spinner genderspinner = findViewById(R.id.genderspinner);
        ArrayAdapter<String> genderadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gender);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(genderadapter);
        genderspinner.setOnItemSelectedListener(new GenderSpinnerClass());


        Spinner professionspinner = findViewById(R.id.professionspinner);
        ArrayAdapter<String> professionadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, profession);
        professionadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        professionspinner.setAdapter(professionadapter);
        professionspinner.setOnItemSelectedListener(new ProfessionSpinnerClass());


        Spinner diseasespinner = findViewById(R.id.diseasespinner);
        ArrayAdapter<String> diseaseadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, disease);
        diseaseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diseasespinner.setAdapter(diseaseadapter);
        diseasespinner.setOnItemSelectedListener(new DiseaseSpinnerClass());

        getLoc = findViewById(R.id.getlocationbutton);
        txt3 = findViewById(R.id.City);
        txt4=findViewById(R.id.Zone);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                } else {
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try{
                        String city=hereLocation(location.getLatitude(),location.getLongitude());
                        txt3.setText("City: "+city);
                        txt4.setText("Zone: Red Zone");
                        userzone="Red Zone";
                        usercity=city;
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(UserRegister.this,"Not found",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user user = new user(userage.getText().toString(),usercity,
                        userdisease,usergender,username.getText().toString(),userpass.getText().toString(),
                        userprofession,userzone);
                Intent intent=new Intent(UserRegister.this, UserAfterRegisteredAndLogin.class);
                intent.putExtra("Age", userage.getText().toString());
                intent.putExtra("City", usercity);
                intent.putExtra("Gender", usergender);
                intent.putExtra("Name", username.getText().toString());
                intent.putExtra("Pass", userpass.getText().toString());
                intent.putExtra("Profession", userprofession);
                intent.putExtra("Zone", userzone);
                startActivity(intent);
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(userphone.getText().toString()).exists()){
                            Toast.makeText(UserRegister.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            user user = new user(userage.getText().toString(),usercity,
                                    userdisease,usergender,username.getText().toString(),userpass.getText().toString(),
                                    userprofession,userzone);
                            table_user.child(userphone.getText().toString()).setValue(user);
                            Toast.makeText(UserRegister.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserAfterRegisteredAndLogin.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private String hereLocation(double lat,double lon) {
        String cityname="";

        Geocoder geocoder = new Geocoder(UserRegister.this, Locale.getDefault());
        List<Address> adresses;
        try {
            adresses = geocoder.getFromLocation(lat,lon, 1);
            if(adresses.size()>0){
                for(Address adr:adresses){
                    if(adr.getLocality()!=null && adr.getLocality().length()>0){
                        cityname=adr.getLocality();
                    }break;}}}
        catch(IOException e){
            e.printStackTrace();
                    }
        return cityname;
    }

    class GenderSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            usergender=gender[i];
        }
    }

    class ProfessionSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            userprofession=profession[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class DiseaseSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            userdisease=disease[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 44:{
                if (grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try{
                        String city=hereLocation(location.getLatitude(),location.getLongitude());
                        txt3.setText("City: "+city);
                        txt4.setText("Zone: Red Zone");
                        userzone="Red Zone";
                        usercity=city;
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(UserRegister.this,"Not found",Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                }
            }
        }
    }
}