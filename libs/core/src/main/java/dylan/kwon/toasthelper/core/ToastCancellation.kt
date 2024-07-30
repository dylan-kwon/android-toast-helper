package dylan.kwon.toasthelper.core

interface ToastCancellation {
    /**
     * Cancels the toast.
     */
    fun cancel()
}

/**
 * An empty toast cancellation.
 * Can be used if the toast created by the [Toast] implementation is not cancellable.
 */
object EmptyToastCancellation : ToastCancellation {
    override fun cancel() {}
}