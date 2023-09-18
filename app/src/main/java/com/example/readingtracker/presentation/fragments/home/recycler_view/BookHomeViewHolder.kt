package com.example.readingtracker.presentation.fragments.home.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.readingtracker.R
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState
import com.example.readingtracker.presentation.fragments.home.state_holders.BookStatus

class BookHomeViewHolder(
    itemView: View,
    private val activity: FragmentActivity
) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.book_home_title)
    private val author = itemView.findViewById<TextView>(R.id.book_home_author)
    private val pages = itemView.findViewById<TextView>(R.id.book_home_pages)
    private val cover = itemView.findViewById<ImageView>(R.id.book_home_cover)
    private val readButton = itemView.findViewById<ImageButton>(R.id.book_home_start_reading)

    companion object {
        fun create(
            parent: ViewGroup,
            activity: FragmentActivity
        ): BookHomeViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.book_home_item, parent, false)
            return BookHomeViewHolder(view, activity)
        }
    }

    fun onBind(item: BookHomeState) {
        title.text = item.title
        author.text = item.author
        if (item.status == BookStatus.READ)
            pages.text = activity.applicationContext.getString(
                R.string.pages_from_pages,
                item.currentPage,
                item.pages
            )

        item.cover?.let {
            cover.load(it)
        }
        readButton.setOnClickListener {
            //new screen with timer and notes
        }
    }
}