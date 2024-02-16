package com.anurag.firebaseauthflow.common

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SkillCardV2(
    skill: String,
    selected: Boolean = false,
    disabled: Boolean = false,
    onClick: (skill: String) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = skill,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f, true),
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = { onClick(skill) }, Modifier.width(50.dp),
                enabled = !disabled
            ) {
                if (!disabled)
                    Icon(
                        imageVector = if (selected) Icons.Default.Close else Icons.Filled.AddCircle,
                        contentDescription = null,
                        tint = if (selected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline
                    )
                else
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillCard(
    skill: String,
    selected: Boolean = false,
    disabled: Boolean = false,
    onClick: (skill: String) -> Unit
) {
    FilterChip(
        onClick = {
            onClick(skill)
        },
        leadingIcon = {
            if (!disabled)
                Icon(
                    if (selected) Icons.Filled.Clear else Icons.Filled.AddCircle,
                    contentDescription = "Add",
                    Modifier.size(FilterChipDefaults.IconSize),
                    if (selected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline
                )
        },
        label = {
            Text(text = skill)
        },
        selected = selected,
        enabled = !disabled,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.secondary,
            selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledLabelColor = MaterialTheme.colorScheme.onSecondary,
            disabledSelectedContainerColor = MaterialTheme.colorScheme.secondary,

        ),
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SkillCardPreview() {
    FirebaseAuthFlowTheme {
        Surface {
            SkillCard(skill = "React Native") {}
        }
    }
}