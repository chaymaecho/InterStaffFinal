package com.example.interimeprojet.services.Services;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interimeprojet.database.Connection;
import com.example.interimeprojet.frontend.CandidatsAnonyme.Candidat_LoginActivity;
import com.example.interimeprojet.frontend.CandidatsAnonyme.Candidats;
import com.example.interimeprojet.frontend.CandidatsAnonyme.AccueilCandidatActivity;
import com.example.interimeprojet.frontend.DashBoard;
import com.example.interimeprojet.frontend.Employee.EmployerLogin;
import com.example.interimeprojet.frontend.Employee.MenuOffre;
import com.example.interimeprojet.models.Employeur;
import com.example.interimeprojet.models.Offres;
import com.example.interimeprojet.util.EnCommun.PointsCommunS;
import com.example.interimeprojet.util.EnCommun.OutilsCommun;
//import com.example.interimeprojet.util.Common.OffrePourtt;
import com.example.interimeprojet.util.EnCommun.OffreValide;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


public class ServiceOffreImp implements ServiceOffre {
    //get model class instance
    Offres offres = new Offres();
    Employeur employeur = new Employeur();

    Candidats candidats = new Candidats();

    //firebase instance
    Connection con = new Connection();
    OutilsCommun cu = new OutilsCommun();
   // OffrePourtt vacancyAdapter;
    RecyclerView rvAll;
    OffreValide offreValide = new OffreValide();
    private StorageTask mUploadTask;


