package com.example.readingtracker.presentation.fragments.home.components

import androidx.lifecycle.LifecycleOwner
import com.example.readingtracker.databinding.FragmentHomeBinding
import com.example.readingtracker.presentation.fragments.home.view_controllers.AppBarViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.BooksViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.GoalsViewController
import com.example.readingtracker.presentation.fragments.home.view_controllers.NotificationViewController

class HomeFragmentViewComponent(
    fragmentComponent: HomeFragmentComponent,
    binding: FragmentHomeBinding,
    lifecycleOwner: LifecycleOwner,
) {
    private val goalsViewController = GoalsViewController(
        fragmentComponent.fragment,
        binding,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val notificationViewController = NotificationViewController(
        fragmentComponent.fragment,
        binding,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val booksViewController = BooksViewController(
        fragmentComponent.fragment,
        binding,
        fragmentComponent.currentBooksAdapter,
        fragmentComponent.plannedBooksAdapter,
        lifecycleOwner,
        fragmentComponent.viewModel,
    )

    private val appBarViewController = AppBarViewController(binding)

    fun setUpViewControllers() {
        goalsViewController.setUpViews()
        notificationViewController.setUpViews()
        booksViewController.setUpViews()
        appBarViewController.setUpViews()
    }
}