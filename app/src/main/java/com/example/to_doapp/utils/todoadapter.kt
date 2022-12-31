package com.example.to_doapp.utils

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.to_doapp.R
import com.example.to_doapp.fragments.home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.NonDisposableHandle.parent
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context

import java.nio.file.attribute.PosixFileAttributeView


class todoadapter(var list: MutableList<String>,var list1: MutableList<String>):RecyclerView.Adapter<viewHolder>()
{
    private lateinit var ref:DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        auth=FirebaseAuth.getInstance()
        ref=FirebaseDatabase.getInstance().getReference("user").child(auth.currentUser?.uid.toString())
        var itemview=LayoutInflater.from(parent.context).inflate(R.layout.eachtodoitem,parent,false)
        return viewHolder(itemview)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.data.text=list[position]
        holder.delete.setOnClickListener()
        {
            ref.child(list1[position]).removeValue()
            //Toast.makeText(context,"deleted",Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
class viewHolder(view: View):RecyclerView.ViewHolder(view)
{
    var data=view.findViewById<TextView>(R.id.data)
    var delete=view.findViewById<ImageView>(R.id.delete)

}
