package ru.leonov.mytasks.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import ru.leonov.mytasks.R

class GalleryFragment : Fragment() {

    private var galleryViewModel: GalleryViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView = root.findViewById<TextView>(R.id.text_gallery)
        galleryViewModel!!.text.observe(this, Observer { s -> textView.text = s })
        return root
    }
}