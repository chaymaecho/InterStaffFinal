package com.example.interimeprojet.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.CandidatsAnonyme.Candidat_LoginActivity;
import com.example.interimeprojet.frontend.Employee.EmployerLogin;
import com.example.interimeprojet.services.Services.ServiceOffreImp;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class Main_appli extends AppCompatActivity {


    Button employer,candidat,agency;
    private FirebaseAuth auth;
    ServiceOffreImp vacSer = new ServiceOffreImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_main_apli);
        // Initialiser FirebaseApp
        FirebaseApp.initializeApp(this);

        // Masquer la barre d'action si nécessaire
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        employer = findViewById(R.id.btnEmployee);
        candidat = findViewById(R.id.btnCandidat);


        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(Main_appli.this, EmployerLogin.class);
                startActivity(i);
            }
        });

        candidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Main_appli.this, Candidat_LoginActivity.class);
                startActivity(i);
            }
        });





    }
}