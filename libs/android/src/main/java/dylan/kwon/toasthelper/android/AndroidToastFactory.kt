package dylan.kwon.toasthelper.android

import android.content.Context
import dylan.kwon.toasthelper.core.Toast
import dylan.kwon.toasthelper.core.ToastDuration
import dylan.kwon.toasthelper.core.ToastFactory
import dylan.kwon.toasthelper.core.ToastMargin
import dylan.kwon.toasthelper.core.ToastPosition

/**
 * This is the factory for [AndroidToast]
 */
class AndroidToastFactory(

    /**
     * Android Application Context.
     * Used for executing the actual toast.
     */
    private val applicationContext: Context

) : ToastFactory {

    /**
     * Creates a [AndroidToast] using a string [resId].
     * [resId] is string resource id of android.
     *
     * [position] and [margin] are not supported on [android.os.Build.VERSION_CODES.R] or higher.
     */
    override fun create(
        resId: Int,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast = create(
        message = applicationContext.getString(resId),
        duration = duration,
        margin = margin,
        position = position,
    )

    /**
     *  Creates a [AndroidToast] using a [message].
     *
     * [position] and [margin] are not supported on [android.os.Build.VERSION_CODES.R] or higher.
     */
    override fun create(
        message: String,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast = AndroidToast(
        applicationContext = applicationContext,
        message = message,
        duration = duration,
        margin = margin,
        position = position
    )

}
