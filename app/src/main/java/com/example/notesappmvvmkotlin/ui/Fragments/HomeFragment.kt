package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentHomeBinding
import com.example.notesappmvvmkotlin.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {
    val viewMode: NotesViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        /*handle click filter*/
        binding.allNotes.setOnClickListener {
            viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        binding.filterHigh.setOnClickListener {
            viewMode.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
        binding.filterMedium.setOnClickListener {
            viewMode.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
        binding.filterLow.setOnClickListener {
            viewMode.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        return binding.root
    }

}