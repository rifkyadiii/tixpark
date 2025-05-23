package ppl.app.tixpark.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import ppl.app.tixpark.MainActivity
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.profile.EditProfileActivity

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnTopUpKoin = view.findViewById<View>(R.id.btn_top_up_koin)
        btnTopUpKoin.setOnClickListener {
            val bottomNavigation = requireActivity().findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)
            bottomNavigation.show(4)

            val walletFragment = WalletFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, walletFragment)
                .addToBackStack(null)
                .commit()
        }

        val informasiAkun = view.findViewById<ConstraintLayout>(R.id.btn_informasi_akun)
        informasiAkun.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        val btnLogout = view.findViewById<View>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}
