package com.example.calendar_app.adapter.action

import com.example.calendar_app.adapter.RecyclerAdapter

interface IRecyclerAction<T> {
    fun onClick(t: T)
    fun bindingDataToItemLayout(holder: RecyclerAdapter<T>.ViewHolder, position: Int, data: List<T>)
}