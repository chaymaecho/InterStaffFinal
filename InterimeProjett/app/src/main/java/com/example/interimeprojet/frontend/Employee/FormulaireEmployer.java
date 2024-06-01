package com.example.interimeprojet.frontend.Employee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.interimeprojet.R;
import com.example.interimeprojet.models.Employeur;
import com.example.interimeprojet.models.Offres;
import com.example.interimeprojet.services.Services.ServiceOffreImp;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class FormulaireEmployer extends AppCompatActivity {

    EditText name,name2,email,email2,tp,tp2,link,numNational,service,sService,pass,repass,society,adresse;
    Button submit,chooseImg;
    ProgressBar pb;
    ImageView imgUpload;
    Offres offres;
    Employeur employeur;
    ServiceOffreImp vacSer = new ServiceOffreImp();
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    String imageName;

    Uri mImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okactivity_register_emploeyeur);
        // Initialiser FirebaseApp
        FirebaseApp.initializeApp(this);

        // Masquer la barre d'action si nécessaire
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        name = findViewById(R.id.etUserName);
        name2 = findViewById(R.id.etUserName2);
        society = findViewById(R.id.etUserOrganism);
        email = findViewById(R.id.etUserEmail);
        email2 = findViewById(R.id.etUserEmail2);
        tp = findViewById(R.id.etUserContact);
        tp2 = findViewById(R.id.etUserContact2);
        link = findViewById(R.id.etUserLink);
        numNational = findViewById(R.id.etUserNumber);
        service = findViewById(R.id.etUserService);
        sService = findViewById(R.id.etUserSService);
        pass = findViewById(R.id.etUserPassword);
        repass = findViewById(R.id.etReUserPassword);
        submit = findViewById(R.id.btnRegister);
        imgUpload = findViewById(R.id.ivUploadProfImg);
        chooseImg = findViewById(R.id.btnUploadProfImg);
        adresse = findViewById(R.id.etUserAddress);
        pb = findViewById(R.id.pbUploadImage);

        mStorageRef = FirebaseStorage.getInstance("gs://interimeprojet.appspot.com").getReference("uploads");

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vacSer.addNewUser(FormulaireEmployer.this,society,name,name2,tp,tp2,email,email2,link,numNational,service,sService,adresse,pass,repass,mImageUri,pb);

            }
        });


    }

    private void openFileChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
                data != null && data.getData() != null){
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(imgUpload);

        }
    }

    private String generateVerificationCode() {
        // Générer un code de vérification aléatoire à 6 chiffres
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }


}