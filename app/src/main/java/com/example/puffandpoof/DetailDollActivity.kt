package com.example.puffandpoof

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.puffandpoof.Fragment.TransactionFragment
import `object`.Transactionlist

class DetailDollActivity : AppCompatActivity() {

    lateinit var ttl: TextView
    lateinit var ids: TextView // TextView to display the doll ID
    lateinit var addToCartButton: Button
    lateinit var quantityPicker: NumberPicker

    var tittle: String = ""
    var idz: String = ""
    private lateinit var ImageView: ImageView
    private lateinit var button: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doll)

        ttl = findViewById(R.id.ttl)
        ids = findViewById(R.id.dollID) // Initialize the TextView to display the doll ID
        ImageView = findViewById(R.id.dtldoll)
        button = findViewById(R.id.backhome)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        quantityPicker = findViewById(R.id.quantity_picker)

        tittle = intent.getStringExtra("tittle") ?: ""
        idz = intent.getStringExtra("dollId") ?: "" // Get the doll ID from the intent
        val img = intent.getIntExtra("img", R.drawable.snorlax)

        ImageView.setImageResource(img)

        button.setOnClickListener {
            finish()
        }

        quantityPicker.minValue = 1
        quantityPicker.maxValue = 10

        addToCartButton.setOnClickListener {
            val quantity = quantityPicker.value
            Transactionlist.addTransaction(idz, tittle, quantity, "your_date_here")
            showToast("Item added to cart")

            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment is TransactionFragment) {
                fragment.updateAdapter()
            }
        }

        ttl.text = tittle
        ids.text = "ID: $idz" // Set the doll ID text
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}




