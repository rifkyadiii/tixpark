package ppl.app.tixpark

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import ppl.app.tixpark.fragments.AccountFragment
import ppl.app.tixpark.fragments.BookingFragment
import ppl.app.tixpark.fragments.DashboardFragment
import ppl.app.tixpark.fragments.TicketFragment
import ppl.app.tixpark.fragments.WalletFragment

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val bottomNavigation = findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)

        bottomNavigation.add(
            CurvedBottomNavigation.Model(1, "", R.drawable.ic_dashboard)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(2, "", R.drawable.ic_ticket)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(3, "", R.drawable.ic_booking)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(4, "", R.drawable.ic_wallet)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(5, "", R.drawable.ic_account)
        )

        bottomNavigation.setOnClickMenuListener{
            when(it.id){
                1 ->{
                    replaceFragment(DashboardFragment())
                }
                2 ->{
                    replaceFragment(TicketFragment())
                }
                3 ->{
                    replaceFragment(BookingFragment())
                }
                4 ->{
                    replaceFragment(WalletFragment())
                }
                5 ->{
                    replaceFragment(AccountFragment())
                }
            }
        }

        val navIndex = intent.getIntExtra("NAV_INDEX", 1)

        bottomNavigation.show(navIndex)

        val selectedFragment = when (navIndex) {
            1 -> DashboardFragment()
            2 -> TicketFragment()
            3 -> BookingFragment()
            4 -> WalletFragment()
            5 -> AccountFragment()
            else -> DashboardFragment()
        }

        replaceFragment(selectedFragment)

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}