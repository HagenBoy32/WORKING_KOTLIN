package Subject


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.presentation.components.CountCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.ui.presentation.components.AddSubjectDialog
import com.example.studysmart.ui.presentation.components.CountCard
import com.example.studysmart.ui.presentation.components.DeleteDialog
import com.example.studysmart.ui.presentation.components.studySessionsList
import com.example.studysmart.presentation.components.tasksList
//import com.example.studysmart.ui.presentation.dashboard.DashBoardScreenTopBar.TaskScreenRouteDestination
//import com.example.studysmart.ui.presentation.task
//import com.example.studysmart.util.SnackbarEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen() {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isFABFExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDEleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHours by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = { subjectName = it },
        onGoalHoursChange = { goalHours = it },
        selectedColors = selectedColor,
        onColorChange = { selectedColor = it },
        onDismissRequest = { isAddSubjectDialogOpen = false }

    DeleteDialog(
        isOpen = isDeleteSubjectDialogOpen,
        title = "Delete Subject?",
        bodyText = "Are you sure, you want to delete this subject? All related " +
                "tasks and study sessions will be permanently removed. This action can not be undone",
        onDismissRequest = { isDeleteSubjectDialogOpen = false },
        onConfirmButtonClick = {
        onEvent(SubjectEvent.DeleteSubject)
        isDeleteSubjectDialogOpen = false
        }
    )

    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete Subject?",
        bodyText = "Are you sure, you want to delete this subject? All related " +
                "tasks and study sessions will be permanently removed. This action can not be undone",
        onDismissRequest = { isDeleteSubjectDialogOpen = false },
        onConfirmButtonClick = {
        onEvent(SubjectEvent.DeleteSubject)
        isDeleteSubjectDialogOpen = false
        }
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectScreenTopBar(
            title = "English",
            onBackButtonClick = { },
            onDeletetButtonClick = {isDEleteSubjectDialogOpen },
            onEditButtonClick = { },
            scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                expanded = isFABFExpanded,
                ) { paddingValue ->

                var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
                var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
                var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                ) {
                    item {
                        SubjectOverviewSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            studiedHours = "10",
                            goalHours = "15",
                            progress = state.progress


                        )
                    }
                }

            }
            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun SubjectScreenTopBar(
                title: String,
                onBackButtonClick: () -> Unit,
                onDeleteButtonClick: () -> Unit,
                onEditButtonClick: () -> Unit,
                scrollBehavior: TopAppBarScrollBehavior
            ) {
                LargeTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = onBackButtonClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "navigate back"
                            )
                        }
                    },
                    title = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    actions = {
                        IconButton(onClick = onDeleteButtonClick) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Subject"
                            )
                        }
                        IconButton(onClick = onEditButtonClick) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Subject"
                            )
                        }
                    }
                )
            }

            @Composable
            fun SubjectOverviewSection(
                modifier: Modifier,
                subjectCount: Int,
                studiedHours: String,
                progress: Float,
                goalHours: String

            ) {
                val percentageProgress = remember(key1 = progress) {
                    (progress * 100).toInt().coerceIn(0, 100)
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CountCard(
                        modifier = Modifier.weight(1f),
                        headingText = "GoalStudy Hours",
                        count = "5"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CountCard(
                        modifier = Modifier.weight(1f),
                        headingText = "Study Hours",
                        count = goalHours
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.width(10.dp),
                        contentAlignment = Alignment.Center,
                        // count = studiedHours
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(100.dp),
                            progress = 1f,
                            strokeWidth = 10.dp,
                            strokeCap = StrokeCap.Round,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                        CircularProgressIndicator(
                            modifier = Modifier.size(100.dp),
                            progress = 1f,
                            strokeWidth = 10.dp,
                            strokeCap = StrokeCap.Round,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                        Text(text = "$percentageProgress%")
                    }


                }

            }
        }