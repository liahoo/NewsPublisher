package com.candyapp.appsflyer.ui

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.appsflyer.support.publisher.R

class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var inputKey: EditText = view.findViewById<EditText>(R.id.inputKey)
    var inputValue: EditText = view.findViewById<EditText>(R.id.inputValue)
}