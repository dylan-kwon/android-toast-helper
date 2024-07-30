package dylan.kwon.toasthelper.android.mapper

import android.widget.Toast
import dylan.kwon.toasthelper.core.callback.OnToastHiddenCallback
import dylan.kwon.toasthelper.core.callback.OnToastShownCallback

/**
 * [OnToastShownCallback] List to [Toast.Callback] List.
 */
internal fun List<OnToastShownCallback>.mapActualShownCallbacks() = map {
    it.toActualShownCallback()
}

/**
 * [OnToastHiddenCallback] List to [Toast.Callback] List.
 */
internal fun List<OnToastHiddenCallback>.mapActualHiddenCallbacks() = map {
    it.toActualHiddenCallback()
}

/**
 * [OnToastShownCallback] to [Toast.Callback]
 */
internal fun OnToastShownCallback.toActualShownCallback() = object : Toast.Callback() {
    override fun onToastShown() {
        this@toActualShownCallback()
    }
}

/**
 * [OnToastHiddenCallback] to [Toast.Callback]
 */
internal fun OnToastHiddenCallback.toActualHiddenCallback() = object : Toast.Callback() {
    override fun onToastHidden() {
        this@toActualHiddenCallback()
    }
}

/**
 * Add All [Toast.Callback] List to [Toast]
 */
internal fun Toast.addCallbacks(callbacks: List<Toast.Callback>) {
    callbacks.forEach {
        addCallback(it)
    }
}