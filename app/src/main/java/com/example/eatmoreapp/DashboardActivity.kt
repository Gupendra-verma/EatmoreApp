package com.example.eatmoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.mikhaellopez.circularimageview.CircularImageView

class DashboardActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val name = findViewById<TextView>(R.id.username)
        val email = findViewById<TextView>(R.id.userEmail)
        val myImage = findViewById<CircularImageView>(R.id.myImage)
        val signOut = findViewById<Button>(R.id.signOut)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        name.text = user?.displayName
        email.text = user?.email

        Glide.with(this).load(user?.photoUrl).centerCrop().into(myImage)

        signOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}