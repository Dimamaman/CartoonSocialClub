package com.raywenderlich.android.cartoonsocialclub

import android.content.Context
import androidx.annotation.DrawableRes

enum class CartoonAvatar(@DrawableRes val drawableRes: Int) {
    DOG(R.mipmap.avatar_dog) {
        override fun selectionDescription(context: Context): String {
            val name = context.getString(R.string.avatar_description_dog)
            return context.getString(R.string.your_selected_avatar_description, name)
        }
    },
    MONSTER(R.mipmap.avatar_monster) {
        override fun selectionDescription(context: Context): String {
            val name = context.getString(R.string.avatar_description_monster)
            return context.getString(R.string.your_selected_avatar_description, name)
        }
    },
    OCTOPUS(R.mipmap.avatar_octopus) {
        override fun selectionDescription(context: Context): String {
            val name = context.getString(R.string.avatar_description_octopus)
            return context.getString(R.string.your_selected_avatar_description, name)
        }
    },
    NONE(R.mipmap.avatar_none) {
        override fun selectionDescription(context: Context): String {
            return "" // No avatar selected, show no text
        }
    };

    abstract fun selectionDescription(context: Context): String

}

