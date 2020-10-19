package com.project.segunfrancis.yetanothertodoapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.databinding.ActivityToDoListBinding
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListActivity : AppCompatActivity(), OnItemClickListener {

    private val binding: ActivityToDoListBinding by lazy {
        ActivityToDoListBinding.inflate(layoutInflater)
    }

    private val toDoListViewModel: ToDoListViewModel by viewModels()

    private val toDoAdapter: ToDoAdapter by lazy {
        ToDoAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            startActivity(Intent(this@ToDoListActivity, AddActivity::class.java))
        }

        binding.include.listTodos.apply {
            layoutManager = LinearLayoutManager(this@ToDoListActivity)
            adapter = toDoAdapter
        }

        toDoListViewModel.toDoList.observe(this, { toDos ->
            binding.include.emptyImage.isVisible = toDos.isEmpty()
            toDoAdapter.submitList(toDos)
        })

        toDoListViewModel.count.observe(this, { count ->
            binding.include.soonValue.text = count.toString()
        })
    }

    override fun onCheckboxChecked(id: String) {
        toDoListViewModel.toggleToDo(id)
    }

    override fun onDeleteIconClicked(toDo: ToDo) {
        toDoListViewModel.deleteToDo(toDo)
    }
}