package com.example.puffandpoof.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.DetailDollActivity
import com.example.puffandpoof.R
import com.example.puffandpoof.adaptor.DollAdap
import com.example.puffandpoof.model.doll
//import android.content.Intent

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var adap: DollAdap
    private lateinit var recyclerView: RecyclerView
    private lateinit var dollist :ArrayList<doll>

    lateinit var id : Array<String>
    lateinit var img : Array<Int>
    lateinit var tittle : Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun onDollClick(position: Int) {
        // Handle item click here
        // For example, you can open a new activity with details of the clicked doll
        val clickedDoll = dollist[position]
        val intent = Intent(requireContext(), DetailDollActivity::class.java)
        intent.putExtra("dollId", clickedDoll.id)
        intent.putExtra("tittle",clickedDoll.tittle)
        intent.putExtra("img",clickedDoll.image)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rvd)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dollist = ArrayList()
        adap = DollAdap(dollist, this) // Pass the click listener here

        // Populate the list of dolls
        datainisiasi()

        // Set adapter to RecyclerView
        recyclerView.adapter = adap
    }



    private fun datainisiasi() {
        // Clear existing data
        dollist.clear()

        // Add new data
        val img = arrayOf(
            R.drawable.snorlax,
            R.drawable.spongebob,
            R.drawable.patrick,
        )

        val tittle = arrayOf(
            "Snorlax",
            "Spongebob",
            "Patrick",
        )
        val price = arrayOf(
            "Rp.11000",
            "Rp.23000",
            "Rp.12000",
        )
        val id = arrayOf(
            "SNRLX",
            "SPNGB",
            "PTRCK",
        )

        for (i in img.indices) {
            val doll = doll(
                img[i], tittle[i], id[i],price[i])
            dollist.add(doll)
        }

        // Notify adapter about the data change
        adap.notifyDataSetChanged()
    }

}