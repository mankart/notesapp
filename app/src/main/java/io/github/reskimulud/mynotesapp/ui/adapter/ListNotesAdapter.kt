package io.github.reskimulud.mynotesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.reskimulud.mynotesapp.databinding.NoteLayoutItemBinding
import io.github.reskimulud.mynotesapp.model.Note

class ListNotesAdapter(
    private val onClickCallback: (noteId: String) -> Unit,
): RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {
    private val _notes: MutableList<Note> = mutableListOf()
    private val notes: List<Note> = _notes

    fun setData(data: List<Note>) {
        _notes.clear()
        _notes.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: NoteLayoutItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val tvTitle = itemView.tvTitle
        private val tvBody = itemView.tvBody
        private val tvUpdatedDate = itemView.tvUpdatedDate

        fun bind(data: Note) {
            data.apply {
                tvTitle.text = title
                tvBody.text = body
                tvUpdatedDate.text = updatedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NoteLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = notes[position]

        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClickCallback(data.id)
        }
    }
}