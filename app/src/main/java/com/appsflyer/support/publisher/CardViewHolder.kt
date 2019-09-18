package com.appsflyer.support.publisher

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CardViewHolder(
        parent: ViewGroup,
        v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
): RecyclerView.ViewHolder(v) {
    private var textView: TextView? = v.findViewById(R.id.textView)
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