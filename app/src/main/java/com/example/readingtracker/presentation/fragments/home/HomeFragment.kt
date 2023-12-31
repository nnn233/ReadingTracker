package com.example.readingtracker.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.readingtracker.application.components.ReadingTrackerApplication
import com.example.readingtracker.databinding.FragmentHomeBinding
import com.example.readingtracker.presentation.fragments.home.components.HomeFragmentComponent
import com.example.readingtracker.presentation.fragments.home.components.HomeFragmentViewComponent
import com.example.readingtracker.presentation.fragments.home.state_holders.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var fragmentComponent: HomeFragmentComponent
    private var fragmentViewComponent: HomeFragmentViewComponent? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val applicationComponent
        get() = ReadingTrackerApplication.get(requireContext()).applicationComponent
    private val viewModel: HomeViewModel by viewModels { applicationComponent.viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = HomeFragmentComponent(
            fragment = this.requireActivity(),
            viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        fragmentViewComponent = HomeFragmentViewComponent(
            fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            setUpViewControllers()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewComponent = null
        _binding = null
    }
}