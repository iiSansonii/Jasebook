package com.example.jasebook

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CommentActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        var mAdapter: commentadapter?= null;
        var mComments: ArrayList<comment> = ArrayList()
        val rec=findViewById<RecyclerView>(R.id.commrec)
        rec.layoutManager=LinearLayoutManager(this)
        mAdapter=commentadapter(mComments)
        val api:api=RetrofitInstance.client.create()
        val call=api.getcomment(pid)
        call.enqueue(object : Callback<ArrayList<comment>> {
            override fun onResponse(
                call: Call<ArrayList<comment>>,
                response: Response<ArrayList<comment>>
            ) {

                    runOnUiThread {
                        val comments = response.body()
                        Log.d("hey", comments.toString())
                        if (comments != null) {
                            mComments.addAll(comments)
                            mAdapter!!.notifyDataSetChanged()
                        }
                    }
            }

            override fun onFailure(call: Call<ArrayList<comment>>, t: Throwable) {

            }


        })
        rec.adapter=mAdapter

        val commbody=findViewById<EditText>(R.id.commfield)
        val commbtn=findViewById<Button>(R.id.commsub)
        commbody.setOnClickListener {
            if(!commbody.text.toString().isBlank()) {
                val comm = postbody(commbody.text.toString())
                val callcom=api.postcomment(pid,comm)
                callcom.enqueue(object : Callback<message>{
                    override fun onResponse(call: Call<message>, response: Response<message>) {
                        if(response.isSuccessful){

                        }else{
                            Log.d("comment",response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<message>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }
    }
}