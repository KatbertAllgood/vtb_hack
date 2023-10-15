package com.example.vtb_hackathon.ui.screen.map.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.entity.BankOfficePresentation
import com.example.vtb_hackathon.ui.theme.VTBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapNavigationBottom(
    onDismissRequest: () -> Unit,
    modalStateForNavigation: SheetState,
    onStartWalking: () -> Unit,
    onStartDriving: () -> Unit,
    office: BankOfficePresentation,
    drivingTime: String,
    drivingDistance: String,
    walkingTime: String,
    walkingDistance: String
) {

    var selected by rememberSaveable {
        mutableStateOf(1)
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = modalStateForNavigation,
        containerColor = VTBTheme.backgroundColors.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.from_my_location),
                color = VTBTheme.textColors.secondary,
                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            Text(
                text = stringResource(id = R.string.where,
                    office.address),
                color = VTBTheme.textColors.primary,
                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = VTBTheme.shape.cornersStyle,
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected == 0) VTBTheme.backgroundColors.accent else VTBTheme.backgroundColors.primary
                    ),
                    modifier = Modifier
                        .clickable {
                            selected = 0
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = if (selected == 0) R.drawable.ic_car_selected else R.drawable.ic_car_unselected),
                            contentDescription = null
                        )
                        Spacer(
                            modifier = Modifier
                                .width(6.dp)
                        )
                        Text(
                            text = drivingTime,
                            color = if (selected == 0) VTBTheme.textColors.contrast else VTBTheme.textColors.secondary,
                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .width(6.dp)
                )

                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = VTBTheme.shape.cornersStyle,
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected == 1) VTBTheme.backgroundColors.accent else VTBTheme.backgroundColors.primary
                    ),
                    modifier = Modifier
                        .clickable {
                            selected = 1
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = if (selected == 1) R.drawable.ic_man_selected else R.drawable.ic_man_unselected),
                            contentDescription = null
                        )
                        Spacer(
                            modifier = Modifier
                                .width(4.dp)
                        )
                        Text(
                            text = walkingTime,
                            color = if (selected == 1) VTBTheme.textColors.contrast else VTBTheme.textColors.secondary,
                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = VTBTheme.shape.cornersStyle,
                    colors = CardDefaults.cardColors(
                        containerColor = VTBTheme.backgroundColors.secondary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                12.dp
                            )
                    ) {
                        Text(
                            text = if (selected == 0) drivingTime else walkingTime,
                            color = VTBTheme.textColors.primary,
                            fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                            fontSize = 16.sp
                        )
                        Spacer(
                            modifier = Modifier
                                .height(0.dp)
                        )
                        Text(
                            text = if (selected == 0) drivingDistance else walkingDistance,
                            color = VTBTheme.textColors.secondary,
                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                            fontSize = 14.sp
                        )
                    }
                }

            }

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Button(
                onClick = {

                    if (selected == 0) {
                        onStartDriving()
                    } else {
                        onStartWalking()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = VTBTheme.shape.cornersStyle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = VTBTheme.backgroundColors.accent
                )
            ) {
                Text(
                    text = stringResource( id = R.string.start),
                    color = VTBTheme.textColors.contrast,
                    fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(48.dp)
            )
        }
    }
}