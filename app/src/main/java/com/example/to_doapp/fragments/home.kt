package com.example.to_doapp.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentHomeBinding
import com.example.to_doapp.databinding.FragmentSigninBinding
import com.example.to_doapp.utils.todoadapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var databaseref: DatabaseReference
    private lateinit var dialogbox:addpopup
    private lateinit var myrecycler:RecyclerView
    private lateinit var list:MutableList<String>
    private lateinit var list1:MutableList<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        navController=Navigation.findNavController(view)

        super.onViewCreated(view, savedInstanceState)
        list= mutableListOf()
        list1= mutableListOf()
        auth= FirebaseAuth.getInstance()
        databaseref= FirebaseDatabase.getInstance().reference.child("user").child(auth.currentUser?.uid.toString())
        myrecycler=binding.todolist
        myrecycler.layoutManager=LinearLayoutManager(context)
        databaseref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                list1.clear()
                if(snapshot.exists())
                {
                    for(shot in snapshot.children)
                    {
                        var finaldata= shot.getValue().toString()
                        list.add(finaldata!!)
                        var finaldata1= shot.getKey().toString()
                        list1.add(finaldata1!!)

                    }

                }
                myrecycler.adapter=todoadapter(list,list1)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"not available",Toast.LENGTH_LONG).show()
            }

        })



        binding.signout.setOnClickListener()
        {
            auth.signOut()
            navController.navigate(R.id.action_home2_to_signin)

        }
        binding.add.setOnClickListener()
        {
            dialogbox=addpopup()
            dialogbox.show(this.childFragmentManager,"Add Task")


                    }

    }


}