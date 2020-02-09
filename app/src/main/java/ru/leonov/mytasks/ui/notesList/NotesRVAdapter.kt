package ru.leonov.mytasks.ui.notesList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.leonov.mytasks.R
import ru.leonov.mytasks.model.entities.Color
import ru.leonov.mytasks.model.entities.Note
import ru.leonov.mytasks.model.utils.formatedString

class NotesRVAdapter(val onItemViewClick : ((note: Note) -> Unit)? = null)
    : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(vh: ViewHolder, pos: Int) = vh.bind(notes[pos])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("DefaultLocale")
        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title.toUpperCase()
            tv_text.text = note.text
            tv_date.text = note.date.formatedString()

            val color = when(note.color){
                Color.WHITE -> R.color.white
                Color.YELLOW -> R.color.yellow
                Color.GREEN -> R.color.green
                Color.BLUE -> R.color.blue
                Color.RED -> R.color.red
                Color.VIOLET -> R.color.violet
                Color.PINK -> R.color.pink
            }

            (this as CardView).setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))
            itemView.setOnClickListener {
                onItemViewClick?.invoke(note)
            }

        }
    }
}