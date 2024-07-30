package dylan.kwon.toasthelper.core

/**
 * The position where the toast is displayed.
 */
data class ToastPosition(
    /**
     * The gravity of the toast.
     */
    val gravity: ToastGravity,

    /**
     * The x-offset from the gravity.
     */
    val xOffset: Int,

    /**
     * The y-offset from the gravity.
     */
    val yOffset: Int
)
