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

class PublishActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        val body=findViewById<EditText>(R.id.body)
        val submit=findViewById<Button>(R.id.pbtn)
        submit.setOnClickListener {
            if (body.text.toString().isBlank()){

            }else{
                val api:api=RetrofitInstance.client.create(api::class.java)
                val b=postbody(body.text.toString())
                val call=api.publishapost(b)
                call.enqueue(object :Callback<message>{
                    override fun onResponse(call: Call<message>, response: Response<message>) {
                        val intent= Intent(this@PublishActivity,MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<message>, t: Throwable) {

                    }

                })
            }
        }

    }
}