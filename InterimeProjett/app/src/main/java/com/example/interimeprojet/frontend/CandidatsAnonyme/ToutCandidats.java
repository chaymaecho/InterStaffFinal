package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interimeprojet.R;
import com.example.interimeprojet.model.Offre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ToutCandidats extends BaseAdapter {

    private Context context;
    private List<Offre> offreItems;

    private LayoutInflater inflater;

    public ToutCandidats(Context context, List<Offre> offreItems) {
        this.context = context;
        this.offreItems = offreItems;
        this.inflater = LayoutInflater.from(context);
    }

    // recuperation de date courantes


    @Override
    public int getCount() {
        return offreItems.size();
    }

    @Override
    public Offre getItem(int position) {

        return offreItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_all_candida, null);

        // Récupération des données
        Offre currantItem = getItem(position);
        String poste = currantItem.title;
        String entreprise = currantItem.organization;
        String region = currantItem.cite;
        String salaire = currantItem.salary;

        // Get item name view
        TextView itempost = convertView.findViewById(R.id.txtDeveloppeurWeb);
        itempost.setText(poste);
        TextView itentreprise = convertView.findViewById(R.id.txtentreprise);
        itentreprise.setText(entreprise);
        TextView itemregion = convertView.findViewById(R.id.txtregion);
        itemregion.setText(region);
        TextView itemsalaire = convertView.findViewById(R.id.txtPrice);
        itemsalaire.setText(salaire);

        // Nouveaux boutons
        Button btnRefuser = convertView.findViewById(R.id.refuser);
        Button btnAccepter = convertView.findViewById(R.id.txtpostuler);
        Button btnVoirPlus = convertView.findViewById(R.id.voirplu);

        // Ajout de l'offre à l'intent
        Gson gson = new GsonBuilder().create();
        String offreJson = gson.toJson(currantItem);

        // Action pour le bouton "Refuser"
        btnRefuser.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Vous avez accepté l'offre.", Toast.LENGTH_SHORT).show();
            });

        // Action pour le bouton "Accepter"
        btnAccepter.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Vous avez accepté l'offre.", Toast.LENGTH_SHORT).show();

        });

        // Action pour le bouton "Voir Plus"
        btnVoirPlus.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Vous avez accepté l'offre.", Toast.LENGTH_SHORT).show();
           });

        return convertView;
    }
}
