package com.anurag.firebaseauthflow.common.Autocomplete

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Searchbar.SearchViewModel
import com.anurag.firebaseauthflow.common.SkillCard
import com.anurag.firebaseauthflow.common.SkillCardV2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AutoComplete(searchVm: SearchViewModel = SearchViewModel(), useV1: Boolean = false,placeHolder:String="Start Typing") {
    val queryString by searchVm.query.collectAsState()
    val skills by searchVm.skills.collectAsState()
    val selected by searchVm.selected.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f, true)) {
                TextField(
                    value = queryString,
                    onValueChange = { searchVm.onSearchTextChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Go
                    ),
                    label = { Text(text = placeHolder) },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent

                    ),
                    trailingIcon = {
                        if (!queryString.isBlank())
                            IconButton(
                                onClick = { searchVm.clear() },
                                modifier = Modifier.background(
                                    MaterialTheme.colorScheme.background
                                ),

                                ) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                    },
                    placeholder = { "Type to add skills" },
                    singleLine = true
                )
            }
            IconButton(onClick = {
                searchVm.save()
            }, modifier = Modifier.width(50.dp)) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        }
        if (useV1) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    6.dp,
                ),
            ) {
                skills.forEach {
                    SkillCard(skill = it, selected = selected.contains(it)) {
                        searchVm.toggleSelection(it)
                    }
                }
            }
        } else {
            FlowColumn(
                verticalArrangement = Arrangement.spacedBy(
                    6.dp,
                    alignment = Alignment.Top
                )
            ) {
                skills.forEach {
                    SkillCardV2(skill = it, selected.contains(it), onClick = {
                        searchVm.toggleSelection(it)
                    })
                }
            }

        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun AutoCompletePreview() {
    FirebaseAuthFlowTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AutoComplete()
        }
    }
}