package io.github.initrc.slidr.feature.chat

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.core.model.Message

@Composable
fun MessageCard(message: Message, modifier: Modifier) {
    Row(
        horizontalArrangement = if (message.isFromMe) Arrangement.End else Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        if (!message.isFromMe) {
            Icon(
                Icons.Rounded.Face,
                contentDescription = stringResource(R.string.user_avatar_content_description),
                modifier = Modifier.size(40.dp)
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
            message = Message("Sunny", false),
            Modifier
        )
    }
}
