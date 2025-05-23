package ppl.app.tixpark.fragments.ticketPrint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ppl.app.tixpark.R

class TicketAdapter(
    private val tickets: List<Ticket>,
    private val onItemClick: (Ticket) -> Unit
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMallName: TextView = itemView.findViewById(R.id.tvMallName)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = tickets[position]

        holder.tvMallName.text = ticket.mallName
        holder.tvDate.text = ticket.date
        holder.tvTime.text = ticket.time

        holder.itemView.setOnClickListener {
            onItemClick(ticket)
        }
    }

    override fun getItemCount(): Int = tickets.size
}