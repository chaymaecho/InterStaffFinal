package com.example.interimeprojet.frontend.Employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.DashBoard;
import com.google.firebase.FirebaseApp;

public class MenuOffre extends AppCompatActivity {

        Button btn1, btn2, btn3;
        String msg;
        TextView top, bottom;
        ImageView topImg, bottomImg, backArrow;
        Animation topAnim, bottomAnim;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.okactivity_vacancy_menu);
            // Initialiser FirebaseApp
            FirebaseApp.initializeApp(this);

            // Masquer la barre d'action si nécessaire
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }



            btn1 = findViewById(R.id.btnAddVaMenu);
            btn2 = findViewById(R.id.btnPublishedVaMenu);
            backArrow = findViewById(R.id.backArrow);

            top = findViewById(R.id.tvCustomerMenuAddTitle);
            bottom = findViewById(R.id.tvCustomerMenuMyVacancyTitle);

            topImg = findViewById(R.id.ivCustomerMenuAddTitle);
            bottomImg = findViewById(R.id.ivCustomerMenuMyVacancyTitle);


            topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
            bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

            topImg.setAnimation(bottomAnim);
            btn1.setAnimation(topAnim);
            top.setAnimation(topAnim);

            bottomImg.setAnimation(bottomAnim);
            btn2.setAnimation(topAnim);
            bottom.setAnimation(topAnim);

            Intent i = getIntent();
            msg = i.getStringExtra("email");

            System.out.println(msg);



            ActionBar actionBarr = getSupportActionBar();
            if (actionBarr != null) {
                actionBarr.hide();
            }

            ImageView backArrow = findViewById(R.id.backArrow);
            backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuOffre.this, DashBoard.class);
                    startActivity(intent);
                    finish();
                }
            });


            ImageView homeImageView = findViewById(R.id.image_home);
            homeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuOffre.this, DashBoard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });


        }

        public void onClick(View v){
            Intent i = new Intent();

            if(v == btn1) {
                i = new Intent(this, Employer_AjoutOffre.class);
                i.putExtra("email",msg);

            }else if(v == btn2) {
                i = new Intent(this, PublierOffre.class);
                i.putExtra("email",msg);
            }

            startActivity(i);
        }


    }

