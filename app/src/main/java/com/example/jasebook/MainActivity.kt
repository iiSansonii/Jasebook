package com.example.jasebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import com.example.jasebook.postadapter
var u=user("","","")
var signed=false

var pid:Int=-1
class MainActivity : ComponentActivity() {
    private var mAdapter: postadapter?= null;
    private var mQuestions: ArrayList<post> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed)
        val rec = findViewById<RecyclerView>(R.id.rec)
        rec.layoutManager = LinearLayoutManager(this)
        mAdapter=postadapter(mQuestions)

        var api: api = RetrofitInstance.client.create(api::class.java)
        val call=api.getfeed()
        call.enqueue(object :Callback<ArrayList<post>>{
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



        val signinbtn=findViewById<Button>(R.id.gosigninbtn)

        signinbtn.setOnClickListener{
            val intent=Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        val switchtome=findViewById<Button>(R.id.myprofilebtn)
        switchtome.setOnClickListener {
            val intent=Intent(this,MyProfileActivity::class.java)
            startActivity(intent)
        }

        val publish=findViewById<Button>(R.id.publish)

        val logoutbtn=findViewById<Button>(R.id.logoutbtn)
        if(signed==false){
            logoutbtn.visibility= View.GONE
            switchtome.visibility=View.GONE
            publish.visibility=View.GONE
        }else{
            signinbtn.visibility=View.GONE
        }
        logoutbtn.setOnClickListener {
            val call=api.logout()
            call.enqueue(object :Callback<message>{
                override fun onResponse(call: Call<message>, response: Response<message>) {
                    if(response.body()?.message=="loged out"){
                        signed=false
                        val intent=Intent(this@MainActivity,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        val m=response.body()?.message
                        if(m!=null) {
                            Log.d("yyyttt", m)
                        }
                    }
                }

                override fun onFailure(call: Call<message>, t: Throwable) {

                }

            })
        }
        publish.setOnClickListener{
            val intent=Intent(this@MainActivity,PublishActivity::class.java)
            startActivity(intent)
        }




    }
}