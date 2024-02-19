package com.anurag.firebaseauthflow.SkillChooser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Autocomplete.AutoComplete
import com.anurag.firebaseauthflow.common.Searchbar.SearchViewModel
import com.anurag.firebaseauthflow.common.SkillCard
import com.anurag.firebaseauthflow.common.SkillCardV2

@Composable
fun SkillChooser(searchVM: SearchViewModel, skills: List<String>?, useV1: Boolean = false) {
    skills?.forEach {
        searchVM.add(it)
    }
    if (useV1)
        SkillCardGroupV1(searchVM)
    else
        SkillCardGroup(searchVM, useV1)
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillCardGroupV1(searchVM: SearchViewModel = SearchViewModel()) {
    val selected by searchVM.selected.collectAsState()
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            AutoComplete(searchVM, useV1 = true)
            Divider(
                modifier = Modifier.height(2.dp)
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    6.dp,
                ),
            ) {
                if (selected.isEmpty()) {
                    SkillCardV2(skill = "No skills chosen", onClick = {}, disabled = true)
                } else
                    selected.forEach {
                        SkillCard(skill = it, selected = selected.contains(it)) {
                            searchVM.toggleSelection(it)
                        }
                    }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillCardGroup(searchVM: SearchViewModel = SearchViewModel(), useV1: Boolean = false) {
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