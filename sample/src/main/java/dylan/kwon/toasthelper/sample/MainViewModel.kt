package dylan.kwon.toasthelper.sample

import androidx.lifecycle.ViewModel
import dylan.kwon.toasthelper.android.AndroidToastFactory
import dylan.kwon.toasthelper.core.ToastCancellation
import dylan.kwon.toasthelper.core.ToastDuration
import dylan.kwon.toasthelper.core.ToastHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    private var toastCancellation: ToastCancellation? = null

    fun showToast() {
        val toast = ToastHelper.create(
            message = message.value,
            duration = ToastDuration.SHORT,
        ).apply {
            addShownCallback {
                _state.value = true
            }
            addHiddenCallback {
                _state.value = false
            }
        }
        toastCancellation = toast.show()
    }

    fun cancel() {
        toastCancellation?.cancel()
        toastCancellation = null
    }

    fun updateMessage(text: String) {
        _message.value = text
    }

}