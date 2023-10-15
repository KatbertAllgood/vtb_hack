package com.example.vtb_hackathon.ui.screen.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.notification.VTBNotification
import com.example.vtb_hackathon.ui.theme.VTBTheme

@Preview
@Composable
private fun LoginScreenPreview(){
    LoginScreen(
        navigateToCode = { }
    )
}

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    viewModel: LoginVM = viewModel(),
    navigateToCode: () -> Unit
) {

    val context = LocalContext.current

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
                text = stringResource(R.string.type_phone),
                fontSize = 20.sp,
                fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                color = VTBTheme.textColors.primary,
                modifier = Modifier
                    .padding(
                        top = 12.dp
                    )
            )

            PhoneField(
                viewModel.phoneNumber,
                mask = "+7 (000) 000-00-00",
                maskNumber = '0',
                onPhoneChanged = { viewModel.updatePhoneNumber(it) })
            
            val enabled = remember {
                derivedStateOf {
                    (viewModel.phoneNumber == "") || (viewModel.phoneNumber.length != 10)
                }
            }

            Button(
                onClick = {
                    viewModel.updatePhoneNumberPrefs()

                    notifyCode(context)

                    navigateToCode()
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
                    text = stringResource(id = R.string.receive_code),
                    fontSize = 16.sp,
                    fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                    color = VTBTheme.textColors.contrast
                )
            }

            Text(
                text = stringResource(R.string.become_client),
                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                color = VTBTheme.textColors.accent,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(
                        top = 24.dp
                    )
                    .clickable {

                    }
            )
        }
    }
}

private fun notifyCode(context: Context) {
    val notification = VTBNotification(context, context.getString(R.string.app_name), context.getString(R.string.code_message))
    notification.sendNotification()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneField(
    phoneNumber: String,
    mask: String = "000 000-00-00",
    maskNumber: Char = '0',
    onPhoneChanged: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
            .height(60.dp),
        value = phoneNumber,
        onValueChange = { it ->
            onPhoneChanged(it.take(mask.count { it == maskNumber }))
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = VTBTheme.textColors.primary,
            unfocusedTextColor = VTBTheme.textColors.primary,
            containerColor = VTBTheme.backgroundColors.primary,
            focusedLabelColor = VTBTheme.textColors.secondary,
            unfocusedLabelColor = VTBTheme.textColors.secondary,
            cursorColor = VTBTheme.textColors.accent,
            focusedBorderColor = VTBTheme.strokeColors.primary,
            unfocusedBorderColor = VTBTheme.strokeColors.primary,
        ),
        label = {
            Text(
                text = stringResource(id = R.string.type_phone_label),
                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                fontSize = 16.sp
            )
        },
        shape = VTBTheme.shape.cornersStyle,
        textStyle = TextStyle(
            fontFamily = VTBTheme.typography.robotoRegular.fontFamily
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
    )
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}
