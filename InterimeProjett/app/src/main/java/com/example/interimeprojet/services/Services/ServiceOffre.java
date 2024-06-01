/**
 * MAD PROJECT 2021
 * Y2-S2
 * @author IT20133290 R.M.Isuru Sahan Kumarasingha
 */

package com.example.interimeprojet.services.Services;

import android.content.Context;
import android.net.Uri;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.interimeprojet.models.Offres;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public interface ServiceOffre {

    void addNewVacancy(Context c, EditText jobTitle, EditText  organization, AutoCompleteTextView jobFamily,
                       AutoCompleteTextView  jobLevel, EditText  description, EditText  salary, String deadline,String email);

    FirebaseRecyclerOptions<Offres> txtSearch(String str);

    void addNewUser(Context c, EditText society, EditText name,EditText name2, EditText tp,EditText tp2, EditText email,EditText email2,EditText numNational,EditText link,EditText adresse, EditText service,EditText sService, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb);

    void validateUser(Context c, EditText email, EditText password);
    void addNewAgence(Context c, EditText interim,EditText tp,EditText email,EditText link,EditText adresse, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb);

    void validateAgency(Context c, EditText email, EditText password);

    void addNewCandidat(Context c, EditText name,EditText prenom,EditText nationalite,EditText datenaiss,EditText tel,EditText ville,EditText email,Uri cv, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb);

    void validateCandidat(Context c, EditText email, EditText password);


    }
