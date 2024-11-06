package dylan.kwon.toasthelper.android.mapper

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import dylan.kwon.toasthelper.core.callback.OnToastHiddenCallback
import dylan.kwon.toasthelper.core.callback.OnToastShownCallback

/**
 * [OnToastShownCallback] List to [Toast.Callback] List.
 */
@RequiresApi(Build.VERSION_CODES.R)
internal fun List<OnToastShownCallback>.mapActualShownCallbacks() = map {
    it.toActualShownCallback()
}

/**
 * [OnToastHiddenCallback] List to [Toast.Callback] List.
 */
@RequiresApi(Build.VERSION_CODES.R)
internal fun List<OnToastHiddenCallback>.mapActualHiddenCallbacks() = map {
    it.toActualHiddenCallback()
}

/**
 * [OnToastShownCallback] to [Toast.Callback]
 */
@RequiresApi(Build.VERSION_CODES.R)
internal fun OnToastShownCallback.toActualShownCallback() = object : Toast.Callback() {
    override fun onToastShown() {
        this@toActualShownCallback()
    }
}

/**
 * [OnToastHiddenCallback] to [Toast.Callback]
 */
@RequiresApi(Build.VERSION_CODES.R)
internal fun OnToastHiddenCallback.toActualHiddenCallback() = object : Toast.Callback() {
    override fun onToastHidden() {
        this@toActualHiddenCallback()
    }
}

/**
 * Add All [Toast.Callback] List to [Toast]
 */
@RequiresApi(Build.VERSION_CODES.R)
internal fun Toast.addCallbacks(callbacks: List<Toast.Callback>) {
    callbacks.forEach {
        addCallback(it)
    }
}