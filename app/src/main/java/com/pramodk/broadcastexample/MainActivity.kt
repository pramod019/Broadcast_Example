package com.pramodk.broadcastexample


import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.BroadcastReceiver
import android.os.BatteryManager
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    // register the receiver in the main activity in order
    // to receive updates of broadcasts events if they occur
    private lateinit var receiver: Broadcast

    companion object {
        var ins: MainActivity? = null
        fun getInstance(): MainActivity? {
            return ins
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ins = this

        receiver = Broadcast()
        val iFilter = IntentFilter()
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        iFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, iFilter);

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
    fun updateTheBatteryScale(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_level)
            textV1.text = t
        }
    }
    fun updateTheBatteryTemperature(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_temp)
            textV1.text = t
        }
    }
    fun updateTheBatteryHealth(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_Health)
            textV1.text = t
        }
    }
    fun updateTheBatteryLevelInPercentage(t: Int) {
        this@MainActivity.runOnUiThread {
            val pbTemp = findViewById<ProgressBar>(R.id.progress)
            pbTemp.incrementProgressBy(t);
            pbTemp.invalidate();

        }
    }
    fun updateTheBatteryPowerSource(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_Source)
            textV1.text = t
        }
    }
    fun updateTheBatteryStatus(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_Status)
            textV1.text = t
        }
    }
    fun updateTheBatteryVoltage(t: String) {
        this@MainActivity.runOnUiThread {
            val textV1 = findViewById<TextView>(R.id.txt_show_Voltage)
            textV1.text = t
        }
    }

}


