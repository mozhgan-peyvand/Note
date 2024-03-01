package com.peivandian.note_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.peivandian.note_ui.util.ui.TabsWithHorizontalPagerScreen
import com.peivandian.note_ui.R as NoteUi
import com.peivandian.base.R as BaseUi

@Composable
fun NoteListScreen(
    navHostController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = BaseUi.dimen.spacing_6x),
                    vertical = dimensionResource(
                        id = BaseUi.dimen.spacing_6x
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                contentDescription = ""
            )
            Text(
                text = "Alireza abbasi", modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = BaseUi.dimen.spacing_2x
                    )
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = BaseUi.dimen.spacing_6x))
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_arrow_forward_ios_24),
                contentDescription = ""
            )
            Column(modifier = Modifier.align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally){
                Row {
                    Image(
                        imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_account_circle_24),
                        contentDescription =""
                    )
                    Text(text = "ToDay")
                }
                Text(modifier = Modifier, text = "Friday, 9 March")

            }

            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                imageVector = ImageVector.vectorResource(NoteUi.drawable.baseline_arrow_back_ios_24),
                contentDescription = ""
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = BaseUi.dimen.spacing_4x)),
            horizontalArrangement = Arrangement.Center,
        ) {
            TabsWithHorizontalPagerScreen(Modifier.weight(5f))




        }


    }


}