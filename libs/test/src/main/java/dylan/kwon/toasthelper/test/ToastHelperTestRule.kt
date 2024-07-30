package dylan.kwon.toasthelper.test

import dylan.kwon.toasthelper.core.ToastFactory
import dylan.kwon.toasthelper.core.ToastHelper
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Sets the [ToastHelper] to [factory] before starting the test.
 * [TestToastFactory] is set as the default and can be changed.
 */
class ToastHelperTestRule(
    private val factory: ToastFactory = TestToastFactory
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        ToastHelper.setFactory(factory)
    }

}