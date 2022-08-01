package com.efronnypardede.xpensive.manage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.databinding.ItemXpenseSourceBinding
import java.text.NumberFormat
import javax.inject.Inject

class XpenseSourceListAdapter @Inject constructor(
    private val numberFormatter: NumberFormat
) :
    ListAdapter<XpenseSource, XpenseSourceListAdapter.ViewHolder>(DIFF_UTIL) {

    class ViewHolder(
        private val binding: ItemXpenseSourceBinding,
        private val numberFormatter: NumberFormat,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: XpenseSource) {
            binding.apply {
                this.data = data
                numberFormatter = this@ViewHolder.numberFormatter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemXpenseSourceBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, numberFormatter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<XpenseSource>() {
            override fun areItemsTheSame(oldItem: XpenseSource, newItem: XpenseSource): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: XpenseSource, newItem: XpenseSource): Boolean {
                return oldItem == newItem
            }

        }
    }
}