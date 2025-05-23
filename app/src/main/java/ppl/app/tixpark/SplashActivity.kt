package ppl.app.tixpark

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat

class SplashActivity : AppCompatActivity() {

    private var isNavigated = false // 🛑 Flag pengaman

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Inisialisasi EmojiCompat
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

        // Ubah warna status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            window.statusBarColor = resources.getColor(R.color.white, theme)
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            wic.isAppearanceLightStatusBars = true
        }

        val btnGetStarted = findViewById<Button>(R.id.btnSignIn)
        btnGetStarted.setOnClickListener {
            if (!isNavigated) {
                isNavigated = true
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        findViewById<TextView>(R.id.tvSignUp).setOnClickListener {
            if (!isNavigated) {
                isNavigated = true
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }

        // Delay otomatis
        Handler(Looper.getMainLooper()).postDelayed({
            if (!isNavigated) {
                isNavigated = true
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 10000)
    }
}
