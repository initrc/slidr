package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SendText(
    onSendClick: (String) -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it},
            shape = MaterialTheme.shapes.medium,
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onSend = {
                    onSendClick(text)
                    text = ""
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
        )

        IconButton(
            onClick = {
                onSendClick(text)
                text = ""
            },
            colors = IconButtonDefaults.outlinedIconButtonColors(),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
        ) {
            Icon(Icons.Outlined.Send, contentDescription = "send")
        }
    }
}

@Preview
@Composable
fun PreviewSendText() = MaterialTheme {
    Surface {
        SendText({}, Modifier)
    }
}