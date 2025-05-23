package ppl.app.tixpark.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.dashboard.ImageSliderAdapter

class DashboardFragment : Fragment() {

    private lateinit var rvSliderMotor: RecyclerView
    private lateinit var rvSliderMobil: RecyclerView

    // Dot indicators
    private lateinit var dotMotor1: View
    private lateinit var dotMotor2: View
    private lateinit var dotMotor3: View
    private lateinit var dotMobil1: View
    private lateinit var dotMobil2: View
    private lateinit var dotMobil3: View

    private val motorImages = listOf(
        R.drawable.motor_image1,
        R.drawable.motor_image2,
        R.drawable.motor_image3,
        R.drawable.motor_image4,
        R.drawable.motor_image5,
        R.drawable.motor_image6
    )
    private val mobilImages = listOf(
        R.drawable.car_image1,
        R.drawable.car_image2,
        R.drawable.car_image3,
        R.drawable.car_image4,
        R.drawable.car_image5,
        R.drawable.car_image6
    )

    private val scrollDelay = 3000L // Interval waktu untuk auto-scroll (3 detik)

    // Handler untuk auto-scroll motor (ke kanan - posisi bertambah)
    private val motorHandler = Handler(Looper.getMainLooper())
    private val motorAutoScrollRunnable = object : Runnable {
        override fun run() {
            val layoutManager = rvSliderMotor.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            // Cari posisi item berikutnya untuk di-scroll
            val nextPosition = lastVisibleItemPosition + 1
            if (nextPosition >= motorImages.size) {
                // Jika sudah di akhir, scroll kembali ke awal (item 0)
                rvSliderMotor.smoothScrollToPosition(0)
            } else {
                rvSliderMotor.smoothScrollToPosition(nextPosition)
            }
            motorHandler.postDelayed(this, scrollDelay)
        }
    }

    // Handler untuk auto-scroll mobil (ke kiri - posisi berkurang)
    private val mobilHandler = Handler(Looper.getMainLooper())
    private val mobilAutoScrollRunnable = object : Runnable {
        override fun run() {
            val layoutManager = rvSliderMobil.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            // Cari posisi item sebelumnya untuk di-scroll
            val prevPosition = firstVisibleItemPosition - 1
            if (prevPosition < 0) {
                // Jika sudah di awal, scroll ke akhir (item terakhir)
                rvSliderMobil.smoothScrollToPosition(mobilImages.size - 1)
            } else {
                rvSliderMobil.smoothScrollToPosition(prevPosition)
            }
            mobilHandler.postDelayed(this, scrollDelay)
        }
    }

    // Handler untuk dot indicator animation
    private val dotHandler = Handler(Looper.getMainLooper())
    private lateinit var motorDotRunnable: Runnable
    private lateinit var mobilDotRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
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

        rvSliderMotor = view.findViewById(R.id.rvSliderMotor)
        rvSliderMobil = view.findViewById(R.id.rvSliderMobil)

        // Initialize dot indicators
        dotMotor1 = view.findViewById(R.id.dotMotor1)
        dotMotor2 = view.findViewById(R.id.dotMotor2)
        dotMotor3 = view.findViewById(R.id.dotMotor3)
        dotMobil1 = view.findViewById(R.id.dotMobil1)
        dotMobil2 = view.findViewById(R.id.dotMobil2)
        dotMobil3 = view.findViewById(R.id.dotMobil3)

        setupMotorRecyclerView()
        setupMobilRecyclerView()
        setupDotIndicators()

        // Mulai auto-scroll dan dot animation setelah setup
        motorHandler.postDelayed(motorAutoScrollRunnable, scrollDelay)
        mobilHandler.postDelayed(mobilAutoScrollRunnable, scrollDelay)
        dotHandler.post(motorDotRunnable)
        dotHandler.post(mobilDotRunnable)
    }

    private fun setupMotorRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvSliderMotor.layoutManager = layoutManager
        val motorAdapter = ImageSliderAdapter(requireContext(), motorImages)
        rvSliderMotor.adapter = motorAdapter
    }

    private fun setupMobilRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvSliderMobil.layoutManager = layoutManager
        val mobilAdapter = ImageSliderAdapter(requireContext(), mobilImages)
        rvSliderMobil.adapter = mobilAdapter
        layoutManager.reverseLayout = true // Atur agar item pertama ada di kanan untuk scroll ke kiri
    }

    private fun setupDotIndicators() {
        val motorDots = arrayOf(dotMotor1, dotMotor2, dotMotor3)
        val mobilDots = arrayOf(dotMobil1, dotMobil2, dotMobil3)
        val motorColors = arrayOf(R.drawable.dot1, R.drawable.dot2, R.drawable.dot3)
        val mobilColors = arrayOf(R.drawable.dot1, R.drawable.dot2, R.drawable.dot3)

        var motorCurrentIndex = 0
        var mobilCurrentIndex = 0

        // Motor dot animation (bergerak ke kiri)
        motorDotRunnable = object : Runnable {
            override fun run() {
                // Motor bergerak ke kiri (indeks menurun)
                val activeIndex = (2 - motorCurrentIndex) % 3

                motorDots.forEachIndexed { index, dot ->
                    dot.setBackgroundResource(motorColors[(index + motorCurrentIndex) % 3])
                }

                motorCurrentIndex = (motorCurrentIndex + 1) % 3
                dotHandler.postDelayed(this, 3000)
            }
        }

        // Mobil dot animation (bergerak ke kanan)
        mobilDotRunnable = object : Runnable {
            override fun run() {
                // Mobil bergerak ke kanan (indeks meningkat)

                mobilDots.forEachIndexed { index, dot ->
                    dot.setBackgroundResource(mobilColors[(index + mobilCurrentIndex) % 3])
                }

                mobilCurrentIndex = (mobilCurrentIndex + 1) % 3
                dotHandler.postDelayed(this, 3000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        motorHandler.removeCallbacks(motorAutoScrollRunnable)
        mobilHandler.removeCallbacks(mobilAutoScrollRunnable)
        dotHandler.removeCallbacks(motorDotRunnable)
        dotHandler.removeCallbacks(mobilDotRunnable)
    }

    override fun onResume() {
        super.onResume()
        motorHandler.postDelayed(motorAutoScrollRunnable, scrollDelay)
        mobilHandler.postDelayed(mobilAutoScrollRunnable, scrollDelay)
        dotHandler.post(motorDotRunnable)
        dotHandler.post(mobilDotRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        motorHandler.removeCallbacksAndMessages(null)
        mobilHandler.removeCallbacksAndMessages(null)
        dotHandler.removeCallbacksAndMessages(null)
    }
}