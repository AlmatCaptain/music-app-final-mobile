package com.example.musicapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Firebase {
    companion object {
        val auth by lazy { FirebaseAuth.getInstance() }
        val database by lazy { FirebaseFirestore.getInstance() }
    }
}