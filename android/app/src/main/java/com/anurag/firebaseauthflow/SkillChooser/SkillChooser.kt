package com.anurag.firebaseauthflow.SkillChooser

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Autocomplete.AutoComplete
import com.anurag.firebaseauthflow.common.Searchbar.SearchViewModel
import com.anurag.firebaseauthflow.common.SkillCardV2
import com.anurag.firebaseauthflow.firestore.About

@Composable
fun SkillChooser(searchVM: SearchViewModel) {
    SkillCardGroup(searchVM)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillCardGroup(searchVM: SearchViewModel = SearchViewModel()) {
    val selected by searchVM.selected.collectAsState()
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            AutoComplete(searchVM)
            Divider(
                modifier = Modifier.height(2.dp)
            )
            FlowColumn(
                verticalArrangement = Arrangement.spacedBy(
                    6.dp,
                    alignment = Alignment.Top
                ),
            ) {
                if (selected.isEmpty()) {
                    SkillCardV2(skill = "No skills chosen", onClick = {}, disabled = true)
                } else
                    selected.forEach {
                        SkillCardV2(skill = it, selected = selected.contains(it)) {
                            searchVM.toggleSelection(it)
                        }
                    }
            }
        }
    }
}