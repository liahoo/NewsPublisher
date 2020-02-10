package com.candyapp.appsflyer.ui

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appsflyer.support.publisher.R

class RecyclerAdapter(inParams: MutableList<Array<String?>>? = null): RecyclerView.Adapter<RecyclerViewHolder>() {

    private val params: MutableList<Array<String?>> = mutableListOf()
    init {
        inParams?.let {
            params.addAll(it)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_key_value, parent, false))
    }

    override fun getItemCount(): Int {
        return params.size
    }

    fun add(key: String? = null, value: String?=null){
        params.add(arrayOf(key, value))
    }
    fun getAll(): List<Array<String?>> {
        return params
    }
    fun get(position: Int): Array<String?>?{
        if(position < params.size) {
            return params.get(position)
        } else{
            return null
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        with(params.get(position)) {
            holder.inputKey.setText(get(0))
            holder.inputKey.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    params.get(holder.adapterPosition)[0] = s?.toString().takeUnless { TextUtils.isEmpty(it) }
                }
            })
            holder.inputValue.setText(get(1))
            holder.inputValue.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    params.get(holder.adapterPosition)[1] = s?.toString()
                }
            })
        }
    }
}