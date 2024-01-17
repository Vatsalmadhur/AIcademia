package com.anurag.firebaseauthflow.common.Searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.SkillCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Searchbar(searchVm: SearchViewModel, callback: (it: String) -> Unit) {
    val scrollState = rememberScrollState()
    val queryString by searchVm.query.collectAsState()
    val isSearching by searchVm.isSearching.collectAsState()
    val skills by searchVm.skills.collectAsState()
    val selected by searchVm.selected.collectAsState()

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isSearching) 0.dp else 8.dp),
        query = queryString,
        onQueryChange = { searchVm.onSearchTextChange(it) },
        onSearch = {
            searchVm.toggleSearch()
            callback(it)
        },
        active = isSearching,
        onActiveChange = {
            searchVm.toggleSearch()
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            if (isSearching)
                IconButton(onClick =
                { searchVm.toggleSearch()}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            else
                Icon(imageVector = Icons.Default.Search, contentDescription = null)

        },
        trailingIcon = {
            if (isSearching)
            IconButton(onClick = { searchVm.clear() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
    ) {
        Column (modifier = Modifier.height(200.dp)){
            FlowColumn(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 4.dp)
            ) {

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        6.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                ) {
                    if (skills.isEmpty()) {
                        Text(text = "No relevant skill found")
                        Text(text = "Submit to add it to the list")
                    } else
                        skills.forEach {
                            SkillCard(skill = it, selected.contains(it)) {
                                searchVm.toggleSelection(it)
                            }
                        }
                }
            }
        }
    }
}