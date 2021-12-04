package com.devprithvi.recyvkotlin

import android.graphics.Color
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(
    private val itemlist: List<ListItem>,
    private val listener: OnItemClickListener


) :
    RecyclerView.Adapter<ItemAdapter.ItemVIewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVIewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemVIewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemVIewHolder, position: Int) {
        val currrentItem = itemlist[position]


        // holder.itemView.setOnClickListener()


        holder.imageView.setImageResource(currrentItem.imageResources)
        holder.textView1.text = currrentItem.text1
        holder.textView2.text = currrentItem.text2

    }

    override fun getItemCount() = itemlist.size

    inner class ItemVIewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        //viewholder represents single row in a list]
        //inner is similar to the static class of java....???

        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)

        //to implement the click listener on every item in the list
        //last part i done here ..

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = bindingAdapterPosition  //changed...
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}