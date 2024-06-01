package com.example.interimeprojet.frontend.CandidatsAnonyme;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interimeprojet.R;
import com.example.interimeprojet.models.Employeur;
import com.example.interimeprojet.models.Offres;
import com.example.interimeprojet.services.Services.ServiceOffreImp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class CandidaterActivity extends AppCompatActivity {

    EditText email, tp, link, pass, repass, name, prenom, DB, NT, adresse;
    Button submit, chooseImg, chooseIcv;
    ProgressBar pb;
    ImageView imgUpload, icvUpload;
    Offres offres;
    Employeur employeur;
    ServiceOffreImp vacSer = new ServiceOffreImp();
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1, PICK_CV_REQUEST = 2;
    String imageName;

    FirebaseAuth.AuthStateListener authStateListener;
    Uri mImageUri;
    Uri mCvUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_candidater);

        // Obtenez une référence à l'ActionBar
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Masquer l'ActionBar
            actionBar.hide();
        }

        name = findViewById(R.id.etUserOrganism);
        prenom = findViewById(R.id.etUserLastName);
        email = findViewById(R.id.etUserEmail);
        tp = findViewById(R.id.etUserContact);
        NT = findViewById(R.id.etUserNT);
        DB = findViewById(R.id.etUserDB);
        pass = findViewById(R.id.etUserPassword);
        repass = findViewById(R.id.etReUserPassword);
        submit = findViewById(R.id.btnRegister);
        imgUpload = findViewById(R.id.ivUploadProfImg);
        chooseImg = findViewById(R.id.btnUploadProfImg);
        icvUpload = findViewById(R.id.ivUploadCv);
        chooseIcv = findViewById(R.id.btnUploadCv);
        adresse = findViewById(R.id.etUserAddress);
        pb = findViewById(R.id.pbUploadImage);

        mStorageRef = FirebaseStorage.getInstance("gs://hireme-2e86a.appspot.com/").getReference("uploads");

        chooseImg.setOnClickListener(v -> openImageChooser());
        chooseIcv.setOnClickListener(v -> openCvChooser());

        submit.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = pass.getText().toString().trim();

            vacSer.addNewCandidat(CandidaterActivity.this, name, prenom, NT, DB, tp, adresse, email, mCvUri, pass, repass, mImageUri, pb);

        });

        // Add TextWatcher to the Date of Birth EditText
        DB.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    // Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        // with leap years - otherwise, date e.g. 29/02/2012
                        // would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    DB.setText(current);
                    DB.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void openCvChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_CV_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(imgUpload);
        }
        if (requestCode == PICK_CV_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            mCvUri = data.getData();
            Picasso.with(this).load(mCvUri).into(icvUpload);
        }
    }
}
