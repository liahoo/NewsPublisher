package com.appsflyer.support.publisher

import android.support.v7.widget.RecyclerView
import android.view.View
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

    fun getAll(): List<CardItem> = list

    override fun onBindViewHolder(vh: CardViewHolder, position: Int) {
        list.takeIf { vh.adapterPosition < list.size }?.get(vh.adapterPosition)?.let {
//            vh.setAdvertise(it.adText, it.adImage)
//            vh.setArticle(it.articleText, it.articleImage)
//            vh.setOnClickAdListenr {
//                with(list.get(vh.adapterPosition)) { onClick?.invoke(this) }
//            }
//            vh.setOnLongClickAdListener {
//                with(list.get(vh.adapterPosition)) { onLongClick?.invoke(this)}
//            }
            vh.setText(it.type, it.text, it.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(parent)
    }
}
