package com.example.interimeprojet.frontend.CandidatsAnonyme;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.example.interimeprojet.R; // Assurez-vous que le chemin du package est correct

public class VoirPlusCa extends Activity {
    private Button applyButton; // Déclaration du bouton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voirplus); // Associe le fichier XML à l'activité

        // Initialisation du bouton dans la méthode onCreate()
        applyButton = findViewById(R.id.applyButton);

        // Ajoutez un écouteur de clic au bouton
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur le bouton
                // Par exemple, démarrer une nouvelle activité
                Intent intent = new Intent(VoirPlusCa.this, PostulerActivity.class);
                startActivity(intent);
            }
        });
    }
}
