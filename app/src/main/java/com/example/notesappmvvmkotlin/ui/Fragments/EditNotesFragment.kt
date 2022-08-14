package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentEditNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {
    var priority: String = "1"
    //viewmodel
    private val viewModel: NotesViewModel by viewModels()
    //
    private val args: EditNotesFragmentArgs by navArgs()

    //View binding
    private lateinit var binding: FragmentEditNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.edtTitle.setText(args.data.title)
        binding.edtSubtitle.setText(args.data.subTitle)
        binding.edtNotes.setText(args.data.notes)


        when(args.data.priority) {
            "1" ->{
                binding.pGreen.setImageResource(R.drawable.ic_done_black)
            }
            "2" ->{
                binding.pYellow.setImageResource(R.drawable.ic_done_black)

            }
            "3" ->{
                binding.pRed.setImageResource(R.drawable.ic_done_black)

            }
        }

        binding.btnEditNotes.setOnClickListener {
            updateNotes(it)
        }

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



        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        //date
        val sdf = SimpleDateFormat("dd/M/yyyy - hh:mm:ss")
        val currentDate = sdf.format(Date()).toString()

        val data = Notes(
            args.data.id,
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
            viewModel.updateNotes(data)
            Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
        }

    }

}