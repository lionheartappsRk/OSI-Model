package com.lionheartapps.rk.osimodel

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionheartapps.rk.osimodel.activities.OsiModelLayers

class MainActivity : AppCompatActivity() {

    private var osiBt: Button? = null
    private var tcpBt: android.widget.Button? = null
    private var figuresBt: android.widget.Button? = null
    private var moreBt: android.widget.Button? = null

    private var leftAni: Animation? = null
    private var rightAni: Animation? = null
    private var zoomAni: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*   //Notification

        Calendar calendar = Calendar.getInstance();

        // we can set time by open date and time picker dialog

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);

        Intent intent1 = new Intent(OsiMainActivity.this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(OsiMainActivity.this, 0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) OsiMainActivity.this
                .getSystemService(OsiMainActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.get*/


        // Declare the UI
        osiBt = findViewById(R.id.osiBtn) as Button
        tcpBt = findViewById(R.id.tcpBtn) as Button
        figuresBt = findViewById(R.id.figuresBtn) as Button
        moreBt = findViewById(R.id.moreBtn) as Button

        //Animation


        //Animation
        leftAni = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        rightAni = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)

        osiBt!!.animation = rightAni
        figuresBt!!.animation = rightAni
        tcpBt!!.animation = leftAni
        moreBt!!.animation = leftAni

        // Button Listeners


        // Button Listeners
        osiBt!!.setOnClickListener {
           // vibrate(com.lionheartapps.rk.osimodel.OsiMainActivity.DEFAULT_VIBRATION)
            val iosi = Intent(this@MainActivity, OsiModelLayers::class.java)
            startActivity(iosi)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }
}