package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.interimeprojet.R;

public class Lire  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lire);

        TextView textView = findViewById(R.id.text_view_confirmation);
        textView.setText("Nous avons le plaisir de vous informer que votre candidature a été acceptée.");
    }
}
