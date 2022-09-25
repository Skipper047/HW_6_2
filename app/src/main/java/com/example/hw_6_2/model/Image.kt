package com.example.hw_6_2.model

import com.example.hw_6_2.base.BaseViewModel


data class Image (
    val image: String? = null,
    override val id: Int? = null
) : BaseViewModel