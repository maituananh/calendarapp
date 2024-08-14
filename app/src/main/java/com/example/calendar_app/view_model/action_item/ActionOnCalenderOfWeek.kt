package com.example.calendar_app.view_model.action_item

import android.content.Context
import android.os.Build
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.domain.model.calendar.Calendar
import com.domain.model.calendar.Job
import com.example.calendar_app.R
import com.example.calendar_app.adapter.RecyclerAdapter
import com.example.calendar_app.adapter.action.IRecyclerAction
import com.example.calendar_app.utils.colorList
import com.example.calendar_app.view_model.CalendarOfWeekViewModel
import java.time.LocalDate

class ActionOnCalenderOfWeek(
    val context: Context,
    val calendarViewModel: CalendarOfWeekViewModel
) : IRecyclerAction<Calendar>,
    RecyclerView.OnItemTouchListener {

    private var llDayOfWeek: LinearLayout? = null
    private var rvJob: RecyclerView? = null
    private var txtDayOfWeek: TextView? = null
    private var txtDayOfMonth: TextView? = null

    override fun onClick(t: Calendar) {
        Toast.makeText(context, "onClick123", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Calendar>.ViewHolder,
        position: Int,
        data: List<Calendar>
    ) {
        llDayOfWeek = holder.itemView.findViewById(R.id.ll_day_of_week)
        rvJob = holder.itemView.findViewById(R.id.rvJob)
        txtDayOfWeek = holder.itemView.findViewById(R.id.txt_day_of_week)
        txtDayOfMonth = holder.itemView.findViewById(R.id.txt_day_of_month)

        attachJobOfDayLayout(data[position].jobs)
        setTextAndColorForDayOfWeekAndOfMonth(data[position])
        resizeLinearLayoutDayOfWeek(data[position].jobs)
    }

    private fun attachJobOfDayLayout(jobs: List<Job>) {
        val linearLayoutManager = LinearLayoutManager(
            rvJob!!.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvJob!!.apply {
            adapter =
                RecyclerAdapter(
                    jobs,
                    ActionOnJobOfDay(context, calendarViewModel),
                    R.layout.layout_job_of_day,
                )
            layoutManager = linearLayoutManager
            setRecycledViewPool(RecyclerView.RecycledViewPool())
            addOnItemTouchListener(this@ActionOnCalenderOfWeek)
        }
    }

    private fun resizeLinearLayoutDayOfWeek(jobData: List<Job>) {
        if (jobData.size > 1) {
            llDayOfWeek!!.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 610)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTextAndColorForDayOfWeekAndOfMonth(calendar: Calendar) {
        calendar.date?.let {
            txtDayOfWeek!!.text = it.dayOfWeek.toString().substring(0, 3)
            txtDayOfMonth!!.text = it.dayOfMonth.toString()
        }

        if (calendar.date!!.isEqual(LocalDate.now())) {
            txtDayOfWeek!!.setTextColor(context.colorList(R.color.c_7470ef))
            txtDayOfMonth!!.setTextColor(context.colorList(R.color.c_7470ef))
        }
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_MOVE -> {
                rv.parent.requestDisallowInterceptTouchEvent(true)
            }
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("Not yet implemented")
    }
}