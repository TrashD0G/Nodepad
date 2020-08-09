package com.artem.notepad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*


class TestFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }


    override fun onStart() {
        super.onStart()
        Ftest_Btn_Back.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
        }




    }
}