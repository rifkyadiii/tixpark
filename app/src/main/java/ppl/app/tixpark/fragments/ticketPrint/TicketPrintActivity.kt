package ppl.app.tixpark.fragments.ticketPrint

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.WindowInsetsControllerCompat
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import ppl.app.tixpark.MenuActivity
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.TicketFragment

class TicketPrintActivity : AppCompatActivity() {

    private lateinit var ivBack: ImageView
    private lateinit var btnDownload: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_print)

        ivBack = findViewById(R.id.ivBack)
        btnDownload = findViewById(R.id.btn_download)

        ivBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("NAV_INDEX", 2)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        btnDownload.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Download Tiket")
            builder.setMessage("Apakah kamu ingin menyimpan tiket sekarang?")
            builder.setPositiveButton("Ya") { dialog, _ ->
                Toast.makeText(this, "Ticket download successfully!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }

        val window = this.window
        window.statusBarColor = resources.getColor(R.color.red2, theme)
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = true
    }
}
