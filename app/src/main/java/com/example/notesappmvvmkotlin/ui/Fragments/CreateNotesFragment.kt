package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesFragment : Fragment() {
    var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    //View binding
    private lateinit var binding: FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        /*default do*/
        binding.pGreen.setImageResource(R.drawable.ic_done_black)


        /*handle click red, yellow, green dot..*/
        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_done_black)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener{
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_done_black)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener{
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_done_black)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }


        /*handle click save notes*/
        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)


        }


        return binding.root
        }


    private fun createNotes(view: View) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        //date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date()).toString()

        val data = Notes(
            null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = currentDate,
            priority

        )

        if(title.isEmpty() || title == "null"){
            Toast.makeText(requireContext(), "Please Enter Title...", Toast.LENGTH_SHORT).show()
        }
        else{
            viewModel.addNotes(data)
            Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()
        }




    }

}