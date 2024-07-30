package dylan.kwon.toasthelper.android.mapper

import android.view.Gravity
import dylan.kwon.toasthelper.core.ToastGravity

/**
 * Library Enum to [android.view.Gravity]
 */
internal fun ToastGravity.toActual() = when (this) {
    ToastGravity.FILL -> Gravity.FILL
    ToastGravity.FILL_HORIZONTAL -> Gravity.FILL_HORIZONTAL
    ToastGravity.FILL_VERTICAL -> Gravity.FILL_VERTICAL
    ToastGravity.START -> Gravity.START
    ToastGravity.END -> Gravity.END
    ToastGravity.LEFT -> Gravity.LEFT
    ToastGravity.RIGHT -> Gravity.RIGHT
    ToastGravity.TOP -> Gravity.TOP
    ToastGravity.BOTTOM -> Gravity.BOTTOM
    ToastGravity.CENTER -> Gravity.CENTER
    ToastGravity.CENTER_HORIZONTAL -> Gravity.CENTER_HORIZONTAL
    ToastGravity.CENTER_VERTICAL -> Gravity.CENTER_VERTICAL
    ToastGravity.DISPLAY_CLIP_HORIZONTAL -> Gravity.DISPLAY_CLIP_HORIZONTAL
    ToastGravity.DISPLAY_CLIP_VERTICAL -> Gravity.DISPLAY_CLIP_VERTICAL
    ToastGravity.CLIP_HORIZONTAL -> Gravity.CLIP_HORIZONTAL
    ToastGravity.CLIP_VERTICAL -> Gravity.CLIP_VERTICAL
    ToastGravity.NO_GRAVITY -> Gravity.NO_GRAVITY
}
