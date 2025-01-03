package com.example.studysmart.domain.model

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.presentation.Red
import com.example.studysmart.ui.presentation.components.DeleteDialog
import com.example.studysmart.ui.presentation.components.SubjectListBottomSheet
import com.example.studysmart.ui.presentation.components.TaskCheckBox
import com.example.studysmart.ui.presentation.components.TaskDatePicker
import com.example.studysmart.util.Priority
import java.lang.reflect.Modifier
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {

    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }


    var isDatePickerDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    val sheetState = rememberModalBottomSheetState()
    var taskTitleError by remember { mutableStateOf(false) }

    taskTitleError = when {
        title.isBlank() -> "Please enter task title."
        title.length < 4 -> "Task Title is too short."
        title.length > 30 -> "Task title is too long. "
        else -> null
    }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Task?",
        bodyText = "Are you sure, you want to delete this task? This action can not be undone",
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = { isDeleteDialogOpen = false }
    )

    TaskDatePicker(
        state = datePickerState,
        isOpen = isDatePickerDialogOpen,
        onDismissRequest = { isDatePickerDialogOpen = false },
        onConfirmButtonClicked = {
            isDatePickerDialogOpen = false
        }
    )

    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        subjects = subjects,
        onDismissRequest = { isBottomSheetOpen = false },
        onSubjectClicked = {


        },
    )

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isTaskExist = true,
                isComplete = false,
                checkBoxBordercolor = Red,
                onBackButtonClick = { },
                onDeleteButtonClick = { isDeleteDialogOpen = true },
                OnCheckBoxClick = { }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                isError = taskTitleError != null && title.isNotBlank(),
                supportingText = { Text(text = taskTitleError.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modfier.fillMaxWidth(),
                value = description,
                onValueChange = onValueChange,
            )
            label = { Text(text = "Description") },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Due Date",
            style = MaterialTheme.typography.bodySmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = datePickerState.selectedDateMillis.changeMillisToDateString(),
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = { isDatePickerDialogOpen = true }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Due Date"
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Priority",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(20.dp)
        ) {
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = "Priority",
                style = MaterialTheme.typography.bodySmall

            )


        }

        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TaskScreenTopBar(
        isTaskExist: Boolean,
        isComplete: Boolean,
        checkBoxBordercolor: androidx.compose.ui.graphics.Color,
        onBackButtonClick: () -> Unit,
        onDeleteButtonClick: () -> Unit,
        onCheckButtonClick: () -> Unit,
        onCheckedChange: (Boolean) -> Unit,
        onCheckBoxClick: () -> Unit,
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { onBackButtonClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            },
            title = {
                Text(
                    text = "Task",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            actions = {
                if (isTaskExist) {
                    TaskCheckBox(
                        isComplete = isComplete,
                        borderColor = checkBoxBordercolor,
                        onCheckBoxClick = onCheckBoxClick
                    )
                    IconButton(onClick = onDeleteButtonClick) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Navigate back",

                            )
                    }
                }


            }


    }


}

@Composable
private fun PriorityButton(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp)
            .border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = labelColor)
    }
}
