package com.example.vtb_hackathon.ui.screen.map.bottomsheet

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.ui.theme.VTBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersSheetBottom(
    onDismissSheet: () -> Unit,
    filterModalSheetState: SheetState
) {

    var indexCurrent by rememberSaveable {
        mutableStateOf(0)
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissSheet
        },
        sheetState = filterModalSheetState,
        containerColor = VTBTheme.backgroundColors.primary
    ) {

        Column {
            Text(
                text = stringResource(id = R.string.filters_departments),
                color = VTBTheme.textColors.primary,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        start = 16.dp
                    )
            )

            val filterList: List<String> = listOf(
                stringResource(id = R.string.for_people),
                stringResource(id = R.string.for_own_busy),
                stringResource(id = R.string.for_small_and_big_business),
                stringResource(id = R.string.for_huge_business),
            )

            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )

            filterList.forEachIndexed { index, filterName ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = filterName,
                        fontSize = 16.sp,
                        fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                        color = VTBTheme.textColors.primary,
                    )
                    RadioButton(
                        selected = (index == indexCurrent),
                        onClick = {
                            Log.d("RV",index.toString())
//                            viewModel.updateSelectedFilterIndex(index)
//                            viewModel.updateSelectedService("Все")
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = VTBTheme.strokeColors.accent,
                            unselectedColor = VTBTheme.strokeColors.accent,
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
        }
    }
}