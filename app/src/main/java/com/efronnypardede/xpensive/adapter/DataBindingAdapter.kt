package com.efronnypardede.xpensive.adapter

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.xpensive.dashboard.XpenseHistoryListAdapter
import com.efronnypardede.xpensive.dashboard.XpenseSourceArrayAdapter
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.manage.XpenseSourceListAdapter

@BindingAdapter("list")
fun setXpenseSourceList(recyclerView: RecyclerView, list: List<XpenseSource>?) {
    list?.let((recyclerView.adapter as XpenseSourceListAdapter)::submitList)
}

@BindingAdapter("list")
fun setXpenseHistoryList(recyclerView: RecyclerView, list: List<XpenseHistoryDatabaseView>?) {
    list?.groupBy { it.date }?.toList()
        ?.let((recyclerView.adapter as XpenseHistoryListAdapter)::submitList)
}

@BindingAdapter("list")
fun setXpenseSourceListSpinner(spinner: Spinner, list: List<XpenseSource>?) {
    list?.let {
        spinner.adapter = XpenseSourceArrayAdapter(spinner.context, it)
    }
}
