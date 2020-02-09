package ru.leonov.mytasks.ui.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_current_note.*
import kotlinx.android.synthetic.main.fragment_notes_list.*
import ru.leonov.mytasks.R
import ru.leonov.mytasks.model.entities.Note
import ru.leonov.mytasks.model.utils.formatedString
import ru.leonov.mytasks.ui.base.BaseFragment
import java.util.*

class CurrentNoteFragment : BaseFragment<Note?, CurrentNoteViewState>() {

    private val NOTE_ID = "NOTE"

    private var note: Note? = null

    override val layoutRes = R.layout.fragment_current_note
    override val viewModel: CurrentNoteViewModel by lazy { ViewModelProvider(this).get(CurrentNoteViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadNote()
        initView()
        initNavigation()
        initFab()
    }

    private fun loadNote() {
        val noteId = arguments?.getString(NOTE_ID)
        noteId?.let {
            viewModel.loadNote(it)
        }
    }

    private fun initView() {
        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = saveNote()
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    fun saveNote() {
        if (et_title.text == null && et_body.text == null) return

            note = note?.copy(
                    title = et_title.text.toString(),
                    text = et_body.text.toString(),
                    date = Date()
            ) ?: createNewNote()

            note?.let { viewModel.save(it) }
    }

    private fun createNewNote(): Note {
        return Note(UUID.randomUUID().toString(),
                et_title.text.toString(),
                et_body.text.toString())
    }

    private fun initFab() {
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.let {
            it.setImageResource(R.drawable.ic_save_black_24dp)
            it.setOnClickListener {
                saveNote()
                viewModel.gotoNotesList()
            }
        }
    }

    private fun initNavigation() {
        viewModel.gotoNotesListEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                activity?.onBackPressed();
            }
        })
    }

    override fun renderData(data: Note?) {
        this.note = data

        note?.let { note ->
            et_title.setText(note.title)
            et_body.setText(note.text)
            tv_status.text = note.date.formatedString()

        }
    }

    override fun showError(error: String) {
        tv_status.text = error
    }
}