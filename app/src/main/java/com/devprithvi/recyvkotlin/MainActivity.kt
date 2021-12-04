package com.devprithvi.recyvkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


/*
1. we created the listitem.
2.adapter class
3.Add and Remove the list item  in the ItemLIst
::::implemet two method  1. insertItem
                        2.removeItem
4.Implementing the click listener on item...
 */

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {
    private val itemlist = generateDummyList(500)
    private val adapter = ItemAdapter(itemlist, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyler_view.adapter = adapter
        recyler_view.layoutManager = LinearLayoutManager(this)
        recyler_view.setHasFixedSize(true)

    }

    fun insertItem(view: View) {
        val index: Int = Random.nextInt(8)
        val newItem = ListItem(
            R.drawable.ic_android,
            "New Item at position $index",
            "Line 2"
        )
        itemlist.add(index, newItem) //to add the new item
        adapter.notifyItemInserted(index)

    }

    fun removeItem(view: View) {
        val index: Int = Random.nextInt(8)  //define a random value between 0-7 position
        itemlist.removeAt(index) //remove by index no.
        adapter.notifyItemRemoved(index) //update the adapter as we click the button to remove the item...
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: ListItem = itemlist[position]

        clickedItem.text1 = "clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ListItem> {
        val list = ArrayList<ListItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic__5g
                else -> R.drawable.ic_sunny
            }
            val item = ListItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}