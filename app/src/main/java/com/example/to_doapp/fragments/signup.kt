package com.example.to_doapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay


class signup : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var navcontrol:NavController
    private lateinit var binding: FragmentSignupBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       binding.progressBar.visibility= INVISIBLE
        super.onViewCreated(view, savedInstanceState)
        init(view)
        binding.dosignin.setOnClickListener()
        {
            navcontrol.navigate(R.id.action_signup_to_signin)
        }
        binding.signupnext.setOnClickListener()
        {

            var email=binding.signupemail.text.toString()
            var pass1=binding.signuppass1.text.toString()
            var pass2=binding.signuppass2.text.toString()
            if(email.isNotEmpty() && pass2.isNotEmpty() && pass1.isNotEmpty())
            {
                if(pass1==pass2)
                {
                    binding.progressBar.visibility= VISIBLE
                    auth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener({
                        if(it.isSuccessful)
                        {
                            Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                            navcontrol.navigate(R.id.action_signup_to_signin)
                        }
                        else
                        {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                        binding.progressBar.visibility= GONE
                    })

                }
                else
                {
                    Toast.makeText(context, "Passwords Mismatched", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(context, "Fields Empty", Toast.LENGTH_SHORT).show()
            }

            //binding.progressBar.visibility=INVISIBLE
        }
    }
    private fun init(view:View)
    {
        navcontrol=Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }


}