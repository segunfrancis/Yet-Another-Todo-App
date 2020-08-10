package com.project.segunfrancis.yetanothertodoapp.ui.list

import com.project.segunfrancis.yetanothertodoapp.data.ToDo

/**
 * Created by SegunFrancis
 *
 * Interface to handle adapter item clicks
 */

interface OnItemClickListener {
    fun onCheckboxChecked(id: String)
    fun onDeleteIconClicked(toDo: ToDo)
}