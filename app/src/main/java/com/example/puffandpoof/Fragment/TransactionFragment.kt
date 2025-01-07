// TransactionFragment.kt
package com.example.puffandpoof.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.R
import com.example.puffandpoof.adaptor.TransactionAdap
import com.example.puffandpoof.model.Transaction
import `object`.Transactionlist

class TransactionFragment : Fragment(), TransactionAdap.TransactionClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdap
    private lateinit var transactionList: MutableList<Transaction>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)

        recyclerView = view.findViewById(R.id.rvt)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize the transaction list
        transactionList = Transactionlist.getTransactions().toMutableList()

        // Initialize the adapter with the transaction list and click listener
        adapter = TransactionAdap(transactionList, this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onTransactionUpdate(position: Int, newQuantity: Int) {
        // Update the quantity of the transaction at the given position
        transactionList[position].quantity = newQuantity
        adapter.notifyItemChanged(position)
        showToast("Quantity updated")
    }

    override fun onTransactionDelete(position: Int) {
        // Handle delete button click
        transactionList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }
}









