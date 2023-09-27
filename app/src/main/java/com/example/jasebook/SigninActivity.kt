package com.example.jasebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class SigninActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val switchtosignupbtn=findViewById<Button>(R.id.switchtosignupbtn)
        val signingbtn=findViewById<Button>(R.id.signinbtn)
        val name=findViewById<EditText>(R.id.namefield)
        val password=findViewById<EditText>(R.id.passwordfield)
        signingbtn.setOnClickListener {
            val s=signingdata(name.text.toString(),password.text.toString())
            var api: api = RetrofitInstance.client.create(api::class.java)
            var call=api.postsignin(s)
            call.enqueue(object :Callback<message>{
                override fun onResponse(
                    call: Call<message>,
                    response: Response<message>
                ) {
                    runOnUiThread {

                        if (response.isSuccessful) {

                            signed=true
                            val intent=Intent(this@SigninActivity,MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.d("abbas", "failed" + response.body())
                        }
                    }
                }

                override fun onFailure(call: Call<message>, t: Throwable) {

                }

            })
        }

        switchtosignupbtn.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

    }

}

