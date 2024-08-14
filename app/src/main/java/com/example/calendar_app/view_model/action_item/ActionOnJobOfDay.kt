package com.example.calendar_app.view_model.action_item

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.domain.model.calendar.Job
import com.domain.model.calendar.JobStatus
import com.example.calendar_app.R
import com.example.calendar_app.adapter.RecyclerAdapter
import com.example.calendar_app.adapter.action.IRecyclerAction
import com.example.calendar_app.utils.colorList
import com.example.calendar_app.view_model.CalendarOfWeekViewModel
import java.util.Locale


class ActionOnJobOfDay
    (
    private val context: Context,
    private val calendarViewModel: CalendarOfWeekViewModel
) : IRecyclerAction<Job> {

    private var isSelected: Boolean = false

    private var imgTickCompleted: ImageView? = null
    private var txtExercise: TextView? = null
    private var txtStatus: TextView? = null
    private var cvJob: CardView? = null
    private var ivDot: ImageView? = null

    private var txtJobName: TextView? = null

    override fun onClick(t: Job) {
        println("ActionInJobOfDay")
    }

    override fun bindingDataToItemLayout(
        holder: RecyclerAdapter<Job>.ViewHolder,
        position: Int,
        data: List<Job>
    ) {
        cvJob = holder.itemView.findViewById(R.id.cv_job)

        imgTickCompleted = holder.itemView.findViewById(R.id.img_tick_completed)
        imgTickCompleted!!.setOnClickListener(
            onClickCompleteJob(
                imgTickCompleted!!,
                data[position]
            )
        )

        txtExercise = holder.itemView.findViewById(R.id.txt_exercise_number)
        txtStatus = holder.itemView.findViewById(R.id.txt_status)

        ivDot = holder.itemView.findViewById(R.id.iv_dot)

        txtJobName = holder.itemView.findViewById(R.id.txt_job_name)

        setDataInJob(position, data)
        setIconCompleted(data[position])
        setColorInStatus(holder, data[position])
    }

    @SuppressLint("ResourceAsColor")
    private fun setColorInStatus(holder: RecyclerAdapter<Job>.ViewHolder, job: Job) {

        when (job.status) {
            JobStatus.MISSED -> {
                cvJob!!.setCardBackgroundColor(context.colorList(R.color.c_f7f8fc))
                txtStatus!!.setTextColor(context.colorList(R.color.red))
            }

            JobStatus.COMPLETED -> {
                cvJob!!.setCardBackgroundColor(context.colorList(R.color.c_7470ef))
                setColorForTextJob(holder, R.color.white)

                txtExercise!!.isVisible = false
                ivDot!!.isVisible = false

                imgTickCompleted!!.apply {
                    setImageResource(R.drawable.completed_icon)
                    setOnClickListener(null)
                    isClickable = false
                }
            }

            else -> setColorForTextJob(holder, R.color.silver)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataInJob(
        position: Int,
        jobs: List<Job>
    ) {
        txtJobName!!.text = jobs[position].name

        val status = jobs[position].status
        if (status != JobStatus.ASSIGNED) {
            txtStatus!!.text =
                status.name.substring(0, 1) + status.name.substring(1, status.name.length)
                    .lowercase(Locale.ROOT)
        } else {
            ivDot!!.isVisible = false
        }

        when (jobs.size) {
            0 -> txtExercise!!.text = ""
            1 -> txtExercise!!.text = jobs[position].exercises.toString() + " exercise"
            else -> txtExercise!!.text = jobs[position].exercises.toString() + " exercises"
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun onClickCompleteJob(
        imgView: ImageView,
        job: Job
    ): View.OnClickListener {
        return View.OnClickListener {
            isSelected = imgView.tag.toString().toBooleanStrict()
            if (!isSelected) {
                imgView.setImageResource(R.drawable.completed_icon_purple)
            } else {
                imgView.setImageResource(R.drawable.completed_icon)
            }
            imgView.tag = !isSelected

            job.isSelected = !isSelected
            calendarViewModel.updateIsSelected(job)
        }
    }

    private fun setIconCompleted(job: Job) {
        if (job.isSelected) {
            imgTickCompleted!!.setImageResource(R.drawable.completed_icon_purple)
        } else {
            imgTickCompleted!!.setImageResource(R.drawable.completed_icon)
        }
        imgTickCompleted!!.tag = job.isSelected
    }

    private fun setColorForTextJob(holder: RecyclerAdapter<Job>.ViewHolder, color: Int) {
        txtJobName!!.setTextColor(context.colorList(color))
        txtStatus!!.setTextColor(context.colorList(color))
        txtExercise!!.setTextColor(context.colorList(color))
    }
}