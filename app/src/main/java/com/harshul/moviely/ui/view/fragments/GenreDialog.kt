package com.harshul.moviely.ui.view.fragments

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.view.Display
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.harshul.moviely.databinding.FragmentGenreDialogBinding
import com.harshul.moviely.ui.adapter.GenreAdapter
import com.harshul.moviely.ui.adapter.OnGenreClickListener

class GenreDialog : DialogFragment(), OnGenreClickListener {

    lateinit var dialogBinding: FragmentGenreDialogBinding
    lateinit var dialogGenre: Dialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dialogGenre = Dialog(requireContext())
        dialogGenre.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialogBinding = FragmentGenreDialogBinding.inflate(layoutInflater)
        dialogGenre.setContentView(dialogBinding.root)

        val list = listOf(
            "\uD83D\uDE31 Horror", "\uD83D\uDE0E Action", "\uD83D\uDE06 Comedy",
            "\uD83D\uDE2D Drama", "\uD83E\uDD29 Fantasy", "\uD83E\uDD70 Romance"
        )

        val genreAdapter = GenreAdapter(list = list, this)
        dialogBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = genreAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        val display: Display = requireActivity().windowManager.defaultDisplay
        val width: Int = display.width - 64
        val window: Window? = dialogGenre.window
        window?.setLayout(width, ActionBar.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        return dialogGenre
    }

    override fun onGenreClick(genre: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToGenreFragment(genre)
        findNavController().navigate(action)
        dialogGenre.dismiss()
    }

}