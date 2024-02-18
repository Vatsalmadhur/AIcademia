package com.anurag.firebaseauthflow.common.Quiz

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anurag.firebaseauthflow.firestore.QuizModel

@Composable
fun QuizList(qList:List<QuizModel> = listOf()){

    val quizVM:QuizViewModel = viewModel()

    qList.forEachIndexed {idx, it ->
        QuizUI(idx+1, question = it.question, options = it.options, it.answer)
        if(idx< qList.size-1){
            Divider()
        }
    }
}

@Composable
fun QuizUI(
    idx: Number,
    question: String,
    options: List<String>,
    answer: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(text = "$idx. $question")
        Spacer(modifier = Modifier.height(4.dp))

        val selectedOptionIndex = remember { mutableStateOf(-1) }

        options.forEachIndexed { index, option ->
            OptionItem(
                option = option,
                isSelected = index == selectedOptionIndex.value,
                onOptionSelected = {
                    selectedOptionIndex.value = index
                }
            )
        }
    }
}

@Composable
fun OptionItem(
    option: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surface)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onOptionSelected() },
        )
        Text(
            text = option,
            modifier = Modifier.weight(1f),
            style = TextStyle(if (isSelected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview()
@Composable
fun QuizUIPreview() {
    FirebaseAuthFlowTheme {
        Surface() {
            QuizUI(
                1,
                question = "What is my name ?",
                options = listOf("ergergerg", "egerg erg ", "Anurag", "gerger gerg aer"),
                1
            )
        }
    }
}