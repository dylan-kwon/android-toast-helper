package dylan.kwon.toasthelper.test

import dylan.kwon.toasthelper.core.ToastHelper
import org.junit.Rule
import org.junit.Test

class ToastHelperTest {

    @get:Rule
    val toastHelperTestRule = ToastHelperTestRule()

    @Test
    fun showToastWithText() {
        ToastHelper.show("Hello")
    }

    @Test
    fun showToastWithInteger() {
        ToastHelper.show(123456)
    }

    @Test
    fun showToastWithCallback() {
        ToastHelper.create("World").apply {

            // Add Shown Callbacks.
            repeat(3) {
                addShownCallback {
                    println("shown: $it")
                }
            }

            // Add Hidden Callbacks.
            repeat(3) {
                addHiddenCallback {
                    println("hidden: $it")
                }
            }

        }.show()
    }

}