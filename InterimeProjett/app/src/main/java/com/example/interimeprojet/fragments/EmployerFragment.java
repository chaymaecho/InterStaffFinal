package com.example.interimeprojet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.interimeprojet.R;
import com.example.interimeprojet.frontend.CandidatsAnonyme.EmployerAdapter;
import com.example.interimeprojet.models.Employeur;

import java.util.ArrayList;
import java.util.List;

public class EmployerFragment extends Fragment {


    public static EmployerFragment newInstance() {
        return new EmployerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_employer, container, false);

        ListView listView = v.findViewById(R.id.recyclerListlineeight);

        List<Employeur> employeeList = new ArrayList<>();

        Employeur employee1 = new Employeur();
        employee1.setName("development ");
        employee1.setName2("mobile");
        employee1.setTel("Chef du projet");
        employee1.setLink("https://www.Linkdin.com");


        employeeList.add(employee1);


        EmployerAdapter adapter = new EmployerAdapter(getContext(), employeeList);

        listView.setAdapter(adapter);

        return v;

    }



}