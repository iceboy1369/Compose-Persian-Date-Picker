package com.razaghimahdi.composepersiandatepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razaghimahdi.compose_persian_date.bottom_sheet.DatePickerLinearModalBottomSheet
import com.razaghimahdi.compose_persian_date.core.components.rememberDialogDatePicker
import com.razaghimahdi.compose_persian_date.dialog.PersianLinearDatePickerDialog
import com.razaghimahdi.composepersiandatepicker.ui.theme.ComposePersianDatePickerTheme
import kotlinx.coroutines.launch
import java.util.Date


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePersianDatePickerTheme {
                val coroutine = rememberCoroutineScope()
                val rememberPersianDialogDatePicker = rememberDialogDatePicker()
                val rememberPersianBottomSheetDatePickerController = rememberDialogDatePicker()
                var showDialog by remember { mutableStateOf(false) }
                val bottomSheetState = rememberModalBottomSheetState()

                LaunchedEffect(Unit) {
                    rememberPersianDialogDatePicker.updateMinYear(1415)
                    rememberPersianDialogDatePicker.updateMaxYear(1420)
                    rememberPersianDialogDatePicker.updateYearRange(2)
//                    rememberPersianDialogDatePicker.updateDate(timestamp = Date().time)
                    rememberPersianDialogDatePicker.updateDisplayMonthNames(false)

                    rememberPersianBottomSheetDatePickerController.updateDate(timestamp = Date().time)
                    rememberPersianBottomSheetDatePickerController.updateDisplayMonthNames (true)
//                rememberPersianBottomSheetDatePickerController.updateMaxYear(1420)
//                rememberPersianBottomSheetDatePickerController.updateMinYear(1390)
//                rememberPersianBottomSheetDatePickerController.updateYearRange(15)
                }

                if (showDialog) {
                    PersianLinearDatePickerDialog(
                        rememberPersianDialogDatePicker,
                        Modifier.fillMaxWidth(),
                        todayTitle = "today",
                        dismissTitle = "close",
                        submitTitle = "submit",
                        onDismissRequest = { showDialog = false },
                        dismissOnClickOutside = false,
                        onDateChanged = { year, month, day ->
                            // do something...
                            Log.i(
                                "TAG",
                                "onCreate getPersianFullDate: " + rememberPersianDialogDatePicker.getPersianFullDate()
                            )
                        })
                }

                if (bottomSheetState.isVisible) {
                    DatePickerLinearModalBottomSheet(
                        modifier = Modifier
                            .fillMaxSize(),
                        sheetState = bottomSheetState,
                        controller = rememberPersianBottomSheetDatePickerController,
                        onDismissRequest = {
                            coroutine.launch {
                                bottomSheetState.hide()
                            }
                        }
                    )
                }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Button(onClick = { showDialog = true }) {
                            Text(text = "نمایش دیالوگ")
                        }
                        Text(text =
                            rememberPersianDialogDatePicker.getPersianFullDate()
                        )

                        Spacer(modifier = Modifier.size(32.dp))

                        Button(onClick = { coroutine.launch { bottomSheetState.show() } }) {
                            Text(text = "نمایش باتم شت")
                        }
                        Text(text = rememberPersianBottomSheetDatePickerController.getPersianFullDate())
                    }

                }
            }
        }
    }
}