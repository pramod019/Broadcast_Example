package com.pramodk.broadcastexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import java.lang.Exception

class Broadcast: BroadcastReceiver() {

    private var _scale:String = "0"
    private var _batteryLevel:Int = 0
    private var _temp:String = "0"
    private var _voltage:String = "0"
    private var _deviceStatus:String ="0"
    private var _chargePlug:String = "0"
    private var _bHealth:String = "0"

    override fun onReceive(context: Context?, intent: Intent?) {

        try {
            intent?.let {
                val level:Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

                //output of type Int/Float
                val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                val batteryLevel: Float = level * 100 / scale.toFloat()
                val temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

                //output is of type string
                val deviceStatus: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
                val chargePlug: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
                val bHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);

                //TO SHOW SCALE
                _scale = scale.toString()
                try{
                    MainActivity.getInstance()?.updateTheBatteryScale(_scale)
                }catch (e: Exception){}

                //TO SHOW BATTERY LEVEL
                _batteryLevel = batteryLevel.toInt()
                try{
                    MainActivity.getInstance()?.updateTheBatteryLevelInPercentage(_batteryLevel)
                }catch (e: Exception){}

                //TO SHOW BATTERY TEMPERATURE
                _temp = temp.toString()
                try{
                    MainActivity.getInstance()?.updateTheBatteryTemperature(_temp)
                }catch (e: Exception){}

                //TO SHOW BATTERY VOLTAGE
                _voltage = voltage.toString()
                try{
                    MainActivity.getInstance()?.updateTheBatteryVoltage(_voltage)
                }catch (e: Exception){}


                //TO SHOW BATTERY STATUS
                var bStatus = "No Data"

                if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
                    bStatus = "Charging";
                }
                else if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    bStatus = "Discharging";
                }
                else if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL) {
                    bStatus = "Full";
                }
                else if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    bStatus = "Not Charging";
                }
                else if (deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    bStatus = "Unknown";
                }
                _deviceStatus = bStatus
                try{
                    MainActivity.getInstance()?.updateTheBatteryStatus(_deviceStatus)
                }catch (e: Exception){}

                //TO SHOW POWER SOURCE
                var bPowerSource = "No Data";

                if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) {
                    bPowerSource = "AC";
                }
                if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) {
                    bPowerSource = "USB";
                }
                _chargePlug = bPowerSource
                try{
                    MainActivity.getInstance()?.updateTheBatteryPowerSource(_chargePlug)
                }catch (e: Exception){}

                //TO SHOW BATTERY HEALTH
                var batteryHealth = "No Data";

                if (bHealth == BatteryManager.BATTERY_HEALTH_COLD) {
                    batteryHealth = "Cold";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_DEAD) {
                    batteryHealth = "Dead";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_GOOD) {
                    batteryHealth = "Good";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    batteryHealth = "Over-Voltage";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    batteryHealth = "Overheat";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
                    batteryHealth = "Unknown";
                }
                else if (bHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                    batteryHealth = "Unspecified Failure";
                }
                _bHealth = batteryHealth
                try{
                    MainActivity.getInstance()?.updateTheBatteryHealth(_bHealth)
                }catch (e: Exception){}

            }
        }catch (e: Exception){
            Log.v("BROADCAST", "Battery Info Error");
        }
    }

}