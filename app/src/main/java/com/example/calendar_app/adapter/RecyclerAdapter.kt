package com.example.calendar_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar_app.adapter.action.IRecyclerAction

class RecyclerAdapter<T>(
    private val data: List<T>,
    private val action: IRecyclerAction<T>?,
    private val layoutItem: Int,
) : RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutItem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        action?.bindingDataToItemLayout(holder, position, data)

        holder.itemView.setOnClickListener {
            action?.onClick(data[position])
        }
    }
}