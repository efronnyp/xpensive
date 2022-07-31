package com.efronnypardede.xpensive.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.xpensive.data.entity.XpenseSource

@BindingAdapter(value = ["data"])
fun setData(recyclerView: RecyclerView, expenseSourceList: List<XpenseSource>?) {
    expenseSourceList.takeIf { it != null }?.let {
        recyclerView.adapter?.notifyItemChanged(2)
    }
}
