package com.example.interimeprojet.frontend.Employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.interimeprojet.R;
import com.example.interimeprojet.database.Connection;
import com.example.interimeprojet.frontend.DashBoard;
import com.example.interimeprojet.models.Offres;
import com.example.interimeprojet.util.EnCommun.PublierOffrePourTT;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;

public class PublierOffre extends AppCompatActivity {

    PublierOffrePourTT vpa;
    RecyclerView recyclerView;
    Connection con =  new Connection();
    String msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_published_vacancy);
        // Initialiser FirebaseApp
        FirebaseApp.initializeApp(this);

        // Masquer la barre d'action si nécessaire
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Intent i = getIntent();
        msg = i.getStringExtra("email");
        System.out.println(msg);

        recyclerView = (RecyclerView)findViewById(R.id.rvUpdateVacancy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                setQuery(con.getRef().child("Offres").orderByChild("email").equalTo(msg), Offres.class).build();

        vpa = new PublierOffrePourTT(options);
        recyclerView.setAdapter(vpa);

        ActionBar actionBarr = getSupportActionBar();
        if (actionBarr != null) {
            actionBarr.hide();
        }

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublierOffre.this, Employer_AjoutOffre.class);
                startActivity(intent);
                finish();
            }
        });


        ImageView homeImageView = findViewById(R.id.image_home);
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublierOffre.this, DashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        ImageView notifImageView = findViewById(R.id.image_notif);
        notifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublierOffre.this, DashBoard.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView profilImageView = findViewById(R.id.image_profil);
        profilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublierOffre.this, Profill.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        vpa.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vpa.stopListening();
    }
}