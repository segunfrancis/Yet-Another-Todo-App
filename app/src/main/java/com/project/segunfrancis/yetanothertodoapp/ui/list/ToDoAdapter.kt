package com.project.segunfrancis.yetanothertodoapp.ui.list

import android.os.Build
import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.databinding.TodoItemBinding
import com.project.segunfrancis.yetanothertodoapp.hide
import java.util.*

/**
 * Created by SegunFrancis
 */

class ToDoAdapter(private val onClickListener: OnItemClickListener) :
    ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) =
        holder.bind(getItem(position), onClickListener)

    class ToDoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo, clickListener: OnItemClickListener) {
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

    class MyDiffUtil : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.equals(newItem)
        }
    }
}