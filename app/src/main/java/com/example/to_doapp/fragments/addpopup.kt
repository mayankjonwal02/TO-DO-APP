package com.example.to_doapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentAddpopupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class addpopup : DialogFragment() {

   private lateinit var binding: FragmentAddpopupBinding
   private lateinit var auth: FirebaseAuth
   private lateinit var databaseref:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddpopupBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth= FirebaseAuth.getInstance()
        databaseref=FirebaseDatabase.getInstance().reference.child("user").child(auth.currentUser?.uid.toString())
        super.onViewCreated(view, savedInstanceState)
        binding.cross.setOnClickListener(){
            dismiss()
        }
        binding.addtask.setOnClickListener()
        {
            var task=binding.task.text.toString()
            if(task.isNotEmpty())
            {
                databaseref.push().setValue(task).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(context, "Task added successfully", Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                    else
                    {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()
            }
        }

    }



}