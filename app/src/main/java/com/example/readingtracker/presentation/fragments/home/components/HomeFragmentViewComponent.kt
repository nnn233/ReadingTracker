package com.example.readingtracker.presentation.fragments.home.components

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.readingtracker.presentation.fragments.home.view_controllers.AppBarViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.BooksViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.GoalsViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.NotificationViewController

class HomeFragmentViewComponent(
    fragmentComponent: HomeFragmentComponent,
    root: View,
    lifecycleOwner: LifecycleOwner,
) {
    private val goalsViewController = GoalsViewController(
        fragmentComponent.fragment,
        root,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val notificationViewController = NotificationViewController(
        fragmentComponent.fragment,
        root,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val booksViewController = BooksViewController(
        fragmentComponent.fragment,
        root,
        fragmentComponent.currentBooksAdapter,
        fragmentComponent.plannedBooksAdapter,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val appBarViewController = AppBarViewController(root)

    fun setUpViewControllers() {
        goalsViewController.setUpViews()
        notificationViewController.setUpViews()
        booksViewController.setUpViews()
        appBarViewController.setUpViews()
    }
}