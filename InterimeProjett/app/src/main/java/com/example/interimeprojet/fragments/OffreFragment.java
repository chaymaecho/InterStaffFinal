package com.example.interimeprojet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.CandidatsAnonyme.OffreAdapterItems;
import com.example.interimeprojet.model.Offre;

import java.util.ArrayList;
import java.util.List;

public class OffreFragment extends Fragment {

    private OffreAdapterItems adapter;
    private ListView offrelisteview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_offre, container, false);
        //Liste des offres :
        offrelisteview = v.findViewById(R.id.recyclerListlineeight);
        // Création des exemples de données
        List<Offre> offreItems = createOffreItems();

        // Création de l'adaptateur
        adapter = new OffreAdapterItems(v.getContext(), offreItems);

        // Assignation de l'adaptateur à la liste
        offrelisteview.setAdapter(adapter);

        // Opération sur l'interface de layout lorsqu'un élément est cliqué
        offrelisteview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Logic pour gérer le clic sur un élément de la liste
                Offre selectedOffre = (Offre) parent.getItemAtPosition(position);
                Toast.makeText(v.getContext(), "Offre sélectionnée : " + selectedOffre.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    // Méthode pour créer des exemples de données d'offres
    private List<Offre> createOffreItems() {
        List<Offre> offreItems = new ArrayList<>();

        // Exemple d'offre 1
        Offre offre1 = new Offre();
        offre1.setTitle("Développeur Web");
        offre1.setOrganization("Oceasoft");
        offre1.setCite("Montpellier");
        offre1.setSalary("2000€");
        offreItems.add(offre1);

        // Exemple d'offre 2
        Offre offre2 = new Offre();
        offre2.setTitle("Chef du projet");
        offre2.setOrganization("Netia");
        offre2.setCite("Montpellier");
        offre2.setSalary("3000€");
        offreItems.add(offre2);

        // Ajouter d'autres exemples d'offres...

        return offreItems;
    }
}