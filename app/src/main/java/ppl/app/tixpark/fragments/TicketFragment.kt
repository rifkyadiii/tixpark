package ppl.app.tixpark.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.widget.AppCompatButton
import ppl.app.tixpark.R
import ppl.app.tixpark.fragments.ticketPrint.Ticket
import ppl.app.tixpark.fragments.ticketPrint.TicketAdapter
import ppl.app.tixpark.fragments.ticketPrint.TicketPrintActivity

class TicketFragment : Fragment() {

    private lateinit var recyclerViewTickets: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var ticketAdapter: TicketAdapter

    private val allTickets = mutableListOf<Ticket>()
    private var filteredTickets = mutableListOf<Ticket>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupRecyclerView()
        setupTabLayout()
        loadSampleData()
    }

    private fun initViews(view: View) {
        recyclerViewTickets = view.findViewById(R.id.recyclerViewTickets)
        tabLayout = view.findViewById(R.id.tabLayout)
    }

    private fun setupRecyclerView() {
        ticketAdapter = TicketAdapter(filteredTickets) { ticket ->
            onTicketClick(ticket)
        }
        recyclerViewTickets.apply {
            adapter = ticketAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupTabLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    filterTickets(it.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun filterTickets(tabPosition: Int) {
        filteredTickets.clear()

        when (tabPosition) {
            0 -> filteredTickets.addAll(allTickets) // All
            1 -> filteredTickets.addAll(allTickets.filter { it.type == "motorcycle" }) // Motorcycle
            2 -> filteredTickets.addAll(allTickets.filter { it.type == "car" }) // Car
        }

        ticketAdapter.notifyDataSetChanged()
    }

    private fun loadSampleData() {
        allTickets.addAll(listOf(
            Ticket("1", "Metro Indah Mall", "Thu 23 Jan", "09.00", "motorcycle"),
            Ticket("2", "Plaza Indonesia", "Fri 24 Jan", "14.30", "car"),
            Ticket("3", "Grand Indonesia", "Sat 25 Jan", "10.15", "motorcycle"),
            Ticket("4", "Senayan City", "Sun 26 Jan", "16.45", "car")
        ))

        // Show all tickets initially
        filteredTickets.addAll(allTickets)
        ticketAdapter.notifyDataSetChanged()
    }

    private fun onTicketClick(ticket: Ticket) {
        val intent = Intent(requireContext(), TicketPrintActivity::class.java)
        intent.putExtra("ticket_id", ticket.id)
        intent.putExtra("mall_name", ticket.mallName)
        intent.putExtra("date", ticket.date)
        intent.putExtra("time", ticket.time)
        intent.putExtra("type", ticket.type)
        startActivity(intent)
    }

    private fun onAddTicketClick() {
        // Handle add ticket button click
        // Navigate to add ticket screen
    }
}