package com.efronnypardede.xpensive.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView
import com.efronnypardede.xpensive.data.source.local.XpenseTypeMapper
import com.efronnypardede.xpensive.databinding.ItemXpenseHistoryBinding
import com.efronnypardede.xpensive.di.FullDateFormatter
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class XpenseHistoryListAdapter @Inject constructor(
    private val numberFormatter: NumberFormat,
    @FullDateFormatter private val dateFormatter: DateFormat,
    private val xpenseTypeMapper: XpenseTypeMapper,
) : ListAdapter<Pair<Date, List<XpenseHistoryDatabaseView>>, XpenseHistoryListAdapter.ViewHolder>(
    DIFF_UTIL
) {
    class ViewHolder(
        private val binding: ItemXpenseHistoryBinding,
        private val numberFormatter: NumberFormat,
        private val xpenseTypeMapper: XpenseTypeMapper,
        private val dateFormatter: DateFormat,
        private val isHeader: Boolean = false,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: XpenseHistoryDatabaseView) {
            binding.apply {
                dateFormatter = this@ViewHolder.dateFormatter
                numberFormatter = this@ViewHolder.numberFormatter
                xpenseTypeMapper = this@ViewHolder.xpenseTypeMapper
                this.data = data

                textviewXpenseDate.isVisible = isHeader
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemXpenseHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(
            dataBinding,
            numberFormatter,
            xpenseTypeMapper,
            dateFormatter,
            isHeader = viewType == HEADER_TYPE_ID
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getData(position))
    }

    override fun getItemViewType(position: Int): Int {
        var isHeader = false
        var itemIndex = 0

        for (i in 0 until currentList.size) {
            if (itemIndex == position) {
                isHeader = true
                break
            } else if (itemIndex < position) {
                itemIndex += currentList[i].second.size
            } else {
                break // Definitely not a header
            }
        }

        return if (isHeader) HEADER_TYPE_ID else OTHER_TYPE_ID
    }

    override fun getItemCount(): Int = currentList.sumOf { it.second.size }

    fun getData(position: Int): XpenseHistoryDatabaseView {
        var chapter = 0

        for (p in currentList) {
            if (position < chapter + p.second.size) {
                return p.second[position - chapter]
            }
            chapter += p.second.size
        }
        throw IndexOutOfBoundsException("Unexpected Out of Bounds Exception!")
    }

    companion object {
        private const val HEADER_TYPE_ID = 100
        private const val OTHER_TYPE_ID = 200

        val DIFF_UTIL =
            object : DiffUtil.ItemCallback<Pair<Date, List<XpenseHistoryDatabaseView>>>() {
                override fun areItemsTheSame(
                    oldItem: Pair<Date, List<XpenseHistoryDatabaseView>>,
                    newItem: Pair<Date, List<XpenseHistoryDatabaseView>>
                ): Boolean =
                    oldItem.first == newItem.first && oldItem.second.size == newItem.second.size

                override fun areContentsTheSame(
                    oldItem: Pair<Date, List<XpenseHistoryDatabaseView>>,
                    newItem: Pair<Date, List<XpenseHistoryDatabaseView>>
                ): Boolean = oldItem.second == newItem.second
            }
    }

}
