package ppl.app.tixpark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi EmojiCompat
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

        // Login dengan Google
        findViewById<LinearLayout>(R.id.btLgnGoogle).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        // Sign In
        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

        // Sign Up
        findViewById<TextView>(R.id.tvSignUp).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
