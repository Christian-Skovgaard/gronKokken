package com.example.gronkokken.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    //Lukas
    //private mutable val so we can change the role and get the ui to update
    private val _role = mutableStateOf(Role.Undefined)
    //role value that the ui can observe
    val role: State<Role> = _role

    //update the mutable _role value
    //e.g. userViewModel.setRole(UserViewModel.Role.Teacher) from Landingpage.kt
    // here the function takes UserViewModel.Role.Teacher as newRole and updates the _role.value to that
    fun setRole(newRole: Role) {
        _role.value = newRole
    }

    //Christian
    var classId:String? = null
    var className:String? = null

    var schoolId:String? = null
    var schoolName:String? = null

    enum class Role {
        Undefined, Student, Teacher, Guest
    }


}

