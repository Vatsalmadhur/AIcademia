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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anurag.firebaseauthflow.common.CustomButtonV2
import com.anurag.firebaseauthflow.firestore.QuizModel

@Composable
fun QuizList(qList: List<QuizModel> = listOf(), refreshQuiz: ()-> Unit) {

    val quizVM: QuizViewModel = viewModel()
    val isLocked by quizVM.isLocked.collectAsState()
    val isReveal by quizVM.isReveal.collectAsState()


    qList.forEachIndexed { idx, it ->
        QuizUI(idx + 1, question = it.question, options = it.options, it.answer, quizVM)
        if (idx < qList.size - 1) {
            Divider()
        }
    }

    Spacer(modifier = Modifier.height(4.dp))
    if (!isReveal)
        CustomButtonV2(label = if (!isLocked) "Lock answers" else "Submit",
            icon = if (isLocked) Icons.Default.Check else Icons
                .Default.Lock,
            onClick = {
                if (!isLocked) {
                    quizVM.lockAnswers()
                } else {
                    quizVM.saveAnswers()
                }
            })
    else
        CustomButtonV2(label = "Refresh questions",
            icon = Icons.Default.Refresh,
            onClick = {
                refreshQuiz()
            })
}

@Composable
fun QuizUI(
    idx: Int,
    question: String,
    options: List<String>,
    answer: Int, quizVM: QuizViewModel
) {
    val isReveal by quizVM.isReveal.collectAsState()
    val answersMarked by quizVM.answersMarked.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(text = "$idx. $question")
        Spacer(modifier = Modifier.height(4.dp))
        options.forEachIndexed { index, option ->
            OptionItem(
                option = option,
                isCorrect = index == answer,
                isSelected = index == answersMarked[idx - 1],
                onOptionSelected = {
                    quizVM.markAnswer(idx - 1, index)
                },
                reveal = isReveal
            )
        }
    }
}

@Composable
fun OptionItem(
    option: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit,
    isCorrect: Boolean,
    reveal: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (reveal) {
                    if (isCorrect) {
                        Color.Green
                    } else {
                        if (isSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface
                    }
                } else {
                    if (isSelected)
                        MaterialTheme.colorScheme.tertiary
                    else
                        MaterialTheme.colorScheme.surface
                }
            )
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
        }
    }
}