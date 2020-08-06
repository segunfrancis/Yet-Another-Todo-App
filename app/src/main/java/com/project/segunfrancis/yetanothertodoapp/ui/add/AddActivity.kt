package com.project.segunfrancis.yetanothertodoapp.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.yetanothertodoapp.databinding.ActivityAddBinding
import com.project.segunfrancis.yetanothertodoapp.hide
import com.project.segunfrancis.yetanothertodoapp.obtainViewModel
import com.project.segunfrancis.yetanothertodoapp.show
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addViewModel = obtainViewModel(AddViewModel::class.java)

        binding.due.date = System.currentTimeMillis()
        binding.due.setOnDateChangeListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            addViewModel.toDo.dueDate = calendar.timeInMillis
            binding.clearDue.show()
        }

        binding.save.setOnClickListener { view ->
            addViewModel.toDo.title = binding.txtTitle.text.toString()

            val message = addViewModel.save()
            if (message != null) {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
            } else {
                finish()
            }
        }

        binding.clearDue.setOnClickListener {
            addViewModel.toDo.dueDate = null
            binding.clearDue.hide()
            binding.due.hide()
            binding.setDue.show()
        }

        binding.setDue.setOnClickListener {
            binding.clearDue.show()
            binding.due.show()
            binding.setDue.hide()
        }
    }
}