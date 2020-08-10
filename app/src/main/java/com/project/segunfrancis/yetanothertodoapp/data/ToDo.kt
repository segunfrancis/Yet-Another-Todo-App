package com.project.segunfrancis.yetanothertodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SegunFrancis
 */

@Entity
data class ToDo(
    @PrimaryKey var id: String,
    var title: String,
    var dueDate: Long?,
    var completed: Boolean,
    var created: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ToDo

        if (id != other.id) return false
        if (title != other.title) return false
        if (dueDate != other.dueDate) return false
        if (completed != other.completed) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (dueDate?.hashCode() ?: 0)
        result = 31 * result + completed.hashCode()
        result = 31 * result + created.hashCode()
        return result
    }
}