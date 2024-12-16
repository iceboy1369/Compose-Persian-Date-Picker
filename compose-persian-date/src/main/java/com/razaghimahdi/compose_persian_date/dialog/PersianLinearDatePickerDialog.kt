package com.razaghimahdi.compose_persian_date.dialog

import androidx.annotation.FontRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.razaghimahdi.compose_persian_date.linear_date_picker.LinearPersianDatePicker
import com.razaghimahdi.compose_persian_date.core.components.NoPaddingAlertDialog
import com.razaghimahdi.compose_persian_date.core.components.PersianDatePickerController
import com.razaghimahdi.compose_persian_date.core.components.rememberDialogDatePicker
import java.util.Date

@Composable
fun PersianLinearDatePickerDialog(
    controller: PersianDatePickerController,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onDateChanged: ((year: Int, month: Int, day: Int) -> Unit)? = null,
    submitTitle: String = "تایید",
    dismissTitle: String = "بستن",
    todayTitle: String = "امروز",
    textButtonStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    unSelectedTextStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
    @FontRes font: Int? = null,
    dismissOnClickOutside: Boolean = true
) {

    var dateIsSet by remember { mutableStateOf(false) }
    val controller0 = rememberDialogDatePicker()

    if (!dateIsSet){
        controller0.updateDisplayMonthNames(false)
        controller0.updateDate(controller.getPersianYear(), controller.getPersianMonth(), controller.getPersianDay())
        dateIsSet = true
    }


    NoPaddingAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        properties = DialogProperties(dismissOnClickOutside = dismissOnClickOutside),
        title = { },
        text = {
            LinearPersianDatePicker(
                modifier = modifier.padding(8.dp),
                controller = controller0,
                contentColor = contentColor,
                selectedTextStyle = selectedTextStyle,
                unSelectedTextStyle = unSelectedTextStyle,
                onDateChanged = null,
                font = font,
            )
        },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {
                    controller0.updateDate(Date().time)
                }) {
                    Text(text = todayTitle, style = textButtonStyle, color = contentColor)
                }
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        controller.updateDate(
                            persianYear = controller0.getPersianYear(),
                            persianMonth = controller0.getPersianMonth(),
                            persianDay = controller0.getPersianDay()
                        )
                        if (onDateChanged != null) {
                            onDateChanged(
                                controller.getPersianYear(),
                                controller.getPersianMonth(),
                                controller.getPersianDay()
                            )
                        }
                        onDismissRequest()
                    }) {
                        Text(text = submitTitle, style = textButtonStyle, color = contentColor)
                    }

                    TextButton(onClick = {
                        onDismissRequest()
                    }) {
                        Text(text = dismissTitle, style = textButtonStyle, color = contentColor)
                    }
                }
            }
        },
    )
}