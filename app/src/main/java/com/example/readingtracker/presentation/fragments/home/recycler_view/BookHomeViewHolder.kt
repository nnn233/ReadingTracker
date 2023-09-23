package com.example.readingtracker.presentation.fragments.home.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.readingtracker.R
import com.example.readingtracker.databinding.BookHomeItemBinding
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus

class BookHomeViewHolder(
    private val binding: BookHomeItemBinding,
    view: View,
    private val activity: FragmentActivity
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(
            parent: ViewGroup,
            activity: FragmentActivity
        ): BookHomeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = BookHomeItemBinding.inflate(inflater, parent, false)
            return BookHomeViewHolder(binding, binding.root, activity)
        }
    }

    fun onBind(item: BookHomeState) {
        binding.bookHomeTitle.text = item.title
        binding.bookHomeAuthor.text = item.author
        if (item.status == BookStatus.READ)
            binding.bookHomePages.text = activity.applicationContext.getString(
                R.string.pages_from_pages,
                item.currentPage,
                item.pages
            )

        item.cover?.let {
            binding.bookHomeCover.load(it)
        }
        binding.bookHomeStartReading.setOnClickListener {
            //new screen with timer and notes
        }
    }
}