package com.example.Mandi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Mandi.model.OtherMandi

class MandiAdapter(val context:Context, val items: List<OtherMandi>):RecyclerView.Adapter<MandiAdapter.MclassViewHolder>() {

//    lateinit var MItems:MutableList<OtherMandi>
//
//    init {
//        MItems = items as MutableList<OtherMandi>
//    }
    class MclassViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.mandiname)
        val disname = itemView.findViewById<TextView>(R.id.disname)
        val image = itemView.findViewById<ImageView>(R.id.mandiimage)
        val state = itemView.findViewById<TextView>(R.id.state)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MclassViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mandi_layout, parent, false)
        return MclassViewHolder(view)
    }

    override fun onBindViewHolder(holder: MclassViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.hindi_name
        holder.disname.text = item.district
        holder.state.text = item.state
        Glide.with(context).load(item.image).fitCenter().into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}