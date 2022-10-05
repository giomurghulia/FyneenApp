package com.example.fyneenapp.presentation

import com.example.fyneenapp.R

sealed class DrawerListItem(val viewType: ViewType) {
    enum class ViewType {
        LOGO,
        USER_CHOOSER,
        DRAWER_ITEM,
        MODE_SWITCH
    }

    object Logo : DrawerListItem(ViewType.LOGO)

    object UserChooser : DrawerListItem(ViewType.USER_CHOOSER)

    data class DrawerItem(
        val type: ItemTypes,
        val isActive: Boolean,
        val notification: Int?,
        val isPositiveNotification: Boolean?
    ) : DrawerListItem(ViewType.DRAWER_ITEM)

    object ModeSwitch : DrawerListItem(ViewType.MODE_SWITCH)

}

enum class ItemTypes(val icon: Int, val title: String) {
    DASHBOARD(R.drawable.ic_dashboard, "Dashboard"),
    POST(R.drawable.ic_post, "Post"),
    NOTIFICATIONS(R.drawable.ic_notifications, "Notifications"),
    CALENDAR(R.drawable.ic_calendar, "Calendar"),
    STATISTICS(R.drawable.ic_statistics, "Statistics"),
    SETTINGS(R.drawable.ic_settings, "Settings")
}