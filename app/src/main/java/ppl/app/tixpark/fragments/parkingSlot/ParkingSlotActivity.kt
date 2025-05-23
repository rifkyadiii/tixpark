package ppl.app.tixpark.fragments.parkingSlot

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.ticketPrint.TicketPrintActivity

class ParkingSlotActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParkingSlotAdapter
    private lateinit var btnBooking: AppCompatButton
    private lateinit var textViewCount: TextView

    private val parkingSlots = mutableListOf<ParkingSlot>()
    private var selectedSlot: ParkingSlot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_slot)

        initViews()
        setupRecyclerView()
        generateParkingSlots()
        setupClickListeners()

        val window = this.window
        window.statusBarColor = resources.getColor(R.color.white, theme)
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = true

        // Ensure the booking button is enabled initially
        updateBookingButton()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewSeat)
        btnBooking = findViewById(R.id.btn_booking)
        textViewCount = findViewById(R.id.textViewCount)

        findViewById<ConstraintLayout>(R.id.back).setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        adapter = ParkingSlotAdapter(parkingSlots) { slot ->
            handleSlotSelection(slot)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@ParkingSlotActivity, 6)
            adapter = this@ParkingSlotActivity.adapter
            addItemDecoration(GridSpacingItemDecoration(6, 8, true))
        }
    }

    private fun generateParkingSlots() {
        val slots = mutableListOf<ParkingSlot>()
        val rows = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M")

        for (row in rows) {
            for (col in 1..6) {
                val slotName = "$row$col"
                val status = when {
                    // Contoh Unavailable
                    slotName == "C2" || slotName == "G5" || slotName == "L3" -> SlotStatus.UNAVAILABLE
                    else -> SlotStatus.AVAILABLE
                }
                slots.add(ParkingSlot(slotName, slotName, status))
            }
        }

        parkingSlots.addAll(slots)
        adapter.notifyDataSetChanged()
    }

    private fun handleSlotSelection(slot: ParkingSlot) {
        selectedSlot?.let { previousSlot ->
            if (previousSlot.status == SlotStatus.SELECTED) {
                previousSlot.status = SlotStatus.AVAILABLE
            }
        }
        when (slot.status) {
            SlotStatus.AVAILABLE -> {
                slot.status = SlotStatus.SELECTED
                selectedSlot = slot
            }
            SlotStatus.SELECTED -> {
                slot.status = SlotStatus.AVAILABLE
                selectedSlot = null
            }
            SlotStatus.UNAVAILABLE -> {
                return
            }
        }
        adapter.notifyDataSetChanged()
        updateBookingButton() // Call to update button state (though now it's always enabled)
        updateSelectedSlotInfo()
    }

    private fun updateBookingButton() {
        // The button will always be enabled.
        // The alpha modification has been removed so it always appears fully opaque.
        btnBooking.isEnabled = true
    }

    private fun updateSelectedSlotInfo() {
        textViewCount.text = selectedSlot?.name ?: "-"
    }

    private fun setupClickListeners() {
        btnBooking.setOnClickListener {
            if (selectedSlot != null) {
                proceedToBooking(selectedSlot!!)
            } else {
                // Display message if no slot is selected
                AlertDialog.Builder(this)
                    .setTitle("No Slot Selected")
                    .setMessage("Please choose your parking slot before booking.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }

    private fun proceedToBooking(slot: ParkingSlot) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Booking")
            .setMessage("Book parking slot ${slot.name}?")
            .setPositiveButton("Yes") { _, _ ->
                slot.status = SlotStatus.UNAVAILABLE
                selectedSlot = null // Deselect after booking
                adapter.notifyDataSetChanged()
                updateBookingButton() // Update button state
                updateSelectedSlotInfo() // Clear selected slot info

                // Display success message
                Toast.makeText(this, "Slot ${slot.name} booked successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TicketPrintActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("No", null)
            .show()
    }
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}
