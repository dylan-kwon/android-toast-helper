package dylan.kwon.toasthelper.android

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import dylan.kwon.toasthelper.android.mapper.addCallbacks
import dylan.kwon.toasthelper.android.mapper.mapActualHiddenCallbacks
import dylan.kwon.toasthelper.android.mapper.mapActualShownCallbacks
import dylan.kwon.toasthelper.android.mapper.toActual
import dylan.kwon.toasthelper.core.Toast
import dylan.kwon.toasthelper.core.ToastCancellation
import dylan.kwon.toasthelper.core.ToastDuration
import dylan.kwon.toasthelper.core.ToastMargin
import dylan.kwon.toasthelper.core.ToastPosition
import android.widget.Toast as ActualToast

/**
 * This object is implemented using the actual toast that runs on Android.
 */
class AndroidToast(
    /**
     * Android Application Context.
     * Used for executing the actual toast.
     */
    private val applicationContext: Context,

    /**
     * The toast message.
     */
    override val message: String,

    /**
     * The duration for which the toast is displayed on the screen.
     *
     * [ToastDuration.SHORT] is [android.widget.Toast.LENGTH_SHORT]
     *
     * [ToastDuration.LONG] is [android.widget.Toast.LENGTH_LONG]
     */
    override val duration: ToastDuration,

    /**
     * The margin between the toast and the screen.
     * Does not work on [android.os.Build.VERSION_CODES.R] or higher.
     */
    override val margin: ToastMargin? = null,

    /**
     * The position where the toast will be displayed on the screen.
     * Does not work on [android.os.Build.VERSION_CODES.R] or higher.
     */
    override val position: ToastPosition? = null

) : Toast() {

    /**
     * Creates and displays a new toast on the screen.
     */
    override fun show(): ToastCancellation {
        val toast = createActualToast().apply {
            show()
        }
        return AndroidToastCancellation(toast)
    }

    /**
     * Creates a new toast (does not display on the screen).
     */
    private fun createActualToast() = ActualToast.makeText(
        applicationContext, message, duration.toActual()
    ).apply {
        setMargin()
        setPosition()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            addCallbacks()
        }
    }

    /**
     * Sets the [margin] for the toast.
     */
    private fun ActualToast.setMargin() {
        if (margin == null) {
            return
        }
        setMargin(
            margin.horizontalFraction,
            margin.verticalFraction
        )
    }

    /**
     * Sets the [position] for the toast.
     */
    private fun ActualToast.setPosition() {
        if (position == null) {
            return
        }
        setGravity(
            position.gravity.toActual(),
            position.xOffset,
            position.yOffset
        )
    }

    /**
     * Maps the callback to [android.widget.Toast.Callback] and then adds it to the toast.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun ActualToast.addCallbacks() {
        addCallbacks(onShownCallbacks.mapActualShownCallbacks())
        addCallbacks(onHiddenCallbacks.mapActualHiddenCallbacks())
    }
}