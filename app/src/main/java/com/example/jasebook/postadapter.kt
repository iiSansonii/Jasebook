package com.example.jasebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView){
        val name:TextView=ItemView.findViewById(R.id.name)
        val body:TextView=ItemView.findViewById(R.id.body)
        val date:TextView=ItemView.findViewById(R.id.date)
    }

}