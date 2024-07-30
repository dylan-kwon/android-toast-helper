package dylan.kwon.toasthelper.core

import dylan.kwon.toasthelper.core.callback.OnToastHiddenCallback
import dylan.kwon.toasthelper.core.callback.OnToastShownCallback

abstract class Toast {

    /**
     * Toast message.
     */
    abstract val message: String

    /**
     * The duration the toast is displayed.
     */
    open val duration: ToastDuration = ToastDuration.SHORT

    /**
     * Set the margins of the toast.
     */
    open val margin: ToastMargin? = null

    /**
     * The position where the toast is displayed.
     */
    open val position: ToastPosition? = null

    /**
     * A list that stores callbacks invoked when the toast is displayed.
     */
    protected val onShownCallbacks = mutableListOf<OnToastShownCallback>()

    /**
     * A list that stores callbacks invoked when the toast is hidden.
     */
    protected val onHiddenCallbacks = mutableListOf<OnToastHiddenCallback>()

    /**
     * Add [OnToastShownCallback].
     */
    open fun addShownCallback(callback: OnToastShownCallback) {
        onShownCallbacks.add(callback)
    }

    /**
     * Add [OnToastHiddenCallback].
     */
    open fun addHiddenCallback(callback: OnToastHiddenCallback) {
        onHiddenCallbacks.add(callback)
    }

    /**
     * Remove [OnToastShownCallback].
     */
    open fun removeShownCallback(callback: OnToastShownCallback) {
        onShownCallbacks.remove(callback)
    }

    /**
     * Remove [OnToastHiddenCallback].
     */
    open fun removeHiddenCallback(callback: OnToastHiddenCallback) {
        onHiddenCallbacks.remove(callback)
    }

    /**
     * Displays the toast on the screen and returns a [ToastCancellation] that can cancel the toast.
     */
    abstract fun show(): ToastCancellation
}