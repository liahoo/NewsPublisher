package com.appsflyer.support.publisher

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class CardRecyclerAdapter<CardItem: CardBindable>() : RecyclerView.Adapter<CardViewHolder>() {

    private var list: MutableList<CardItem> = mutableListOf()
    override fun getItemCount(): Int {
        return list.size
    }

    fun add(item: CardItem){
        list.add(item)
    }

    fun addAll(list: Collection<CardItem>?){
        list?.forEach { add(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return get(position)?.type ?: 0
    }
    fun get(position: Int): CardItem? = if (position<list.size) list.get(position) else null


    override fun onBindViewHolder(vh: CardViewHolder, position: Int) {
        list.takeIf { vh.adapterPosition < list.size }?.get(vh.adapterPosition)?.let {
            vh.setText(it.type, it.text, it.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(parent)
    }
}
