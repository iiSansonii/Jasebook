package com.example.jasebook

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var mAdapter: postadapter?= null;
private var mQuestions: ArrayList<post> = ArrayList()

class MyProfileActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)
        val img=findViewById<ImageView>(R.id.pfp)
        val name=findViewById<TextView>(R.id.myprofile_name)
        val username=findViewById<TextView>(R.id.myprofile_username)
        var api: api = RetrofitInstance.client.create(api::class.java)
        val calluser=api.getme()
        var url = "https://7032-91-106-52-179.ngrok-free.app/img/"

            calluser.enqueue(object : Callback<user> {
                override fun onResponse(call: Call<user>, response: Response<user>) {
                    val user = response.body()
                    if (user != null) {
                        name.text = user.name
                        username.text = user.username
                        url += user.img
                        Log.d("popo", url)
                        Glide.with(this@MyProfileActivity)
                            .load(url)
                            .fitCenter()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.img_1).into(img)
                    }
                }

                override fun onFailure(call: Call<user>, t: Throwable) {

                }

            })















        val callpost=api.getuserpost()
        val rec = findViewById<RecyclerView>(R.id.myprofile_rec)
        rec.layoutManager = LinearLayoutManager(this)
        mAdapter=postadapter(mQuestions)
        callpost.enqueue(object : Callback<ArrayList<post>> {
            override fun onResponse(
                call: Call<ArrayList<post>>,
                response: Response<ArrayList<post>>
            ) {
                runOnUiThread {
                    val posts = response.body()
                    Log.d("hey", posts.toString())
                    if (posts != null) {
                        mQuestions.addAll(posts)
                        mAdapter!!.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<post>>, t: Throwable) {
                Log.d("jasim", "Got error : " + t.localizedMessage)
            }

        })

        rec.adapter=mAdapter

    }

}