package com.example.notesmvvm.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.Model.Notes
import com.example.notesmvvm.R
import com.example.notesmvvm.databinding.ItemsNotesBinding
import com.example.notesmvvm.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val context: Context, val notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    class notesViewHolder(val binding: ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemsNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data =notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1"->{
                holder.binding.ViewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.ViewPriority.setBackgroundResource(R.drawable.red_dot)
            }
            "3"->{
                holder.binding.ViewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
        }

        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }
}