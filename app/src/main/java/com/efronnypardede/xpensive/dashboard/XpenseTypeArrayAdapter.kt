package com.efronnypardede.xpensive.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.efronnypardede.xpensive.data.source.local.XpenseTypeMapper
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class XpenseTypeArrayAdapter @Inject constructor(
    @ActivityContext context: Context,
    private val xpenseTypeMapper: XpenseTypeMapper,
) : ArrayAdapter<Pair<Int, String>>(
    context,
    android.R.layout.simple_spinner_dropdown_item,
    xpenseTypeMapper.xpenseTypeMap.toList()
) {
    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createAndSetTextView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createAndSetTextView(position, convertView, parent)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)!!.first.toLong()
    }

    private fun createAndSetTextView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = if (convertView == null) {
            inflater.inflate(
                android.R.layout.simple_spinner_dropdown_item,
                parent,
                false
            ) as TextView
        } else {
            convertView as TextView
        }

        return textView.apply {
            text = getItem(position)!!.second
        }
    }
}
