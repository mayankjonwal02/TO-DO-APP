package com.example.to_doapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentSigninBinding
import com.example.to_doapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth


class signin : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var navController: NavController


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSigninBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.progressBar2.visibility= View.INVISIBLE
        super.onViewCreated(view, savedInstanceState)
        auth= FirebaseAuth.getInstance()
        navController=Navigation.findNavController(view)
        binding.dosignin.setOnClickListener()
        {
            navController.navigate(R.id.action_signin_to_signup)
        }
        binding.signupnext.setOnClickListener()
        {
            var email=binding.signinemail.text.toString()
            var pass=binding.signinpass.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty())
            {
                binding.progressBar2.visibility= View.VISIBLE
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener({
                    if(it.isSuccessful)
                    {
                        Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_signin_to_home2)
                    }
                    else
                    {
                        Toast.makeText(context,"invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar2.visibility= GONE
                })
            }
            else{
                Toast.makeText(context, "Fields Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }


}