package com.example.jasebook

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.ComponentActivity

class SignupActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val switchtosigninbtn=findViewById<Button>(R.id.switchtosigninbtn)

        switchtosigninbtn.setOnClickListener {
            val intent=Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
    }
}