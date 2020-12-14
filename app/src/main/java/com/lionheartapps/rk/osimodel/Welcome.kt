package com.lionheartapps.rk.osimodel

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.lionheartapps.rk.osimodel.model.User
import com.lionheartapps.rk.osimodel.util.PrefManager

class Welcome : AppCompatActivity() {

    private var mFirebaseDatabase: DatabaseReference? = null
    private var mFirebaseInstance: FirebaseDatabase? = null

    private val TAG = Welcome::class.java.simpleName
    var linearLayout: LinearLayout? = null
    private var skip: TextView? = null
    private var welcome: TextView? = null
    private var inputName: EditText? = null
    private var inputEmail: EditText? = null
    private var btnSave: Button? = null
    val prefManager = PrefManager()
  //  private val prefManager: PrefManager? = null
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       prefManager.PrefManager(this@Welcome)

        if (!prefManager!!.isFirstTimeLaunch()) {
            launchHomeScreen()
            finish()
        }

        setContentView(R.layout.activity_welcome)

        welcome = findViewById(R.id.welcome) as TextView
        skip = findViewById(R.id.skipTv) as TextView
        //secretTextView = (SecretTextView) findViewById(R.id.welcomeText);
        //secretTextView = (SecretTextView) findViewById(R.id.welcomeText);
        inputName = findViewById(R.id.name) as EditText
        inputEmail = findViewById(R.id.email) as EditText
        btnSave = findViewById(R.id.btn_save) as Button
        linearLayout = findViewById(R.id.linearLayout) as LinearLayout


// Checking for first time launch - before calling setContentView()


        //Firebase


        //Notification
/*
        Calendar calendar = Calendar.getInstance();

        // we can set time by open date and time picker dialog

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        Intent intent1 = new Intent(Welcome.this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Welcome.this, 0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) Welcome.this
                .getSystemService(Welcome.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);*/


        //Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance()

        // get reference to 'users' node

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance?.getReference("users")

        // store app title to 'app_title' node

        // store app title to 'app_title' node
        mFirebaseInstance?.getReference("app_title")?.setValue("Lionheartapps")

        // app_title change listener

        // app_title change listener
        mFirebaseInstance?.getReference("app_title")?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.e(TAG, "App title updated")
                    val appTitle = dataSnapshot.getValue(String::class.java)
                    // update toolbar title
                   // supportActionBar!!.setTitle(appTitle)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                  //  Log.e(TAG, "Failed to read app title value.", error.toException())
                }
            })

        // Check the button for enable disable
        // set listeners

        // Check the button for enable disable
        // set listeners
        inputEmail!!.addTextChangedListener(mTextWatcher)
        inputName!!.addTextChangedListener(mTextWatcher)

        // run once to disable if empty

        // run once to disable if empty
        checkFieldsForEmptyValues()


        // Save / update the user


        // Save / update the user
        btnSave!!.setOnClickListener {
            val emailVal = inputEmail!!.text.toString().trim { it <= ' ' }
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            // onClick of button perform this simplest code.
            if (isValidEmail(emailVal)) {
                val name = inputName!!.text.toString()
                val email = inputEmail!!.text.toString()

                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, email)
                } else {
                    updateUser(name, email)
                }
            } else {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        skip!!.setOnClickListener {
            launchHomeScreen()
            finish()
            Toast.makeText(applicationContext, "Skip", Toast.LENGTH_SHORT).show()
        }


    }

    private fun createUser(name: String, email: String) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase!!.push().key
        }

        val user = User()
        user.name = name;
        user.email = email;

        mFirebaseDatabase!!.child(userId!!).setValue(user)
        Toast.makeText(applicationContext, "Register Successfully", Toast.LENGTH_SHORT).show()
        launchHomeScreen()
    }



    private fun updateUser(name: String, email: String) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name)) mFirebaseDatabase!!.child(userId!!).child("name")
            .setValue(name)
        if (!TextUtils.isEmpty(email)) mFirebaseDatabase!!.child(userId!!).child("email")
            .setValue(email)
        launchHomeScreen()
    }

    fun checkFieldsForEmptyValues() {
        val s1: String = inputName.toString()
        val s2: String = inputEmail.toString()
        if (s1 == "" || s2 == "") {
            btnSave?.setEnabled(false)
            btnSave?.setBackgroundColor(Color.parseColor("#607D8B"))
        } else {
            btnSave?.setEnabled(true)
            btnSave?.setBackgroundColor(Color.parseColor("#37474F"))
        }
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun afterTextChanged(editable: Editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues()
        }
    }


    open fun launchHomeScreen() {
        prefManager!!.setFirstTimeLaunch(false)
        startActivity(Intent(this@Welcome, Splash::class.java))
        finish()
    }


    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}

