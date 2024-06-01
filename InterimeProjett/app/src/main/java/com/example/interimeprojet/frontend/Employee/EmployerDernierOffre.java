package com.example.interimeprojet.frontend.Employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.interimeprojet.R;
import com.example.interimeprojet.database.Connection;
import com.example.interimeprojet.models.Offres;
import com.example.interimeprojet.services.Services.ServiceOffreImp;
import com.example.interimeprojet.util.EnCommun.OffrePourtt;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EmployerDernierOffre extends AppCompatActivity {

//    Button btn1;
    Button clear;

    RecyclerView rvAll;
    Connection con = new Connection();
    OffrePourtt offrePourtt;
    ServiceOffreImp vacSer = new ServiceOffreImp();

    //category
    String item[] = {"Banque","Conduite","Informatique ","Développement","Marketing","Internet","Privé", "Gouvernement", "Autre"};
    AutoCompleteTextView atCategory;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_latest_vacancy);

        rvAll = findViewById(R.id.rvVacancy);
        rvAll.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                setQuery(con.getRef().child("Offres"), Offres.class).build();

        offrePourtt = new OffrePourtt(options);
        rvAll.setAdapter(offrePourtt);

        ///Category dropdown menu
        atCategory = findViewById(R.id.etVacancyCategory);
        adapter = new ArrayAdapter<String>(this,R.layout.it20133290_list_item,item);
        atCategory.setAdapter(adapter);
        atCategory.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String i = Long.toString(parent.getItemIdAtPosition(position));


                FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                        setQuery(con.getRef().child("Offres").orderByChild("jobFamily").startAt(item[Integer.parseInt(i)]).endAt(item[Integer.parseInt(i)]+"~"), Offres.class).build();

                offrePourtt = new OffrePourtt(options);
                rvAll.setAdapter(offrePourtt);
                offrePourtt.startListening();


                Toast.makeText(getApplicationContext(),"Item "+i, Toast.LENGTH_SHORT);
            }
        });

        //clear button
        clear = findViewById(R.id.btnVacancySortClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atCategory.setText("");
                FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                        setQuery(con.getRef().child("Offres"), Offres.class).build();

                offrePourtt = new OffrePourtt(options);
                rvAll.setAdapter(offrePourtt);
                offrePourtt.startListening();
            }
        });




//        btn1 = (Button)findViewById(R.id.btnApply);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPopUpWindow();
//            }
//        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }



    }


    @Override
    protected void onStart() {
        super.onStart();
        offrePourtt.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        offrePourtt.stopListening();
    }

//    public void openPopUpWindow(){
//        Intent popUp = new Intent(this,IT20133290_Popup_apply_form.class);
//        startActivity(popUp);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.v_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                offrePourtt = new OffrePourtt(vacSer.txtSearch(query)); // call the search query
                offrePourtt.startListening();
                rvAll.setAdapter(offrePourtt);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {


                offrePourtt = new OffrePourtt(vacSer.txtSearch(query));  // call the search query
                offrePourtt.startListening();
                rvAll.setAdapter(offrePourtt);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}