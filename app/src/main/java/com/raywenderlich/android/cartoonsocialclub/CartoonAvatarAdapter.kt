package com.raywenderlich.android.cartoonsocialclub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.cartoonsocialclub.databinding.ListItemAvatarBinding

// TODO 1 : replace this dataclass with one using an Enum.
data class Item(
    // 1
    val avatar: CartoonAvatar,
    var isSelected: Boolean
) {
    // 2
    @DrawableRes
    var imageResource = avatar.drawableRes
}

class ViewHolder private constructor(val binding: ListItemAvatarBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.avatarThumbnailImageView.setImageResource(item.imageResource)
        val backgroundColor = if (item.isSelected) R.color.colorSelectionBackground else android.R
            .color.transparent
        binding.root.setBackgroundResource(backgroundColor)
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemAvatarBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

/**
 * Adapter for populating the list of cartoon avatar choices in the recycler view
 * the onItemClicked is a lambda method passed for convenience in order to handle this externally
 */
class CartoonAvatarAdapter(
    var onItemClick: ((Item) -> Unit) = {}
) : ListAdapter<Item, ViewHolder>
    (CartoonAvatarItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.root.setOnClickListener { onItemClick.invoke(item) }
        holder.bind(item)
    }
}

private class CartoonAvatarItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem.imageResource == newItem.imageResource

    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        oldItem == newItem
}
