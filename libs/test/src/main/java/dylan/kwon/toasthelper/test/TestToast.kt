package dylan.kwon.toasthelper.test

import dylan.kwon.toasthelper.core.EmptyToastCancellation
import dylan.kwon.toasthelper.core.Toast
import dylan.kwon.toasthelper.core.ToastCancellation

/**
 * This is the toast used in the test environment.
 * Prints the toast message using println.
 */
class TestToast(
    override val message: String,
) : Toast() {

    /**
     * Prints the toast message using println.
     * Calls the callback before and after displaying the toast.
     */
    override fun show(): ToastCancellation {
        executeAllShownCallbacks()
        println(createPrintMessage())
        executeAllHiddenCallbacks()
        return EmptyToastCancellation
    }

    private fun createPrintMessage() =
        "[TOAST][${duration.name}] $message"

    private fun executeAllShownCallbacks() {
        onShownCallbacks.forEach { it() }
    }

    private fun executeAllHiddenCallbacks() {
        onHiddenCallbacks.forEach { it() }
    }
}