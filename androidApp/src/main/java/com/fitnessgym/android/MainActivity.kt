package com.fitnessgym.android

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.fitnessgym.android.navigation.BottomNavigation
import com.fitnessgym.android.navigation.TopAppBarComponent
import com.fitnessgym.android.navigation.TopAppBarStateComponent
import com.fitnessgym.android.navigation.navigateToAddExercises
import com.fitnessgym.android.screens.principal.FloatingActionButtonComponent


data class FlatIconState(
    val visibility: MutableState<Boolean> = mutableStateOf(false)
)

data class BottomNavigationState(
    val visibility: MutableState<Boolean> = mutableStateOf(false)
)

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavControllerComponent(navController)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions() {
        val sendSmsPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
        val receiverSmsPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
        if (sendSmsPermission != PackageManager.PERMISSION_GRANTED && receiverSmsPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "PERMISSION_GRANTED...", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.SEND_SMS
                ),
                SEND_SMS_REQUEST_CODE
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            SEND_SMS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, perform necessary actions
                    Toast.makeText(
                        this,
                        "Permission granted, perform necessary actions",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        this,
                        "Permission denied, handle accordingly",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    // Permission denied, handle accordingly
                }
            }
        }
    }

//    @Composable
//    fun TopAppBarStateComponent() {
//
//    }

    companion object {
        private const val SEND_SMS_REQUEST_CODE = 101
    }
}

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                val messages: Array<SmsMessage?> = arrayOfNulls(pdus.size)
                var sms: String? = null
                for (i in messages.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                    val sender = messages[i]?.originatingAddress
                    val messageBody = messages[i]?.messageBody
                    sms = "SMS $sender $messageBody"
                    Log.d("SMSReceiver", "Sender: $sender, Message: $messageBody")
                }

                Toast.makeText(context, "Show code", Toast.LENGTH_SHORT).show()

            }
        }
    }
}