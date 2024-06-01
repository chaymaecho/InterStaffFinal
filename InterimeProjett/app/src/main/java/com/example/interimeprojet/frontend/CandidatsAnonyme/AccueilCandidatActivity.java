package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.Employee.EmployerDernierOffre;
import com.example.interimeprojet.frontend.Employee.MenuOffre;

public class AccueilCandidatActivity extends AppCompatActivity {

    private CardView vacancie, candidat, agence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_accuiel_candidat);


        vacancie = findViewById(R.id.cardview1);
        candidat = findViewById(R.id.cardview2);
        agence = findViewById(R.id.cardview3);

        vacancie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(AccueilCandidatActivity.this, EmployerDernierOffre.class);
                startActivity(intent);
            }
        });

        candidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(AccueilCandidatActivity.this, GestionCandidatureActivity.class);
                startActivity(intent);
            }
        });

        agence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AccueilCandidatActivity.this, CandidaTureRefuser.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        ImageView homeImageView = findViewById(R.id.image_home);
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilCandidatActivity.this, AccueilCandidatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        ImageView notifImageView = findViewById(R.id.image_notif);
        notifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilCandidatActivity.this, MenuOffre.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView profilImageView = findViewById(R.id.image_profil);
        profilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilCandidatActivity.this, ProfileeCandida.class);
                startActivity(intent);
                finish();
            }
        });

    }




}
