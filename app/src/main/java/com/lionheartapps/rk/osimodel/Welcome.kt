package com.lionheartapps.rk.osimodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Welcome : AppCompatActivity() {

    private val mFirebaseDatabase: DatabaseReference? = null
    private val mFirebaseInstance: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


    }
}