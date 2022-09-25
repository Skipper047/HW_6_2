package com.example.hw_6_2.ui.Photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_6_2.base.BaseUtil
import com.example.hw_6_2.databinding.ItemPhotoBinding
import com.example.hw_6_2.extentions.loadWithGlide
import com.example.hw_6_2.model.Image

class PhotoAdapter : ListAdapter<Image, PhotoAdapter.PhotoViewHolder>(BaseUtil()) {

    val secondList = arrayListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            image.image?.let { it -> binding.image.loadWithGlide(it) }
            binding.root.setOnClickListener {
                if (binding.image.isInvisible) {
                    binding.image.isVisible = true
                    isSelected(image.image)
                    println(secondList)
                } else {
                    binding.image.isInvisible = true
                    selected(image.image)
                }
            }
        }

        private fun isSelected(imageView: String?) {
            secondList.remove(Image(image = imageView))
        }

        private fun selected(imageView: String?) {
            secondList.add(Image(image = imageView))
        }
    }
}