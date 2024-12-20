package com.razaghimahdi.compose_persian_date.bottom_sheet

import androidx.annotation.FontRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.razaghimahdi.compose_persian_date.core.components.PersianDatePickerController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerLinearModalBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    controller: PersianDatePickerController,
    onDismissRequest: () -> Unit,
    onDateChanged: ((year: Int, month: Int, day: Int) -> Unit)? = null,
    submitTitle: String = "تایید",
    todayTitle: String = "امروز",
    textButtonStyle: TextStyle = LocalTextStyle.current,
    unSelectedTextStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
    @FontRes font: Int? = null,
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    windowInsets: WindowInsets = BottomSheetDefaults.windowInsets,
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
) {

    val coroutine = rememberCoroutineScope()

    ModalBottomSheet(
        content = {
            LinearDatePickerBottomSheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onDateChanged = onDateChanged,
                contentColor = contentColor,
                submitTitle = submitTitle,
                todayTitle = todayTitle,
                controller = controller,
                textButtonStyle = textButtonStyle,
                unSelectedTextStyle = unSelectedTextStyle,
                selectedTextStyle = selectedTextStyle,
                font = font,
                onDismissRequest = {
                    coroutine.launch {
                        sheetState.hide()
                    }
                }
            )
        },
        sheetMaxWidth = sheetMaxWidth,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = shape,
        containerColor = containerColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        contentWindowInsets = { windowInsets },
        properties = properties,
     )

}