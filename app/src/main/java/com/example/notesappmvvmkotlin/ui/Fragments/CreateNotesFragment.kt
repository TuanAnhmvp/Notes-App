package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.databinding.FragmentCreateNotesBinding

class CreateNotesFragment : Fragment() {

    //View binding
    private lateinit var binding: FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)


        return binding.root
        }

}