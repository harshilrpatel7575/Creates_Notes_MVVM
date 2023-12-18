package com.example.notesmvvm.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesmvvm.R
import com.example.notesmvvm.ViewModel.NotesViewModel
import com.example.notesmvvm.databinding.FragmentHomeBinding
import com.example.notesmvvm.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

   lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)



        binding.allNotes.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.filterHigh.setOnClickListener {
            viewModel.gethighNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getmediumNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.filterLow.setOnClickListener {
            viewModel.getlowNotes().observe(viewLifecycleOwner,{notesList ->

                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }


}