package com.appsflyer.support.publisher

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CardViewHolder(parent: ViewGroup,
                     v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)):
        RecyclerView.ViewHolder(v) {
    private var textView: TextView? = v.findViewById(R.id.textView)
//    private var textAd: TextView? = v.findViewById(R.id.textAd)
//    private var textArticle: TextView? = v.findViewById(R.id.textArticle)
//    private var cardAd: CardView? = v.findViewById(R.id.cardAd)
//
//    fun setOnClickAdListenr(listener: View.OnClickListener){
//        cardAd?.setOnClickListener(listener)
//    }
//    fun setAdvertise(adText: String?, adImage: Int? = null) {
//        textAd?.let { tv->
//            adImage?.let { tv.setCompoundDrawablesWithIntrinsicBounds(0,0,0, adImage) }
//                    ?: Runnable { tv.text = adText }
//        }
//    }
//
//    fun setArticle(title: String?, imageRes: Int? = null){
//        textArticle?.let { tv ->
//            tv.text = title
//            imageRes?.let { tv.setCompoundDrawablesWithIntrinsicBounds(0,0,0, imageRes) }
//        }
//    }
//
//    fun setOnLongClickAdListener(onLongClickListener: View.OnLongClickListener) {
//        cardAd?.setOnLongClickListener(onLongClickListener)
//    }
//    fun setOnClickAdListenr( onClick: (CardViewHolder) -> Unit){
//        cardAd?.setOnClickListener {
//            onClick.invoke(this@CardViewHolder)
//        }
//    }
//
//    fun setOnLongClickAdListener(onLongClick: (CardViewHolder) -> Unit) {
//        cardAd?.setOnLongClickListener {
//            onLongClick.invoke(this@CardViewHolder)
//            true
//        }
//    }

    fun setText(type:Int, text: String?, image: Int?) {
        textView?.let { tv->
            tv.text = text
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, image ?: 0)
            if(type==1) {
                tv.setTextColor(Color.BLUE)
                tv.gravity = Gravity.CENTER
            } else {
                tv.setTextColor(Color.BLACK)
                tv.gravity = Gravity.START
            }
        }
    }
}