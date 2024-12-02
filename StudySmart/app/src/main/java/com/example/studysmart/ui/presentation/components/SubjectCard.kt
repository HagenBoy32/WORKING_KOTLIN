package com.example.studysmart.ui.presentation.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
//import java.lang.reflect.Modifier
import com.example.studysmart.R
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subjectName: String,
    //headingText: String,
    gradientColors: List<Color>,
    count: String,
    onCLick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clickable{ onCLick()}
            .background(
                brush = Brush.verticalGradient(gradientColors),
                shape = MaterialTheme.shapes.medium
            )

    ){
      Column (
          modifier = Modifier.padding(12.dp),
          verticalArrangement = Arrangement.Center,
      ){
          Image(
             painter = painterResource(R.drawable.img_books),
              contentDescription = subjectName,
              modifier = Modifier.size(80.dp)
          )
          Text(
              modifier = Modifier.fillMaxWidth(),
              text = subjectName,
              style = MaterialTheme.typography.headlineMedium,
              color = Color.White,
          )
      }



    }


}
