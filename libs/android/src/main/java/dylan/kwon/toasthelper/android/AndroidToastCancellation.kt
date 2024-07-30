package dylan.kwon.toasthelper.android

import android.widget.Toast
import dylan.kwon.toasthelper.core.ToastCancellation

class AndroidToastCancellation(
    private val toast: Toast
) : ToastCancellation {

    /**
     * Cancels the toast.
     */
    override fun cancel() {
        toast.cancel()
    }
}