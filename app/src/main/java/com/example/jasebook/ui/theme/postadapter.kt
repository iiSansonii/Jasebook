package com.example.jasebook.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jasebook.R
import com.example.jasebook.post

class postadapter(private val mList: List<post>): RecyclerView.Adapter<postadapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postadapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.post_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: postadapter.ViewHolder, position: Int) {
        val posts=mList[position]
        holder.name.text=posts.name
        holder.body.text=posts.body
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView){
        val name:TextView=ItemView.findViewById(R.id.name)
        val body:TextView=ItemView.findViewById(R.id.body)
    }

}
