package com.example.jasebook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class postadapter(private val mList: ArrayList<post>): RecyclerView.Adapter<postadapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.post_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts=mList[position]
        holder.name.text=posts.name
        holder.body.text=posts.body
        holder.date.text=posts.date
        if(u.name!=posts.name){
            holder.Editbtn.visibility=View.GONE
            holder.Deletebtn.visibility=View.GONE
        }

        val api:api=RetrofitInstance.client.create(api::class.java)
        val calllike=api.getnumberoflikes(posts.id)
        calllike.enqueue(object :Callback<count>{
            override fun onResponse(call: Call<count>, response: Response<count>) {
                if(response.isSuccessful){
                    val c= response.body()
                    if(c!=null) {

                        holder.likes.text = c.count.toString()
                    }
                }
            }

            override fun onFailure(call: Call<count>, t: Throwable) {

            }

        })

        val callcom=api.getnumberofcomment(posts.id)
        callcom.enqueue(object :Callback<count>{
            override fun onResponse(call: Call<count>, response: Response<count>) {
                if(response.isSuccessful){
                    val c= response.body()
                    if(c!=null) {

                        holder.comments.text = c.count.toString()
                    }
                }
            }

            override fun onFailure(call: Call<count>, t: Throwable) {

            }

        })

        holder.likebtn.setOnClickListener {
            val c=api.like(posts.id)
            c.enqueue(object :Callback<message>{
                override fun onResponse(call: Call<message>, response: Response<message>) {
                    if(response.isSuccessful){
                        val calll=api.getnumberoflikes(posts.id)
                        calll.enqueue(object :Callback<count>{
                            override fun onResponse(call: Call<count>, response: Response<count>) {
                                if(response.isSuccessful){
                                    val c= response.body()
                                    if(c!=null) {

                                        holder.likes.text = c.count.toString()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<count>, t: Throwable) {

                            }

                        })
                    }
                }

                override fun onFailure(call: Call<message>, t: Throwable) {

                }

            })
        }
        holder.Deletebtn.setOnClickListener {
            var calldelete=api.deletepost(posts.id)
            calldelete.enqueue(object :Callback<message>{
                override fun onResponse(call: Call<message>, response: Response<message>) {
                    if(response.isSuccessful){
                        mList.remove(posts)
                        notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<message>, t: Throwable) {

                }

            })
        }
        holder.Editbtn.setOnClickListener {
            val intent= Intent(holder.itemView.context,EditPostActivity::class.java)
            pid=posts.id
            holder.itemView.context.startActivity(intent)
        }

        holder.commentimg.setOnClickListener{
            val intent=Intent(holder.itemView.context,CommentActivity::class.java)
            pid=posts.id
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView){
        val name:TextView=ItemView.findViewById(R.id.name)
        val body:TextView=ItemView.findViewById(R.id.body)
        val date:TextView=ItemView.findViewById(R.id.date)
        val likes:TextView=ItemView.findViewById(R.id.no_like)
        val likebtn:ImageView=ItemView.findViewById(R.id.likeimg)
        val Editbtn:Button=ItemView.findViewById(R.id.EditBtn)
        val Deletebtn:Button=ItemView.findViewById(R.id.DeleteBtn)
        val commentimg:ImageView=ItemView.findViewById(R.id.commentimg)
        val comments:TextView=ItemView.findViewById(R.id.no_comment)
    }

}