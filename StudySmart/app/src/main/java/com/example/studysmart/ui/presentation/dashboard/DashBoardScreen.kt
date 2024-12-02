package com.example.studysmart.ui.presentation.dashboard

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier // Import the correct Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.style.TextAlign
//port androidx.compose.ui.tooling.data.EmptyGroup.name

import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.presentation.components.tasksList
import com.example.studysmart.ui.presentation.components.AddSubjectDialog
import com.example.studysmart.ui.presentation.components.CountCard
import com.example.studysmart.ui.presentation.components.DeleteDialog
import com.example.studysmart.ui.presentation.components.SubjectCard
import com.example.studysmart.ui.presentation.components.studySessionsList


@Composable
fun DashBoardScreen() {

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName            by remember { mutableStateOf("") }
    var goalHours              by remember { mutableStateOf("") }
    var selectedColor          by remember { mutableStateOf(Subject.subjectCardColors.random()) }




    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = { subjectName = it },
        onGoalHoursChange = { goalHours = it },
        selectedColors = selectedColor,
        onColorChange = { selectedColor = it },
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = {
            isAddSubjectDialogOpen = false
        }
    )

    DeleteDialog(
        isOpen =isDeleteSessionDialogOpen,
        title = "Delete Subject",
        bodyText = "Are you sure you want to delete this subject?",
        onDismissRequest = { isDeleteSessionDialogOpen = false},
        onConfirmButtonClick = { isDeleteSessionDialogOpen }

    )

    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    subjectCount = 5,
                    studiedHours = "10",
                    goalHours = "15"
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier
                              .fillMaxWidth(),
                    sujectList = subjects,
                    onAddIconClicked = { isAddSubjectDialogOpen = true }

                )
            }
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }
            tasksList(
                sectionTitle = "Upcoming Tasks",
                emptyListText = "You dont have any upcoming tasks.\n Click the +"  +
                        " button to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            tasksList(
                sectionTitle = "Completed Tasks",
                emptyListText = "You dont have any completed tasks.\n" +
                 " button to add new task.",

                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {},
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            studySessionsList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any recent study sessions.\n " +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteIconClick = {isDeleteSessionDialogOpen = true}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "StudySMart",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}


@Composable
fun CountCardsSection(
    modifier: Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
) {
    Row(modifier = modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = "15"
        )
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Studied Hours",
            count = studiedHours.toString()
        )
    }

}

@Composable
fun SubjectCardsSection(
    modifier: Modifier,
    sujectList: List<Subject>,
    emptyListText: String = "You dont have any subjects.\n Click the  + button to add new subject.",
    onAddIconClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "Subject Count",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            IconButton(onClick = { onAddIconClicked }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }
        if (sujectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.img_books),
                contentDescription = emptyListText
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.DKGRAY,
                textAlign = TextAlign.Center
            )

        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList) { subject ->
                SubjectCard(
                    subjectName = subject.name,
                    gradientColors = subject.colors,
                    count = subject.goalHours.toString(),
                    onCLick = { }
                )
            }
        }
    }

}

fun LazyRow(
    horizontalArrangement: Arrangement.HorizontalOrVertical,
    contentPadding: PaddingValues
) {

}
