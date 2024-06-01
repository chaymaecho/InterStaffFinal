package com.example.interimeprojet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.CandidatsAnonyme.NotificationAdapter;
import com.example.interimeprojet.model.Notification;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_notification, container, false);

            // Récupérer la ListView à partir de la vue du fragment
            ListView listView = view.findViewById(R.id.recyclerListlineeight);

        List<Notification> responseNotifications = new ArrayList<>();

// Response 1
        Notification response1 = new Notification();
        response1.setTitre("Réponse à votre candidature");
        response1.setMessage("Nous avons le plaisir de vous informer que votre candidature a été acceptée.");
        response1.setDate("30/05/2024");
        responseNotifications.add(response1);

// Response 2
        Notification response2 = new Notification();
        response2.setTitre("Réponse à votre candidature");
        response2.setMessage("Nous avons le plaisir de vous informer que votre candidature a été acceptée.");
        response2.setDate("17/05/2024");
        responseNotifications.add(response2);

// Use the responseNotifications list wherever you need, such as displaying them in a list or storing them in a database




        NotificationAdapter adapter = new NotificationAdapter(getActivity(), responseNotifications);

            // Définir l'adaptateur sur la ListView
            listView.setAdapter(adapter);

            // Ajouter des actions supplémentaires selon tes besoins

            return view;
        }


}