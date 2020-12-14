package com.lionheartapps.rk.osimodel.model

class User {

    var name: String? = null
    var email: String? = null

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    //fun User() {}

    fun User(name: String?, email: String?) {
        this.name = name
        this.email = email
    }
}