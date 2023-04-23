package com.suresh.appkeyboard

import android.content.Context
import android.content.res.Resources

object KeyboardUtils {
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun pxToDp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }
}