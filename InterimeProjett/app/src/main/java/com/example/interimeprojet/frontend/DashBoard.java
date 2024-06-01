package com.example.interimeprojet.frontend;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.interimeprojet.model.Notification;
import com.example.interimeprojet.frontend.CandidatsAnonyme.AccepteCandidatureActivity;
import com.example.interimeprojet.frontend.CandidatsAnonyme.CandidatNonTrait;
import com.example.interimeprojet.frontend.CandidatsAnonyme.CandidatureAllActivity;
import com.example.interimeprojet.frontend.Employee.Employer_AjoutOffre;
import com.example.interimeprojet.frontend.Employee.EmployerLogin;
import com.example.interimeprojet.frontend.Employee.MenuOffre;
import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.Employee.Profill;

public class DashBoard extends AppCompatActivity {

    Button  btnhireWorker,btnworker;
    CardView btnVacancy,btnNONTRAIT,btnFeedbackID;
    String msg,name,img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.okactivity_dash_board);



        btnVacancy = findViewById(R.id.cardview1);
        btnNONTRAIT= findViewById(R.id.cardview2);
        btnFeedbackID = findViewById(R.id.cardview3);


        Intent i = getIntent();
        msg = i.getStringExtra("Email");
        name = i.getStringExtra("Nom");
        img = i.getStringExtra("img");

        System.out.println(msg);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        btnVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(DashBoard.this, MenuOffre.class);
                startActivity(intent);
            }
        });

        btnFeedbackID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(DashBoard.this, AccepteCandidatureActivity.class);
                startActivity(intent);
            }
        });
        btnNONTRAIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(DashBoard.this, CandidatNonTrait.class);
                startActivity(intent);
            }
        });

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, EmployerLogin.class);
                startActivity(intent);
                finish();
            }
        });





        ImageView homeImageView = findViewById(R.id.image_home);
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, DashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        ImageView notifImageView = findViewById(R.id.image_notif);
        notifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, Notification.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView profilImageView = findViewById(R.id.image_profil);
        profilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, Profill.class);
                startActivity(intent);
                finish();
            }
        });

    }



    public void onClick(View v){
        Intent i = new Intent();

        if(v == btnVacancy){
            i = new Intent(this, Employer_AjoutOffre.class);
            i.putExtra("email",msg);

        }
        if(v == btnNONTRAIT){
            i = new Intent(this, CandidatureAllActivity.class);
            i.putExtra("email",msg);

        }
        if(v == btnFeedbackID){
            i = new Intent(this, AccepteCandidatureActivity.class);
            i.putExtra("email",msg);

        }

        startActivity(i);
    }

}