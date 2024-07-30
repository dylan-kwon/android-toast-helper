package dylan.kwon.toasthelper.android.mapper

import android.widget.Toast
import dylan.kwon.toasthelper.core.ToastDuration

/**
 * Library Enum to [Toast] Enum.
 */
internal fun ToastDuration.toActual() = when (this) {
    ToastDuration.SHORT -> Toast.LENGTH_SHORT
    ToastDuration.LONG -> Toast.LENGTH_LONG
}