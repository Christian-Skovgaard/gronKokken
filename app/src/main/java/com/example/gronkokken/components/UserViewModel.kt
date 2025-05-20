package com.example.gronkokken.components

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var role:Role = Role.Undefined

    var classId:String? = null
    var className:String? = null

    var schoolId:String? = null
    var schoolName:String? = null

    enum class Role {
        Undefined, Student, Teacher, Guest
    }
}

