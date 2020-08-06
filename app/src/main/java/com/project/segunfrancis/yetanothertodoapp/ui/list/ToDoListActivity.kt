package com.project.segunfrancis.yetanothertodoapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.segunfrancis.yetanothertodoapp.data.ToDo
import com.project.segunfrancis.yetanothertodoapp.databinding.ActivityToDoListBinding
import com.project.segunfrancis.yetanothertodoapp.obtainViewModel
import com.project.segunfrancis.yetanothertodoapp.ui.add.AddActivity

class ToDoListActivity : AppCompatActivity(), ToDoAdapter.OnClickListener {

    private lateinit var binding: ActivityToDoListBinding
    private lateinit var toDoListViewModel: ToDoListViewModel
    private val adapter: ToDoAdapter by lazy {
        ToDoAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        toDoListViewModel = obtainViewModel(ToDoListViewModel::class.java)

        binding.fab.setOnClickListener {
            startActivity(Intent(this@ToDoListActivity, AddActivity::class.java))
        }

        binding.include.listTodos.layoutManager = LinearLayoutManager(this)
        binding.include.listTodos.adapter = adapter
        toDoListViewModel.toDoList.observe(this, Observer { toDos ->
            binding.include.emptyImage.isVisible = toDos.isEmpty()
            adapter.setToDos(toDos)
        })

        toDoListViewModel.count.observe(this, Observer { count ->
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