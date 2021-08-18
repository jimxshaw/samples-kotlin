package me.jimmyshaw.activityanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // The MainActivity will hold or stay in place while the SecondActivity slides
        // out to the right to reveal the MainActivity, which we know is there already.
        btnFinishWithSlideOut.setOnClickListener {
            // Calling the finish() method on an Activity will call the
            // lifecycle onDestroy() method.
            finish()

            // Specify the entrance animation for the new Activity and
            // the exit animation for the current Activity.
            overridePendingTransition(R.anim.hold, R.anim.slide_out_right)
        }

        // The MainActivity will be pulled in from the left side while the SecondActivity is pushed
        // out to the right side.
        btnFinishWithPullOut.setOnClickListener {

            finish()

            // Specify the entrance animation for the new Activity and
            // the exit animation for the current Activity.
            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right)
        }

        // The MainActivity will fade in while the SecondActivity fades out.
        btnFinishWithFade.setOnClickListener {

            finish()

            // Specify the entrance animation for the new Activity and
            // the exit animation for the current Activity.
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}