package com.example.readingtracker.presentation.fragments.home.view_controllers

import android.view.View
import androidx.core.widget.NestedScrollView
import com.example.readingtracker.R
import com.google.android.material.appbar.AppBarLayout

class AppBarViewController(
    root: View
) {
    private val nestedScrollView: NestedScrollView = root.findViewById(R.id.nested_scroll_view)
    private val topBar: AppBarLayout = root.findViewById(R.id.top_navigation_bar)

    fun setUpViews() {
        nestedScrollView.setOnScrollChangeListener { v, _, _, _, _ ->
            if (v.canScrollVertically(SCROLL_DIRECTION_UP))
                topBar.elevation = 10f
            else topBar.elevation = 0f
        }
    }

    companion object {
        private const val SCROLL_DIRECTION_UP = -1
    }
}