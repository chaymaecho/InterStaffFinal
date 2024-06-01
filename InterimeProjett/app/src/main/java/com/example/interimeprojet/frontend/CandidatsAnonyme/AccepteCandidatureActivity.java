package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.interimeprojet.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.interimeprojet.model.Offre;

import java.util.ArrayList;
import java.util.List;

public class AccepteCandidatureActivity extends AppCompatActivity {

    private ListView offrelisteview;
    private CandidatureAccepterOffre adapter;

    static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_accepte_candidature);



        // Menu
        Toolbar toolbar = findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        offrelisteview = findViewById(R.id.recyclerListlineeight);

        List<Offre> offreItems = createOffreItems();

        adapter = new CandidatureAccepterOffre(this, offreItems);

        offrelisteview.setAdapter(adapter);

        offrelisteview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Logic pour gérer le clic sur un élément de la liste
                Offre selectedOffre = (Offre) parent.getItemAtPosition(position);
              }
        });



    }



    // Méthode pour créer des exemples de données d'offres
    private List<Offre> createOffreItems() {
        List<Offre> offreItems = new ArrayList<>();

        Offre offre1 = new Offre();
        offre1.setTitle("Chouari Chaymae");
        offre1.setOrganization("Developeur Web");
        offre1.setCite("Montpellier");
        offre1.setSalary("3000€");
        offreItems.add(offre1);

        Offre offre2 = new Offre();
        offre2.setTitle("Aziza Ressmaoui");
        offre2.setOrganization("Software Engineer");
        offre2.setCite("Montpellier");
        offre2.setSalary("2500€");
        offreItems.add(offre2);

        Offre offre4 = new Offre();
        offre4.setTitle("Anais Farhaoui");
        offre4.setOrganization("Chef de projet");
        offre4.setCite("Lyon");
        offre4.setSalary("3000€");
        offreItems.add(offre4);


        return offreItems;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu4, menu);
        return true;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Gérer les actions du menu contextuel
        int itemId = item.getItemId();
        if (itemId == R.id.menu_refuse) {
            Toast.makeText(this, "Refuse", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.menu_contact) {
            Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();

            String phoneNumber = "0774859517"; // Numéro de téléphone à appeler

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));

            // Vérifier si la permission CALL_PHONE est accordée
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, lancer l'appel
                startActivity(intent);
            } else {
                // Demander la permission à l'utilisateur
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
            }

            return true;
        } else if (itemId == R.id.menu_add_to_calendar) {
            Toast.makeText(this, "ajouter au calednrier ", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }





}