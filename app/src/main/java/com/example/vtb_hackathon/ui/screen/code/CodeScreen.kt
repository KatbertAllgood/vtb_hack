package com.example.vtb_hackathon.ui.screen.code

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.ui.screen.login.LoginVM
import com.example.vtb_hackathon.ui.screen.login.PhoneVisualTransformation
import com.example.vtb_hackathon.ui.theme.VTBTheme
import kotlinx.coroutines.delay

@Preview
@Composable
private fun CodeScreenPreview() {
    CodeScreen(
        navigateToMap = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeScreen(
    viewModel: CodeScreenVM = viewModel(),
    navigateToMap: () -> Unit
) {

    viewModel.getPhoneNumberPrefs()

    VTBTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(VTBTheme.backgroundColors.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_phone),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        top = 32.dp
                    )
            )
            Text(
                text = stringResource(R.string.confirm_phone),
                fontSize = 20.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
                modifier = Modifier
                    .padding(
                        top = 12.dp
                    )
            )

            Spacer(
                modifier = Modifier
                    .padding(
                        top = 24.dp
                    )
            )



            MultiStyleText(
                text1 = stringResource(id = R.string.code_hint),
                color1 = VTBTheme.textColors.primary,
                text2 = viewModel.phoneNumberLiveData.observeAsState().value ?: "",
                color2 = VTBTheme.textColors.accent
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val focusManager = LocalFocusManager.current

                var isError by rememberSaveable { mutableStateOf(false) }

                for (i in 1..6) {

                    OutlinedTextField(
                        value = when(i) {
                            1 -> viewModel.firstDigit
                            2 -> viewModel.secondDigit
                            3 -> viewModel.thirdDigit
                            4 -> viewModel.fourthDigit
                            5 -> viewModel.fifthDigit
                            6 -> viewModel.sixthDigit
                            else -> ""
                                        },
                        onValueChange = { newText ->

                            if (newText.length <= 1) viewModel.updateDigit(i, newText)

                            focusManager.moveFocus(FocusDirection.Next)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedTextColor = VTBTheme.textColors.primary,
                            focusedTextColor = VTBTheme.textColors.primary,
                            containerColor = VTBTheme.backgroundColors.primary,
                            focusedLabelColor = VTBTheme.textColors.secondary,
                            unfocusedLabelColor = VTBTheme.textColors.secondary,
                            cursorColor = VTBTheme.textColors.accent,
                            focusedBorderColor = VTBTheme.strokeColors.primary,
                            unfocusedBorderColor = VTBTheme.strokeColors.primary,
                            errorBorderColor = VTBTheme.strokeColors.negative
                        ),
                        isError = isError,
                        shape = VTBTheme.shape.cornersStyle,
                        textStyle = TextStyle(
                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                            color = VTBTheme.textColors.primary,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions {
                            focusManager.moveFocus(FocusDirection.Next)
                        },
                        modifier = Modifier
                            .width(50.dp)
                    )
                }
            }



            val enabled = remember {
                derivedStateOf {
                    (viewModel.firstDigit == "") || (viewModel.secondDigit == "") ||
                            (viewModel.thirdDigit == "") || (viewModel.fourthDigit == "") ||
                            (viewModel.fifthDigit == "") || (viewModel.sixthDigit == "")
                }
            }

            Button(
                onClick = {
//                          if(
//                              (viewModel.firstDigit != "7") || (viewModel.secondDigit != "4") ||
//                              (viewModel.thirdDigit != "6") || (viewModel.fourthDigit != "1") ||
//                              (viewModel.fifthDigit != "2") || (viewModel.sixthDigit != "4")
//                          ) {
//
//                          } else {
//                              viewModel.isErrorUpdate(true)
//                          }

                          navigateToMap()
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
                    .height(50.dp)
                    .padding(
                        horizontal = 16.dp
                    ),
                enabled = !enabled.value
            ) {
                Text(
                    text = stringResource(id = R.string.sing_in),
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.contrast
                )
            }
        }
    }
}

@Composable
fun BasicCountdownTimer() {
    var timeLeft by remember { mutableStateOf(60) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    Text(text = "Time left: $timeLeft")
}

@Composable
private fun MultiStyleText(text1: String, color1: Color, text2: String, color2: Color) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = color1,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    fontSize = 14.sp,
                )
            ) {
                append(text1)
            }
            withStyle(
                style = SpanStyle(
                    color = color2,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    fontSize = 14.sp,
                )
            ) {
                append(text2)
            }
        }
    )
}