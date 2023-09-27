package com.example.jasebook

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class SignupActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val switchtosigninbtn=findViewById<Button>(R.id.switchtosigninbtn)
        val name=findViewById<EditText>(R.id.txtuser)
        val username=findViewById<EditText>(R.id.txtusername)
        val password=findViewById<EditText>(R.id.txtpass)
        val signup=findViewById<Button>(R.id.btnsignup)
        switchtosigninbtn.setOnClickListener {
            val intent=Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener{
            val api:api=RetrofitInstance.client.create(api::class.java)
            val s=signupdata(name.text.toString(),password.text.toString(),username.text.toString())
            val call=api.postsignup(s)
            call.enqueue(object :Callback<message>{
                override fun onResponse(call: Call<message>, response: Response<message>) {
                    if(response.isSuccessful){
                        signed=true
                        val intent=Intent(this@SignupActivity,MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<message>, t: Throwable) {

                }


            })
        }
    }
}