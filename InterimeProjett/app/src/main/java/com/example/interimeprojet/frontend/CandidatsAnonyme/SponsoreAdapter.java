package com.example.interimeprojet.frontend.CandidatsAnonyme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.interimeprojet.R;
import com.example.interimeprojet.model.Employeee;

import java.util.List;

public class SponsoreAdapter extends BaseAdapter {


    private Context context;
    private List<Employeee> Employname;
    private LayoutInflater inflater;

    public SponsoreAdapter(Context context, List<Employeee> Employname){
        this.context= context;
        this.Employname = Employname;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return Employname.size();
    }

    @Override
    public Employeee getItem(int position) {
        return Employname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_agence, null );


        // recuperation des data
        Employeee currantItem =  getItem(position);
        String agencename = currantItem.Employname;
        String contactnumber = currantItem.contactnumber;
        String adresse = currantItem.adresse;
        String link= currantItem.link;

        // get item name view
        TextView itemagencename= convertView.findViewById(R.id.txtagencyname);
        itemagencename.setText(agencename);
        TextView itemcontactnumber= convertView.findViewById(R.id.txttel);
        itemcontactnumber.setText(contactnumber);
        TextView itemtext= convertView.findViewById(R.id.txttext);
        itemtext.setText(link);
        Button boutonPostuler = convertView.findViewById(R.id.butmessage);


        boutonPostuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(v.getContext(), CandidatureSpontaneActivity.class);
                context.startActivity(intent);
            }
        });



        return convertView;
    }

}


