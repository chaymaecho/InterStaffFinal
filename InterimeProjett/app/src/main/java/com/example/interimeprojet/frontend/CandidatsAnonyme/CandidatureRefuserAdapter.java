package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.interimeprojet.R;
import com.example.interimeprojet.model.Offre;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


    public class CandidatureRefuserAdapter extends BaseAdapter {

        private Context context;
        private List<Offre> offreItems;

        private LayoutInflater inflater;

        public CandidatureRefuserAdapter (Context context, List<Offre> offreItems){
            this.context= context;
            this.offreItems = offreItems;
            this.inflater = LayoutInflater.from(context);
        }

        // recuperation de date courantes
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();



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
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.okitem_candidature_refuser, parent, false);
            }

            // Retrieving data
            Offre currentItem = getItem(position);
            String poste = currentItem.title;
            String entreprise = currentItem.organization;
            String region = currentItem.cite;
            String salaire = currentItem.salary;

            // Get item views
            TextView itemPoste = convertView.findViewById(R.id.txtDeveloppeurWeb);
            itemPoste.setText(poste);
            TextView itemEntreprise = convertView.findViewById(R.id.txtentreprise);
            itemEntreprise.setText(entreprise);
            TextView itemRegion = convertView.findViewById(R.id.txtregion);
            itemRegion.setText(region);
            TextView itemSalaire = convertView.findViewById(R.id.txtPrice);
            itemSalaire.setText(salaire);
            Button boutonPostuler = convertView.findViewById(R.id.txtpostuler);

            // Attach context menu to the item view
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show the context menu
                    v.showContextMenu();
                }
            });

            // Register the view for the context menu
            convertView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    MenuInflater inflater = ((Activity) context).getMenuInflater();
                    inflater.inflate(R.menu.main_menucontact, menu);
                }
            });




            return convertView;
        }

    }
