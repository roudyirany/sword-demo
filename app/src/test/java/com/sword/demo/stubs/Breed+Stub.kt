package com.sword.demo.stubs

import com.sword.demo.network.models.Breed
import com.sword.demo.network.models.Image

fun Breed.Companion.stub(
    id: Int = 0,
    category: String = "category",
    group: String = "group",
    image: Image? = null,
    name: String = "name",
    origin: String = "origin",
    temperament: String = "temperament"
) = Breed(
    id = id,
    category = category,
    group = group,
    image = image,
    name = name,
    origin = origin,
    temperament = temperament
)