package dylan.kwon.toasthelper.core

/**
 * Specifies the position where the toast is displayed.
 * The exact position is determined by the [Toast] implementation.
 */
enum class ToastGravity {
    FILL,
    FILL_HORIZONTAL,
    FILL_VERTICAL,
    START,
    END,
    LEFT,
    RIGHT,
    TOP,
    BOTTOM,
    CENTER,
    CENTER_HORIZONTAL,
    CENTER_VERTICAL,
    DISPLAY_CLIP_HORIZONTAL,
    DISPLAY_CLIP_VERTICAL,
    CLIP_HORIZONTAL,
    CLIP_VERTICAL,
    NO_GRAVITY
}