package com.example.vtb_hackathon.ui.screen.map.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.entity.BankOfficePresentation
import com.example.vtb_hackathon.ui.theme.VTBTheme


@Preview
@Composable
private fun DepartmentItemPreview() {

}

@Composable
fun DepartmentItem(
    bankOffice: BankOfficePresentation,
    onClick: () -> Unit
) {
    VTBTheme {

        Card(
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
            shape = RoundedCornerShape(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VTBTheme.backgroundColors.primary)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 6.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_logo_for_department_item),
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
                        text = bankOffice.name,
                        fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                        fontSize = 16.sp,
                        color = VTBTheme.textColors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                    )

                    Text(
                        text = bankOffice.address,
                        fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                        fontSize = 14.sp,
                        color = VTBTheme.textColors.secondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )

                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        progress = (bankOffice.departments[0].services[0].occupancy.toDouble() / 100.0).toFloat(),
                        color = VTBTheme.backgroundColors.accent,
                        trackColor = VTBTheme.backgroundColors.secondary
                    )
                }
            }
        }
    }
}
