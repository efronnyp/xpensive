package com.efronnypardede.xpensive.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.manage.XpenseSourceListAdapter

@BindingAdapter("list")
fun setXpenseSourceList(recyclerView: RecyclerView, list: List<XpenseSource>?) {
    list?.let((recyclerView.adapter as XpenseSourceListAdapter)::submitList)
}

@BindingAdapter("items")
fun setXpenseHistoryList(recyclerView: RecyclerView, list: List<XpenseHistory>?) {
    list?.let {
        //
    }
}
