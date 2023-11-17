package io.github.initrc.slidr.feature.chat

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.initrc.slidr.R
import io.github.initrc.slidr.ui.theme.SlidrTheme


data class Message(val body: String, val isFromMe: Boolean, val isLoading: Boolean = false)

@Composable
fun MessageCard(message: Message) {
    Row(
        horizontalArrangement = if (message.isFromMe) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        if (!message.isFromMe) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .paint(painter = painterResource(R.drawable.ic_launcher_background))
            )

            Spacer(modifier = Modifier.width(8.dp))
        }


        val messageBorderColor by animateColorAsState(
            if (message.isFromMe) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            label = "message border color"
        )

        val messageBgColor by animateColorAsState(
            if (message.isFromMe) {
                if (message.isLoading) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.primary
                }
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            label = "message bg color"
        )

        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 1.dp,
            color = messageBgColor,
            modifier = Modifier
                .animateContentSize()
                .border(1.dp, messageBorderColor, MaterialTheme.shapes.medium)
                .widthIn(max = 300.dp)
        ) {
            Text(
                text = message.body,
                modifier = Modifier.padding(all = 8.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = if (message.isFromMe) TextAlign.End else TextAlign.Start
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true
)
@Composable
fun PreviewMessageCard() = SlidrTheme {
    Surface {
        MessageCard(
            message = Message("Sunny", false)
        )
    }
}
