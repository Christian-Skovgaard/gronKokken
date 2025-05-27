package com.example.gronkokken.components

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class UserViewModel: ViewModel() {

    var role:Role = Role.Undefined

    var classId:String? = null
    var className:String? = null

    var schoolId:String? = null
    var schoolName:String? = null

    enum class Role {
        Undefined, Student, Teacher, Guest
    }

    val userId: String?
        get() = FirebaseAuth.getInstance().currentUser?.uid
}

