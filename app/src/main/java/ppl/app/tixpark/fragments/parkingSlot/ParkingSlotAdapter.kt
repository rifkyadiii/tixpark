package ppl.app.tixpark.fragments.parkingSlot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ppl.app.tixpark.R

class ParkingSlotAdapter(
    private val parkingSlots: MutableList<ParkingSlot>,
    private val onSlotClick: (ParkingSlot) -> Unit
) : RecyclerView.Adapter<ParkingSlotAdapter.SlotViewHolder>() {

    inner class SlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val slotName: TextView = itemView.findViewById(R.id.tvSlotName)
        private val slotIcon: ImageView = itemView.findViewById(R.id.ivSlotIcon)
        private val slotContainer: View = itemView.findViewById(R.id.slotContainer)

        fun bind(slot: ParkingSlot) {
            slotName.text = slot.name

            when (slot.status) {
                SlotStatus.AVAILABLE -> {
                    slotIcon.setImageResource(R.drawable.ic_seat_available)
                    slotContainer.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_seat_available
                    )
                    slotContainer.isEnabled = true
                }
                SlotStatus.UNAVAILABLE -> {
                    slotIcon.setImageResource(R.drawable.ic_seat_unavailable)
                    slotContainer.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_seat_unavailable
                    )
                    slotContainer.isEnabled = false
                }
                SlotStatus.SELECTED -> {
                    slotIcon.setImageResource(R.drawable.ic_seat_selected)
                    slotContainer.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_seat_selected
                    )
                    slotContainer.isEnabled = true
                }
            }

            slotContainer.setOnClickListener {
                if (slot.status != SlotStatus.UNAVAILABLE) {
                    onSlotClick(slot)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parking_slot, parent, false)
        return SlotViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        holder.bind(parkingSlots[position])
    }

    override fun getItemCount(): Int = parkingSlots.size

}