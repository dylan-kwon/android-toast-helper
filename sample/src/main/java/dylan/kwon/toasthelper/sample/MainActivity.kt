@file:OptIn(ExperimentalLayoutApi::class)

package dylan.kwon.toasthelper.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dylan.kwon.toasthelper.android.AndroidToastFactory
import dylan.kwon.toasthelper.core.ToastHelper
import dylan.kwon.toasthelper.sample.ui.theme.ToasthelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ToasthelperTheme {

                val viewModel: MainViewModel = viewModel()

                val message by viewModel.message.collectAsStateWithLifecycle()
                val state by viewModel.state.collectAsStateWithLifecycle()

                Screen(
                    message = message,
                    state = state,
                    onTextChange = viewModel::updateMessage,
                    onShowClick = viewModel::showToast,
                    onCancelClick = viewModel::cancel
                )
            }
        }
    }
}

@Composable
fun Screen(
    message: String,
    state: Boolean,
    onTextChange: (String) -> Unit,
    onShowClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                32.dp, Alignment.CenterVertically
            )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "isShown: $state",
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Toast Message")
                },
                value = message,
                onValueChange = onTextChange,
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp, Alignment.CenterHorizontally
                ),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp, Alignment.CenterVertically
                )
            ) {
                ShowButton(
                    onClick = onShowClick
                )
                CancelButton(
                    onClick = onCancelClick
                )
            }
        }
    }
}

@Composable
fun ShowButton(onClick: () -> Unit) {
    ElevatedButton(onClick = onClick) {
        Text(text = "Show")
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    ElevatedButton(onClick = onClick) {
        Text(text = "Cancel")
    }
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    ToasthelperTheme {
        Screen(
            message = "Hello World",
            state = false,
            onShowClick = {},
            onTextChange = {},
            onCancelClick = {}
        )
    }
}

