package com.example.notesmvvm.ui.Fragments


import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesmvvm.Model.Notes
import com.example.notesmvvm.R
import com.example.notesmvvm.ViewModel.NotesViewModel
import com.example.notesmvvm.databinding.FragmentCreateNoteBinding
import java.util.Date


class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.baseline_done_outline_24)

        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_outline_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)

        }
        binding.pRed.setOnClickListener{
            priority = "2"

            binding.pRed.setImageResource(R.drawable.baseline_done_outline_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)

        }
        binding.pYellow.setOnClickListener{
            priority = "3"

            binding.pYellow.setImageResource(R.drawable.baseline_done_outline_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }


        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }
        return binding.root


    }

    private fun createNotes(it: View?) {
      val title= binding.edtTitle.text.toString()
      val subtitle= binding.edtSubTitle.text.toString()
      val notes= binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

    val data = Notes(
        null,
        title = title,
        subTitle = subtitle,
        notes = notes,
        date = notesDate.toString(),
        priority
    )

        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes created Succesfully",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}