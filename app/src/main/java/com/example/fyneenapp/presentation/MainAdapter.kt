package com.example.fyneenapp.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.fyneenapp.R
import com.example.fyneenapp.databinding.LayoutDrawerItemBinding
import com.example.fyneenapp.databinding.LayoutLogoItemBinding
import com.example.fyneenapp.databinding.LayoutModeItemBinding
import com.example.fyneenapp.databinding.LayoutUserChooserItemBinding


class MainAdapter :
    ListAdapter<DrawerListItem, RecyclerView.ViewHolder>(MainDiffUtil()) {
    private var callBack: CallBack? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            DrawerListItem.ViewType.LOGO.ordinal -> {
                LogoViewHolder(
                    LayoutLogoItemBinding.inflate(layoutInflater, parent, false)
                )
            }
            DrawerListItem.ViewType.USER_CHOOSER.ordinal -> {
                UserChooserViewHolder(
                    LayoutUserChooserItemBinding.inflate(layoutInflater, parent, false)
                )
            }
            DrawerListItem.ViewType.DRAWER_ITEM.ordinal -> {
                DrawerItemViewHolder(
                    LayoutDrawerItemBinding.inflate(layoutInflater, parent, false)
                )
            }
            DrawerListItem.ViewType.MODE_SWITCH.ordinal -> {
                ModeSwitchViewHolder(
                    LayoutModeItemBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> throw IllegalStateException()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is LogoViewHolder -> {}
            is UserChooserViewHolder -> {}
            is DrawerItemViewHolder -> {
                holder.bind(item as DrawerListItem.DrawerItem)
            }
            is ModeSwitchViewHolder -> {}
        }
    }

    inner class LogoViewHolder(
        private val binding: LayoutLogoItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class UserChooserViewHolder(
        private val binding: LayoutUserChooserItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class DrawerItemViewHolder(
        private val binding: LayoutDrawerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(item: DrawerListItem.DrawerItem) {

            if (item.isActive) binding.root.setCardBackgroundColor(R.color.grey)
            else binding.root.setCardBackgroundColor(Color.WHITE)

            binding.typeImage.setImageResource(item.type.icon)
            binding.titleText.text = item.type.title

            if (item.notification != null) {
                binding.notificationText.visibility = View.VISIBLE

                binding.notificationText.text = item.notification.toString()

                if (item.isPositiveNotification!!) {
                    binding.notificationText.setBackgroundResource(R.drawable.shape_item_positive)
                } else {
                    binding.notificationText.setBackgroundResource(R.drawable.shape_item_negative)
                }

            } else {
                binding.notificationText.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                callBack?.onItemClick(item.type)
            }
        }

    }

    inner class ModeSwitchViewHolder(
        private val binding: LayoutModeItemBinding
    ) : RecyclerView.ViewHolder(binding.root)


    interface CallBack {
        fun onItemClick(item: ItemTypes)
    }
}