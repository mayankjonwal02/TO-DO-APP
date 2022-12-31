package com.example.to_doapp.fragments

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.to_doapp.R
import com.example.to_doapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth

class splash : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding:FragmentSplashBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var animation=AnimationUtils.loadAnimation(this.context, androidx.appcompat.R.anim.abc_slide_in_top).apply {
            duration=5000
        }
        binding.textView3.startAnimation(animation)

        auth=FirebaseAuth.getInstance()
        navController= Navigation.findNavController(view)
        Handler().postDelayed( Runnable {
            if(auth.currentUser!=null)
            {
                navController.navigate(R.id.action_splash_to_home2)
            }
            else
            {
                navController.navigate(R.id.action_splash_to_signin)
            }
        },4000)
    }
}