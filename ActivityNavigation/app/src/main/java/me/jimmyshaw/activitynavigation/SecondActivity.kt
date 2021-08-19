package me.jimmyshaw.activitynavigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent

        // Get the data passed to this Activity.
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        // Display the received data.
        textViewEmail.text = email
        textViewPassword.text = password

        // Bind click event to button.
        btnClose.setOnClickListener {
            // Pack up some data, set a result and send
            // them back to the previous Activity that called
            // this Activity and then close this Activity.
            val resultIntent = Intent()

            // Pretend we logged into this user's account and
            // got the user's username and current high score.
            resultIntent.putExtra("username", "AndroidKing2000")
            resultIntent.putExtra("highScore", 314159)

            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }
    }
}