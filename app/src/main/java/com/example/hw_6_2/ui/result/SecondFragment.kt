package com.example.hw_6_2.ui.result

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hw_6_2.R
import com.example.hw_6_2.base.BaseFragment
import com.example.hw_6_2.databinding.FragmentSecondBinding
import com.example.hw_6_2.extentions.submitData
import com.example.hw_6_2.ui.Photo.PhotoAdapter
import com.example.hw_6_2.ui.Photo.PhotoViewModel
import kotlinx.coroutines.launch

class SecondFragment :
    BaseFragment<FragmentSecondBinding>(R.layout.fragment_second) {
    override val binding by viewBinding(FragmentSecondBinding::bind)
    private val viewModel: PhotoViewModel by activityViewModels()
    private val photoAdapter = PhotoAdapter()

    override fun initViews() {
        setupAdapter()
    }

    override fun initListeners() {
        binding.btnArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupAdapter() {
        binding.recyclerview.overScrollMode = View.OVER_SCROLL_NEVER
        binding.recyclerview.adapter = photoAdapter
        binding.recyclerview.layoutManager = GridLayoutManager(context, 3)
    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listState.observe(viewLifecycleOwner) { list ->
                println(list.toString())
                photoAdapter.submitData(list)
            }
        }
    }
}