package dylan.kwon.toasthelper.android.startup

import android.content.Context
import androidx.startup.Initializer
import dylan.kwon.toasthelper.android.AndroidToastFactory
import dylan.kwon.toasthelper.core.ToastHelper

class AndroidToastHelperInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        ToastHelper.setFactory(
            AndroidToastFactory(context)
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}