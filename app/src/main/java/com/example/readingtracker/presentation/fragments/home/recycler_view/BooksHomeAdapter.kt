package com.example.readingtracker.presentation.fragments.home.recycler_view

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.readingtracker.presentation.fragments.home.state_holders.BookHomeState

class BooksHomeAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var onLongClickListener: OnLongClickListener? = null
    var items = mutableListOf<BookHomeState>()
        set(value) {
            val callback = CommonCallback(
                oldItems = field,
                newItems = value,
                { oldItem: BookHomeState, newItem -> oldItem.id == newItem.id })
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookHomeViewHolder.create(parent, activity)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BookHomeViewHolder
        holder.onBind(items[position])

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onClick(position, items[position].id)
            }
        }

        holder.itemView.setOnLongClickListener {
            if (onLongClickListener != null) {
                onLongClickListener?.onLongClick(position, items[position].id)
            }
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = items.size

    fun setOnClickListeners(
        onClickListener: OnClickListener,
        onLongClickListener: OnLongClickListener
    ) {
        this.onClickListener = onClickListener
        this.onLongClickListener = onLongClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, id: Int)
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int, id: Int)
    }
}