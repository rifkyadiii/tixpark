package ppl.app.tixpark.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.parkingSlot.ParkingSlotActivity
import java.util.Calendar

class BookingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        // Setup Vehicle Type Dropdown
        val vehicleTypes = arrayOf("Car", "Motorcycle", "Truck", "Bus")
        val vehicleDropdown = view.findViewById<AutoCompleteTextView>(R.id.etVehicle)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, vehicleTypes)
        vehicleDropdown.setAdapter(adapter)

        // Set threshold to 1 (or 0) to show all options with minimal typing
        vehicleDropdown.threshold = 1

        // Add click listener to show dropdown when clicked
        vehicleDropdown.setOnClickListener {
            vehicleDropdown.showDropDown()
        }

        // Setup Date Picker
        val etDate = view.findViewById<EditText>(R.id.etDate)
        etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                R.style.DialogTheme,
                { _, year, month, dayOfMonth ->
                    val selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                    etDate.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Setup Time Picker
        val etTime = view.findViewById<EditText>(R.id.etTime)
        etTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(
                requireContext(),
                R.style.DialogTheme,
                { _, hourOfDay, minute ->
                    val selectedTime = "%02d:%02d".format(hourOfDay, minute)
                    etTime.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }

        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val intent = Intent(requireContext(), ParkingSlotActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}
