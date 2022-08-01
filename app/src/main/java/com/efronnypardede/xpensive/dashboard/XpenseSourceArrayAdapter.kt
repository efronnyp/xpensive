package com.efronnypardede.xpensive.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.efronnypardede.xpensive.data.entity.XpenseSource

class XpenseSourceArrayAdapter(
    context: Context,
    items: List<XpenseSource>,
) : ArrayAdapter<XpenseSource>(context, android.R.layout.simple_spinner_dropdown_item, items) {
    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createAndSetTextView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createAndSetTextView(position, convertView, parent)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)!!.id
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
            text = getItem(position)!!.name
        }
    }
}
