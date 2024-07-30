package dylan.kwon.toasthelper.core

object ToastHelper {

    /**
     * This is a factory for creating toasts.
     * The appropriate factory can be set depending on the application runtime environment or testing environment.
     */
    private lateinit var factory: ToastFactory

    /**
     * Sets the factory.
     */
    fun setFactory(factory: ToastFactory) {
        this.factory = factory
    }

    /**
     * Create toast with [resId].
     */
    fun create(
        resId: Int,
        duration: ToastDuration = ToastDuration.SHORT,
        position: ToastPosition? = null,
        margin: ToastMargin? = null
    ): Toast = factory.create(resId, duration, position, margin)

    /**
     * Create toast with [message].
     */
    fun create(
        message: String,
        duration: ToastDuration = ToastDuration.SHORT,
        position: ToastPosition? = null,
        margin: ToastMargin? = null
    ): Toast = factory.create(message, duration, position, margin)

    /**
     * Show toast with [resId].
     */
    fun show(
        resId: Int,
        duration: ToastDuration = ToastDuration.SHORT,
        position: ToastPosition? = null,
        margin: ToastMargin? = null
    ): ToastCancellation = create(resId, duration, position, margin).show()

    /**
     * Show toast with [message].
     */
    fun show(
        message: String,
        duration: ToastDuration = ToastDuration.SHORT,
        position: ToastPosition? = null,
        margin: ToastMargin? = null
    ): ToastCancellation = create(message, duration, position, margin).show()

}