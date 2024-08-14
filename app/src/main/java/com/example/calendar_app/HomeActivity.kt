package com.example.calendar_app

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar_app.adapter.RecyclerAdapter
import com.example.calendar_app.databinding.ActivityHomeBinding
import com.example.calendar_app.view_model.CalendarOfWeekViewModel
import com.example.calendar_app.view_model.action_item.ActionOnCalenderOfWeek
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val calendarViewModel: CalendarOfWeekViewModel by viewModels<CalendarOfWeekViewModel>()

    private var llDayOfWeek: LinearLayout? = null
    private var rvJob: RecyclerView? = null
    private var txtDayOfWeek: TextView? = null
    private var txtDayOfMonth: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()

        calendarViewModel.fetchCalendar()

        calendarViewModel.calendarList.observe(this) {
            binding.rvFragmentCalendar.adapter =
                RecyclerAdapter(it, ActionOnCalenderOfWeek(this@HomeActivity,
                    calendarViewModel), R.layout.layout_day_of_week)
        }

        binding.rvFragmentCalendar.layoutManager = GridLayoutManager(
            this@HomeActivity, 1, GridLayoutManager.VERTICAL,
            false
        )
    }



    private fun showLoading() {
        calendarViewModel.loading.observe(this) {
            it?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}