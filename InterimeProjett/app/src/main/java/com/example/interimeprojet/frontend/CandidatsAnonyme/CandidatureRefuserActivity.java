package com.example.interimeprojet.frontend.CandidatsAnonyme;

import static com.example.interimeprojet.frontend.CandidatsAnonyme.AccepteCandidatureActivity.REQUEST_CALL_PERMISSION;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;


import com.example.interimeprojet.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.interimeprojet.model.Offre;

import java.util.ArrayList;
import java.util.List;

public class CandidatureRefuserActivity extends AppCompatActivity {

    private ListView offrelisteview;
    private CandidatureRefuserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_candidature_refuser);


        // Menu
        Toolbar toolbar = findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        offrelisteview = findViewById(R.id.recyclerListlineeight);

        List<Offre> offreItems = createOffreItems();

        adapter = new CandidatureRefuserAdapter(this, offreItems);

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
        offre1.setTitle("Designer");
        offre1.setOrganization("notNetia");
        offre1.setCite("Montpellier");
        offre1.setSalary("3000€");
        offreItems.add(offre1);

        Offre offre2 = new Offre();
        offre2.setTitle("Software Engineer");
        offre2.setOrganization("YSL");
        offre2.setCite("Lyon");
        offre2.setSalary("2500€");
        offreItems.add(offre2);

        Offre offre4 = new Offre();
        offre4.setTitle("CyberSecurute");
        offre4.setOrganization("CGI");
        offre4.setCite("Paris");
        offre4.setSalary("3000€");
        offreItems.add(offre4);

        Offre offre3 = new Offre();
        offre3.setTitle("Designer");
        offre3.setOrganization("Entreprise B");
        offre3.setCite("Lyon");
        offre3.setSalary("2500€");
        offreItems.add(offre3);

        Offre offre5 = new Offre();
        offre5.setTitle("Data engineer");
        offre5.setOrganization("123 Industries");
        offre5.setCite("Lyon");
        offre5.setSalary("2500€");
        offreItems.add(offre5);

        return offreItems;
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
            Toast.makeText(this, "Add to Calendar", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

}

