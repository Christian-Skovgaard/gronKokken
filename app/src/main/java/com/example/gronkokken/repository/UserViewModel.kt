package com.example.gronkokken.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    private val _role = mutableStateOf(Role.Undefined)
    val role: State<Role> = _role

    fun setRole(newRole: Role) {
        _role.value = newRole
    }

    var classId:String? = null
    var className:String? = null

    var schoolId:String? = null
    var schoolName:String? = null

    enum class Role {
        Undefined, Student, Teacher, Guest
    }


}

