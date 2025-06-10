package com.furmannsoft.maximatech.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : NavIcon()
    data class DrawableResIcon(@DrawableRes val drawableRes: Int) : NavIcon()
}

data class NavigationItems(
    var label: String,
    var icon: Any
)