    @Override
    public void addNewVacancy(Context c, EditText jobTitle, EditText organization, AutoCompleteTextView jobFamily,
                              AutoCompleteTextView jobLevel, EditText description, EditText salary, String deadline, String email) {


        String DES_PATTERN = ".{1,2000}"; // creating pattern for description
        try {
            //check input filed are empty or not
            if (TextUtils.isEmpty(jobTitle.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_TITLE, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(jobFamily.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_FAMILY, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(jobLevel.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_LEVEL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(organization.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_ORGANIZATION, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(salary.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_SALARY, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(description.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_DESCRIPTION, Toast.LENGTH_LONG).show();

            else if (!(description.getText().toString()).matches(DES_PATTERN))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_JOB_DESCRIPTION_LIMIT, Toast.LENGTH_LONG).show();

            else {
                offres.setJobTitle(jobTitle.getText().toString().trim());
                offres.setJobFamily(jobFamily.getText().toString().trim());
                offres.setJobLevel(jobLevel.getText().toString().trim());
                offres.setOrganization(organization.getText().toString().trim());
                offres.setSalary(salary.getText().toString().trim());
                offres.setDescription(description.getText().toString().trim());
                offres.setDeadline(deadline);
                offres.setEmail(email);

                //insert value in to database
                con.getRef().child(PointsCommunS.VACANCY).child(PointsCommunS.VACANCY_ID + (cu.getNextID())).setValue(offres);

                Toast.makeText(c, PointsCommunS.DATA_INSERT_SUCCESSFULLY, Toast.LENGTH_LONG).show();

                Intent i = new Intent(c, MenuOffre.class);
                i.putExtra(PointsCommunS.EMAIL_EXTRA, email);
                c.startActivity(i);

                //clear entered values
                clearVacancyForm(jobTitle, organization, jobFamily,
                        jobLevel, description, salary);
            }
        } catch (Exception e) {
            Toast.makeText(c, PointsCommunS.DATA_INSERT_UNSUCCESSFULLY, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public FirebaseRecyclerOptions<Offres> txtSearch(String str) {
        int val = 0;
        String val2 = "";

        //check the value is number or string
        try {
            val = Integer.parseInt(str);
            val2 = "" + val;
            //find values by salary
            FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                    setQuery(con.getRef().child(PointsCommunS.VACANCY).orderByChild("salary").startAt(val2).endAt(val2 + "~"), Offres.class).build();
            return options;
        } catch (Exception e) {
            //find values by job title
            FirebaseRecyclerOptions<Offres> options = new FirebaseRecyclerOptions.Builder<Offres>().
                    setQuery(con.getRef().child(PointsCommunS.VACANCY).orderByChild("jobTitle").startAt(str).endAt(str + "~"), Offres.class).build();
            return options;
        }

    }

    @Override
    public void addNewUser(Context c, EditText society, EditText name,EditText name2, EditText tp,EditText tp2, EditText email,EditText email2,EditText numNational,EditText link,EditText adresse, EditText service,EditText sService, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passwordPatter = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        try {
            if (offreValide.isNameEmpty((name.getText().toString())))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_NAME, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(tp.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_MOBILE_NUMBER, Toast.LENGTH_LONG).show();

            else if((tp.getText().toString().length() < 10))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_MOBILE_NUMBER, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(service.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_SERVICE, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(adresse.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_ADDRESS, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!password.getText().toString().equals(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PASSWORD_MISMATCHED, Toast.LENGTH_LONG).show();

            else if (!offreValide.isEmailValid(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else if(!offreValide.isPasswordValid(password.getText().toString())){
                Toast.makeText(c, PointsCommunS.STRONG_PASSWORD, Toast.LENGTH_LONG).show();

            } else {

                String key = email.getText().toString().trim();
                employeur.setName(name.getText().toString().trim());
                employeur.setTel(tp.getText().toString().trim());
                employeur.setEmail(email.getText().toString().trim());
                employeur.setPassword(password.getText().toString().trim());
                employeur.setSociety(society.getText().toString().trim());
                employeur.setAddress(adresse.getText().toString().trim());
                employeur.setService(service.getText().toString().trim());
                employeur.setSservice(sService.getText().toString().trim());
                employeur.setEmail2(email2.getText().toString().trim());
                employeur.setTel2(tp2.getText().toString().trim());
                employeur.setName2(name2.getText().toString().trim());
                employeur.setLink(link.getText().toString().trim());
                employeur.setNumVacancy(numNational.getText().toString().trim());

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(c, "En cours...", Toast.LENGTH_SHORT);
                } else {
                    uploadFile(mImageUri, pb, c, employeur);
                }


            }

        } catch (Exception e) {

        }


    }


    @Override
    public void addNewAgence(Context c, EditText interim,EditText tp,EditText email,EditText link,EditText adresse, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passwordPatter = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        try {
            if (offreValide.isNameEmpty((interim.getText().toString())))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_NAME, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(tp.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_MOBILE_NUMBER, Toast.LENGTH_LONG).show();

            else if((tp.getText().toString().length() < 10))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_MOBILE_NUMBER, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(adresse.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_ADDRESS, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!password.getText().toString().equals(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PASSWORD_MISMATCHED, Toast.LENGTH_LONG).show();

            else if (!offreValide.isEmailValid(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else if(!offreValide.isPasswordValid(password.getText().toString())){
                Toast.makeText(c, PointsCommunS.STRONG_PASSWORD, Toast.LENGTH_LONG).show();



            }

        } catch (Exception e) {

        }


    }


    @Override
    public void validateUser(Context c, EditText email, EditText password) {
        //create patter for email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        try {
            //check input fields are empty or not
            if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!(email.getText().toString()).matches(emailPattern))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else {
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                //validate user
                Query checkUser = con.getRef().child(PointsCommunS.APP_USER).orderByChild("email").equalTo(enteredEmail);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String val = "ok";
                            for (DataSnapshot temp : snapshot.getChildren()) {
                                val = temp.getKey();
                            }

                            String dbPassword = snapshot.child(val).child("password").getValue(String.class);
                            //get user values
                            if (dbPassword.equals(enteredPassword)) {
                                String dbName = snapshot.child(val).child("name").getValue(String.class);
                                String dbTel = snapshot.child(val).child("tel").getValue(String.class);
                                String dbEmail = snapshot.child(val).child("email").getValue(String.class);
                                String img =snapshot.child(val).child("img").getValue(String.class);
                                String society =snapshot.child(val).child("society").getValue(String.class);
                                Intent i = new Intent(c, DashBoard.class);

                                //i.putExtra("pass",dbPassword);
                                i.putExtra("name", dbName);
                                i.putExtra("tel", dbTel);
                                i.putExtra("email", dbEmail);
                                i.putExtra("img",img);

                                c.startActivity(i);
                            } else {
                                Toast.makeText(c, PointsCommunS.ENTERED_PASSWORD_INCORRECT, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(c, PointsCommunS.NO_SUCH_USER, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(c, PointsCommunS.TRY_AGAIN , Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void validateAgency(Context c, EditText email, EditText password) {
        //create patter for email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        try {
            //check input fields are empty or not
            if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!(email.getText().toString()).matches(emailPattern))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else {
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                //validate user
                Query checkAgency = con.getRef().child(PointsCommunS.APP_AGENCE).orderByChild("email").equalTo(enteredEmail);
                checkAgency.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String val = "ok";
                            for (DataSnapshot temp : snapshot.getChildren()) {
                                val = temp.getKey();
                            }

                            String dbPassword = snapshot.child(val).child("password").getValue(String.class);
                            //get user values
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(c, PointsCommunS.TRY_AGAIN , Toast.LENGTH_LONG).show();
        }

    }

    public void addNewCandidat(Context c, EditText name,EditText prenom,EditText nationalite,EditText datenaiss,EditText tel,EditText ville,EditText email,Uri cv, EditText password, EditText repassword, Uri mImageUri, ProgressBar pb) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passwordPatter = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        try {
            if (offreValide.isNameEmpty((name.getText().toString())))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_NAME, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!password.getText().toString().equals(repassword.getText().toString()))
                Toast.makeText(c, PointsCommunS.PASSWORD_MISMATCHED, Toast.LENGTH_LONG).show();

            else if (!offreValide.isEmailValid(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else if(!offreValide.isPasswordValid(password.getText().toString())){
                Toast.makeText(c, PointsCommunS.STRONG_PASSWORD, Toast.LENGTH_LONG).show();

            } else {

                String key = email.getText().toString().trim();
                candidats.setName(name.getText().toString().trim());
                candidats.setTel(tel.getText().toString().trim());
                candidats.setEmail(email.getText().toString().trim());
                candidats.setPassword(password.getText().toString().trim());
                candidats.setDate(datenaiss.getText().toString().trim());
                candidats.setVille(ville.getText().toString().trim());
                candidats.setPrenom(prenom.getText().toString().trim());

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(c, "En cours...", Toast.LENGTH_SHORT);
                } else {
                    uploadFileC(mImageUri, pb, c, candidats);
                }


            }

        } catch (Exception e) {

        }


    }

    @Override
    public void validateCandidat(Context c, EditText email, EditText password) {
        //create patter for email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        try {
            //check input fields are empty or not
            if (TextUtils.isEmpty(email.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_EMAIL, Toast.LENGTH_LONG).show();

            else if (TextUtils.isEmpty(password.getText().toString()))
                Toast.makeText(c, PointsCommunS.PLEASE_ENTER_YOUR_PASSWORD, Toast.LENGTH_LONG).show();

            else if (!(email.getText().toString()).matches(emailPattern))
                Toast.makeText(c, PointsCommunS.INCORRECT_EMAIL, Toast.LENGTH_LONG).show();

            else {
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                //validate user
                Query checkUser = con.getRef().child(PointsCommunS.APP_CANDIDATE).orderByChild("email").equalTo(enteredEmail);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String val = "ok";
                            for (DataSnapshot temp : snapshot.getChildren()) {
                                val = temp.getKey();
                            }

                            String dbPassword = snapshot.child(val).child("password").getValue(String.class);
                            //get user values
                            if (dbPassword.equals(enteredPassword)) {
                                String dbName = snapshot.child(val).child("name").getValue(String.class);
                                String dbTel = snapshot.child(val).child("tel").getValue(String.class);
                                String dbEmail = snapshot.child(val).child("email").getValue(String.class);
                                String img =snapshot.child(val).child("img").getValue(String.class);
                                String prenom =snapshot.child(val).child("prenom").getValue(String.class);
                                Intent i = new Intent(c, AccueilCandidatActivity.class);

                                //i.putExtra("pass",dbPassword);
                                i.putExtra("name", dbName);
                                i.putExtra("tel", dbTel);
                                i.putExtra("email", dbEmail);
                                i.putExtra("prenom",prenom);
                                i.putExtra("img",img);

                                c.startActivity(i);
                            } else {
                                Toast.makeText(c, PointsCommunS.ENTERED_PASSWORD_INCORRECT, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(c, PointsCommunS.NO_SUCH_USER, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(c, PointsCommunS.TRY_AGAIN , Toast.LENGTH_LONG).show();
        }

    }



    public void clearVacancyForm(EditText jobTitle, EditText organization, EditText jobFamily,
                                 EditText jobLevel, EditText description, EditText salary) {
        jobTitle.setText("");
        organization.setText("");
        jobFamily.setText("");
        jobLevel.setText("");
        description.setText("");
        salary.setText("");
    }


    private String getFileExtension(Uri uri, Context c) {
        ContentResolver cR = c.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void uploadFile(Uri mImageUri, ProgressBar pb, Context c, Employeur employeur) {
        StorageReference mStorageRef = FirebaseStorage.getInstance(PointsCommunS.FIREBASE_STORAGE_1).getReference("uploads");

        if (mImageUri != null) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri, c));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(0);
                        }
                    }, 500);

                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            //Do what you want with the url

                            employeur.setImg(downloadUrl.toString());
                            con.getRef().child(PointsCommunS.APP_USER).child(PointsCommunS.APP_USER_ID + (cu.getCusID())).setValue(employeur);


                            Toast.makeText(c, PointsCommunS.SUCCESS_LOGIN, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(c, EmployerLogin.class);
                            c.startActivity(i);
                        }

                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    pb.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(c, PointsCommunS.NO_FILE_SELECTED, Toast.LENGTH_SHORT).show();
        }
    }


    public void uploadFileC(Uri mImageUri, ProgressBar pb, Context c, Candidats candidats) {
        StorageReference mStorageRef = FirebaseStorage.getInstance(PointsCommunS.FIREBASE_STORAGE_1).getReference("uploads");

        if (mImageUri != null) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri, c));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(0);
                        }
                    }, 500);

                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            //Do what you want with the url

                            candidats.setImg(downloadUrl.toString());
                            con.getRef().child(PointsCommunS.APP_CANDIDATE).child(PointsCommunS.APP_CANDIDATE_ID + (cu.getCusID())).setValue(candidats);


                            Toast.makeText(c, PointsCommunS.SUCCESS_LOGIN, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(c, Candidat_LoginActivity.class);
                            c.startActivity(i);
                        }

                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    pb.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(c, PointsCommunS.NO_FILE_SELECTED, Toast.LENGTH_SHORT).show();
        }
    }
}
