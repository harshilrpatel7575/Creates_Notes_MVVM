package com.example.notesmvvm.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesmvvm.Model.Notes
import com.example.notesmvvm.R
import com.example.notesmvvm.ViewModel.NotesViewModel
import com.example.notesmvvm.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    var priority: String = "1"
    lateinit var binding: FragmentEditNotesBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data!!.title)
        binding.edtSubTitle.setText(oldNotes.data!!.subTitle)
        binding.edtNotes.setText(oldNotes.data!!.notes)

        when(oldNotes.data!!.priority){
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_outline_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)

            }
            "2"->{
                priority = "2"

                binding.pRed.setImageResource(R.drawable.baseline_done_outline_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)

            }
            "3"->{
                priority = "3"

                binding.pYellow.setImageResource(R.drawable.baseline_done_outline_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title= binding.edtTitle.text.toString()
        val subtitle= binding.edtSubTitle.text.toString()
        val notes= binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            oldNotes.data!!.id,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Notes Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dailog_delete)

            val textviewYes= bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo= bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
              viewModel.deleteNotes(oldNotes.data!!.id!!)
                bottomSheet.dismiss()

                findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
            }

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}