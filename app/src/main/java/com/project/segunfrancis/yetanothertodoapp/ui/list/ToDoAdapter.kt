package com.project.segunfrancis.yetanothertodoapp.ui.list

import android.os.Build
import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.databinding.TodoItemBinding
import com.project.segunfrancis.yetanothertodoapp.hide
import java.util.*

/**
 * Created by SegunFrancis
 */

class ToDoAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var allToDos: List<ToDo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = allToDos.size

    fun setToDos(toDos: List<ToDo>) {
        this.allToDos = toDos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) =
        holder.bind(allToDos[position], onClickListener)

    class ToDoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo, clickListener: OnClickListener) {
            binding.completed.isChecked = toDo.completed
            binding.completed.setOnClickListener { clickListener.onCheckboxChecked(toDo.id) }
            binding.delete.setOnClickListener { clickListener.onDeleteIconClicked(toDo) }

            binding.title.text = toDo.title
            val calender = Calendar.getInstance()
            val dateFormat = DateFormat.getDateFormat(binding.root.context)

            calender.timeInMillis = toDo.created
            binding.start.text = dateFormat.format(calender.time)

            if (toDo.dueDate != null) {
                calender.timeInMillis = toDo.dueDate!!
                binding.due.text = dateFormat.format(calender.time)
            } else {
                binding.due.hide()
                binding.dueLabel.hide()
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.card.setCardBackgroundColor(
                    binding.root.context.getColor(
                        determineCardColor(
                            toDo.dueDate,
                            toDo.completed
                        )
                    )
                )
            }
        }
    }

    interface OnClickListener {
        fun onCheckboxChecked(id: String)
        fun onDeleteIconClicked(toDo: ToDo)
    }
}