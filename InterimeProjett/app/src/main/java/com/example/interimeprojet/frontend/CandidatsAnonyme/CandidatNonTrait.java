package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.interimeprojet.R;
import com.example.interimeprojet.model.Offre;

import java.util.ArrayList;
import java.util.List;

public class CandidatNonTrait extends AppCompatActivity {

        private ListView offrelisteview;
        private ToutCandidats adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_candidature_not_trait);

            // Menu
            Toolbar toolbar = findViewById(R.id.maintoolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            offrelisteview = findViewById(R.id.recyclerListlineeight);

            List<Offre> offreItems = createOffreItems();

            adapter = new ToutCandidats(this, offreItems);

            offrelisteview.setAdapter(adapter);

            offrelisteview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Logic pour gérer le clic sur un élément de la liste
                    Offre selectedOffre = (Offre) parent.getItemAtPosition(position);
                    Toast.makeText(CandidatNonTrait.this, "Offre sélectionnée : " + selectedOffre.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });



        }



        // Méthode pour créer des exemples de données d'offres
        private List<Offre> createOffreItems() {
            List<Offre> offreItems = new ArrayList<>();

            Offre offre1 = new Offre();
            offre1.setTitle("Chouari Chaymae");
            offre1.setOrganization("Ingenieur DataScience");
            offre1.setCite("Montpellier");
            offre1.setSalary("Déposer le 13/03/2024");
            offreItems.add(offre1);

            Offre offre2 = new Offre();
            offre2.setTitle("Omar Farhaoui");
            offre2.setOrganization("Chef du Projet");
            offre2.setCite("Montpellier");
            offre2.setSalary("Déposée le 20/02/2024");
            offreItems.add(offre2);

            Offre offre4 = new Offre();
            offre4.setTitle("Aziza Ressmaoui");
            offre4.setOrganization("Développeur web");
            offre4.setCite("Paris");
            offre4.setSalary("Déposée le 15/05/2024");
            offreItems.add(offre4);

            Offre offre3 = new Offre();
            offre3.setTitle("Anais Farhaoui");
            offre3.setOrganization("Chef du Projet");
            offre3.setCite("Lyon");
            offre3.setSalary("Déposer le 15/03/2024");
            offreItems.add(offre3);


            return offreItems;
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.main_menu4, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                // Action à réaliser lorsque le bouton de retour est cliqué
                Intent intent = new Intent(this, CandidatureAcueilActivity.class);
                startActivity(intent);
                return true;

            }

            return super.onOptionsItemSelected(item);
        }
    }

