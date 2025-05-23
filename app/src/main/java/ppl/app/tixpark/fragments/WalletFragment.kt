package ppl.app.tixpark.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import ppl.app.tixpark.R

class WalletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wallet, container, false)

        // Ambil semua RadioButton
        val radioButtons = listOf(
            view.findViewById<RadioButton>(R.id.r1),
            view.findViewById<RadioButton>(R.id.r2),
            view.findViewById<RadioButton>(R.id.r3),
            view.findViewById<RadioButton>(R.id.r4),
            view.findViewById<RadioButton>(R.id.r5),
            view.findViewById<RadioButton>(R.id.r6)
        )

        // Atur agar hanya satu RadioButton bisa dipilih
        for (rb in radioButtons) {
            rb.setOnClickListener {
                radioButtons.forEach { it.isChecked = false }
                rb.isChecked = true
            }
        }

        return view
    }
}
