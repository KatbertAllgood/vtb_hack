package com.example.vtb_hackathon.ui.screen.map.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.entity.BankOfficePresentation
import com.example.vtb_hackathon.ui.theme.VTBTheme

@Preview
@Composable
private fun DepartmentInfoPreview() {
//    DepartmentInfo()
}

@Composable
fun DepartmentInfo(
    bank: BankOfficePresentation,
    doRoute: () -> Unit,
//    departmentName: String,
//    address: String,
//    allLoadPercent: Float,
//    serviceLoadPercent: Float,
//    serviceName: String,
//    nearbySubway: String,
//    subwayLine: String,
//    subwayDistance: String
) {
    VTBTheme {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .background(VTBTheme.backgroundColors.primary)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = bank.name,
                fontSize = 18.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
                modifier = Modifier
                    .padding(
                        bottom = 14.dp
                    )
            )

            Text(
                text = bank.address,
                fontSize = 16.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.secondary,
                modifier = Modifier
                    .padding(
                        bottom = 14.dp
                    )
            )

            Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(
                      bottom = 6.dp
                  ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.all_load),
                    fontSize = 14.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary
                )

                Text(
                    text = "${bank.occupancy}%",
                    fontSize = 14.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary
                )
            }

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 14.dp
                    )
                    .height(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                progress = (bank.occupancy.toDouble() / 100.0).toFloat(),
                color = VTBTheme.backgroundColors.accent,
                trackColor = VTBTheme.backgroundColors.secondary
            )

            Button(
                onClick = {
                    doRoute()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = VTBTheme.backgroundColors.accent,
                    contentColor = VTBTheme.textColors.contrast,
                    disabledContainerColor = VTBTheme.backgroundColors.disable,
                    disabledContentColor = VTBTheme.textColors.contrast
                ),
                shape = VTBTheme.shape.cornersStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.do_route),
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.contrast
                )
            }

            Text(
                text = stringResource(id = R.string.service),
                fontSize = 16.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 12.dp
                    )
            )

            bank.departments[0].services.forEach {service ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VTBTheme.backgroundColors.primary),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_people),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier
                            .width(12.dp)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {

                        Text(
                            text = service.name,
                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                            fontSize = 16.sp,
                            color = VTBTheme.textColors.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(
                            modifier = Modifier
                                .height(2.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.load),
                                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                                fontSize = 14.sp,
                                color = VTBTheme.textColors.secondary,
                            )

                            Text(
                                text = "${service.occupancy}%",
                                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                                fontSize = 14.sp,
                                color = VTBTheme.textColors.secondary,
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                        )

                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            progress = (service.occupancy.toDouble() / 100.0).toFloat(),
                            color = VTBTheme.backgroundColors.accent,
                            trackColor = VTBTheme.backgroundColors.secondary
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = VTBTheme.strokeColors.primary,
                thickness = (0.5).dp
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Text(
                text = stringResource(id = R.string.allow_area),
                fontSize = 16.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            if (bank.isForLowMobility) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_low_mobility),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier
                            .width(12.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.low_mobility_people),
                        fontSize = 18.sp,
                        fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                        color = VTBTheme.textColors.primary,
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
            } else {

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = VTBTheme.strokeColors.primary,
                thickness = (0.5).dp
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Text(
                text = stringResource(id = R.string.business_time),
                fontSize = 16.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.mn_fr),
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary,
                )

                Text(
                    text = "7:00 - 22:00",
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.primary,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.st_sn),
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary,
                )

                Text(
                    text = "8:00 - 20:00",
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.primary,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = VTBTheme.strokeColors.primary,
                thickness = (0.5).dp
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Text(
                text = stringResource(id = R.string.nearby_subway),
                fontSize = 16.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Text(
                text = "Площадь Революции",
                fontSize = 18.sp,
                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                color = VTBTheme.textColors.primary,
            )

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Арбатско-Покровская линия",
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary,
                )

                Text(
                    text = "245 м",
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.secondary,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )
        }
    }
}
