
package com.example.interimeprojet.database;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Connection {

    private DatabaseReference ref;

    public Connection(){
        ref = FirebaseDatabase.getInstance("https://interimeprojet-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    }


    public DatabaseReference getRef() {
        return ref;
    }

    public void setRef(DatabaseReference ref) {
        this.ref = ref;
    }




}
