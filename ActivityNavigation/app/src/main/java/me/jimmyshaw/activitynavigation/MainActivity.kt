package me.jimmyshaw.activitynavigation

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.*
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log.d as d1

class MainActivity : AppCompatActivity() {

    private val NEXT_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Listen to the button event.
        btnNextScreen.setOnClickListener {
            // Start a new Intent.
            val nextScreen = Intent(applicationContext, SecondActivity::class.java)

            // Send data to another Activity.
            nextScreen.putExtra("email", editTextEmail.text.toString())
            nextScreen.putExtra("password", editTextPassword.text.toString())

            // Start a new Activity and ignore any results.
            //startActivity(nextScreen)

            // Start a new Activity and get the results.
            registerForActivityResult(StartActivityForResult()) {
                result -> onActivityResult(NEXT_ACTIVITY, result)
            }.launch(nextScreen)
        }
    }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data

            when (requestCode) {
                NEXT_ACTIVITY -> {
                    Log.d(TAG, "Activity.RESULT_OK")

                    // Get the result data.
                    val username = intent?.getStringExtra("username")
                    Log.d(TAG, "username = $username")

                    val highScore = intent?.getIntExtra("highScore", 0)
                    Log.d(TAG, "highScore = $highScore")

                    textViewUsername.text = "Username: $username"
                    textViewHighScore.text = "High Score: $highScore"

                }
            }
        }
    }

    companion object {
        private const val TAG: String = "MainActivity"
    }
}