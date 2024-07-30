package dylan.kwon.toasthelper.sample

import dylan.kwon.toasthelper.test.ToastHelperTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val toastHelperTestRule = ToastHelperTestRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun showToastTest() {
        viewModel.updateMessage("Hello World")
        viewModel.showToast()
    }

}