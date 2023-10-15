package com.example.vtb_hackathon.ui.screen.map.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.ui.theme.VTBTheme

@Composable
fun NavigatorItem(
    distance: String,
    time: String,
    doClose: () -> Unit
) {

    VTBTheme {
        Card(
            shape = VTBTheme.shape.cornersStyle,
            colors = CardDefaults.cardColors(
                containerColor = VTBTheme.backgroundColors.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(
                    modifier = Modifier
                        .width(20.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = time,
                        fontSize = 20.sp,
                        fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                        color = VTBTheme.textColors.primary
                    )
                    Spacer(
                        modifier = Modifier
                            .width(24.dp)
                    )
                    Text(
                        text = distance,
                        fontSize = 20.sp,
                        fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                        color = VTBTheme.textColors.secondary
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                )

                Image(
                    painter = painterResource( id = R.drawable.ic_cancel),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            doClose()
                        }
                )

                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                )
            }
        }
    }
}