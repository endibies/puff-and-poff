package com.example.puffandpoof.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.Fragment.HomeFragment
import com.example.puffandpoof.R
import com.example.puffandpoof.model.doll
//import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.imageview.ShapeableImageView


class DollAdap(private val dolllist: ArrayList<doll>, private val clickListener: HomeFragment) : RecyclerView.Adapter<DollAdap.holder>() {
    interface OnDollClickListener {
        fun onDollClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doll_list,parent,false)
        return holder(itemView)
    }

    override fun getItemCount(): Int {
        return dolllist.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        val crtitem = dolllist[position]
        holder.img.setImageResource(crtitem.image)
        holder.ttl.text = crtitem.tittle
        holder.price.text = crtitem.price


        holder.cardd.setOnClickListener {
                clickListener.onDollClick(position)

        }



    }
    class holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img : ShapeableImageView = itemView.findViewById(R.id.imgdoll)
        val ttl : TextView = itemView.findViewById(R.id.ttldol)
        val price : TextView = itemView.findViewById(R.id.price)
        val cardd: CardView = itemView.findViewById(R.id.carddoll)

    }

}
