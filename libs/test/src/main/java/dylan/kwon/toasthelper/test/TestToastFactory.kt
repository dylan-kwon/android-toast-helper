package dylan.kwon.toasthelper.test

import dylan.kwon.toasthelper.core.Toast
import dylan.kwon.toasthelper.core.ToastDuration
import dylan.kwon.toasthelper.core.ToastFactory
import dylan.kwon.toasthelper.core.ToastMargin
import dylan.kwon.toasthelper.core.ToastPosition

/**
 * This is the factory for [TestToast]
 */
object TestToastFactory : ToastFactory {

    /**
     * Returns a [TestToast] that converts the [resId] to a string and prints it.
     */
    override fun create(
        resId: Int,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast {
        return TestToast(resId.toString())
    }

    /**
     * Returns a [TestToast] that prints a [message].
     */
    override fun create(
        message: String,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast {
        return TestToast(message)
    }

}