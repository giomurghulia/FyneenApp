package com.example.fyneenapp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(createFirstList(ItemTypes.DASHBOARD))
    val state get() = _state.asStateFlow()

    fun onItemClick(item: ItemTypes) {
        submitNewList(item)
    }

    private fun submitNewList(itemClick: ItemTypes) {
        _state.tryEmit(createFirstList(itemClick).toList())
    }

    private fun createFirstList(activeItem: ItemTypes): List<DrawerListItem> {
        val list = mutableListOf<DrawerListItem>()

        list.add(DrawerListItem.Logo)
        list.add(DrawerListItem.UserChooser)
        list.add(DrawerListItem.DrawerItem(ItemTypes.DASHBOARD, false, null, null))
        list.add(DrawerListItem.DrawerItem(ItemTypes.POST, false, 15, false))
        list.add(DrawerListItem.DrawerItem(ItemTypes.NOTIFICATIONS, false, 20, true))
        list.add(DrawerListItem.DrawerItem(ItemTypes.CALENDAR, false, null, null))
        list.add(DrawerListItem.DrawerItem(ItemTypes.STATISTICS, false, null, null))
        list.add(DrawerListItem.DrawerItem(ItemTypes.SETTINGS, false, null, null))
        list.add(DrawerListItem.ModeSwitch)

        list.forEachIndexed { index: Int, it ->
            if (it is DrawerListItem.DrawerItem) {
                if (it.type == activeItem) {
                    list[index] = it.copy(isActive = true)
                }
            }
        }

        return list.toList()
    }
}