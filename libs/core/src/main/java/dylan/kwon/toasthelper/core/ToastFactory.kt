package dylan.kwon.toasthelper.core

/**
 * This is a factory for creating toast objects.
 * Extend this interface and pass a concrete factory to ToastHelper.
 */
interface ToastFactory {

    /**
     * Creates a toast using an Int type key that can retrieve a string.
     */
    fun create(
        resId: Int,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast

    /**
     * Creates a toast using a string.
     */
    fun create(
        message: String,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast

}