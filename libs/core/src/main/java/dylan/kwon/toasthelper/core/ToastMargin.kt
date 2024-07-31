package dylan.kwon.toasthelper.core

/**
 *  Set the margins of the toast.
 */
data class ToastMargin(

    /**
     * The horizontal margin, in percentage of the container width,
     * between the container's edges and the notification.
     */
    val horizontalFraction: Float,

    /**
     * The vertical margin, in percentage of the container height,
     * between the container's edges and the notification.
     */
    val verticalFraction: Float

)