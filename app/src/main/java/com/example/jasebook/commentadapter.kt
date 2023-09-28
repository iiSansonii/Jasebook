package com.example.jasebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class commentadapter(private val mList: ArrayList<comment>): RecyclerView.Adapter<commentadapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.post_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment=mList[position]
        holder.name.text=comment.commenter
        holder.date.text=comment.date
        holder.body.text=comment.body
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView){
        val name: TextView =ItemView.findViewById(R.id.name)
        val body: TextView =ItemView.findViewById(R.id.body)
        val date: TextView =ItemView.findViewById(R.id.date)
        val likes: TextView =ItemView.findViewById(R.id.no_like)
        val likebtn: ImageView =ItemView.findViewById(R.id.likeimg)
        val Editbtn: Button =ItemView.findViewById(R.id.EditBtn)
        val Deletebtn: Button =ItemView.findViewById(R.id.DeleteBtn)
        val commentimg: ImageView =ItemView.findViewById(R.id.commentimg)
        val comments: TextView =ItemView.findViewById(R.id.no_comment)
    }

}