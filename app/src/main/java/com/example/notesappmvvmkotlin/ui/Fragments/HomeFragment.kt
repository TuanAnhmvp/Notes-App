package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentHomeBinding
import com.example.notesappmvvmkotlin.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    val viewMode: NotesViewModel by viewModels()

    /*list to hold current*/
    var noteFilter = arrayListOf<Notes>()

    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager

        /*get all Notes*/
        viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->
            noteFilter = notesList as ArrayList<Notes> /* = java.util.ArrayList<com.example.notesappmvvmkotlin.Model.Notes> */
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        /*handle click filter*/
        binding.allNotes.setOnClickListener {
            viewMode.getNotes().observe(viewLifecycleOwner) { notesList ->
                noteFilter = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }

        binding.filterHigh.setOnClickListener {
            viewMode.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                noteFilter = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterMedium.setOnClickListener {
            viewMode.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                noteFilter = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterLow.setOnClickListener {
            viewMode.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                noteFilter = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                noteFilterint(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun noteFilterint(newText: String?) {
        /*arraylist to hold notes filter to pass adapter*/
        val newFilterList = arrayListOf<Notes>()

        for (i in noteFilter){
            if (i.title.contains(newText!!) || i.subTitle.contains(newText)){
                newFilterList.add(i)
            }
        }

        adapter.filtering(newFilterList)

    }

}