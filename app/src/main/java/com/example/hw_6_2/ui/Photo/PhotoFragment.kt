package com.example.hw_6_2.ui.Photo

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hw_6_2.R
import com.example.hw_6_2.base.BaseFragment
import com.example.hw_6_2.databinding.FragmentPhotoBinding
import com.example.hw_6_2.extentions.navigateSafely
import com.example.hw_6_2.extentions.submitData
import com.example.hw_6_2.model.Image

class PhotoFragment : BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {

    override val binding by viewBinding(FragmentPhotoBinding::bind)
    private val photoAdapter = PhotoAdapter()
    private val viewModel: PhotoViewModel by activityViewModels()
    private val list = arrayListOf<Image>()

    private fun addImage(): List<Image> {
        list.apply {
            add(Image(image = "https://oir.mobi/uploads/posts/2021-05/1621021754_3-oir_mobi-p-kudryavii-barashek-zhivotnie-krasivo-foto-3.jpg"))
            add(Image(image = "https://static.mk.ru/upload/entities/2022/07/05/07/articles/detailPicture/17/b5/36/50/2ea24bfc9b049f59f491a6b4579c9441.jpg"))
            add(Image(image = "https://proza.ru/pics/2018/02/19/1011.jpg"))
        }
        return list
    }

    override fun initViews() {
        setupAdapter()
        photoAdapter.submitData(addImage())

    }

    private fun setupAdapter() {
        binding.recycler.overScrollMode = View.OVER_SCROLL_NEVER
        binding.recycler.adapter = photoAdapter
        binding.recycler.layoutManager = GridLayoutManager(context, 3)
    }

    override fun initListeners() {
        binding.fabNext.setOnClickListener {
            println(photoAdapter.secondList)
            viewModel.putPicture(photoAdapter.secondList)
            findNavController().navigateSafely(R.id.action_photoFragment_to_secondFragment)
        }
    }
}