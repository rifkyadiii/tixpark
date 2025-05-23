package ppl.app.tixpark.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.AccountFragment

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)

        val btnBack = findViewById<ConstraintLayout>(R.id.back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}