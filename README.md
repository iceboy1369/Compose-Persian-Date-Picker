# Compose Persian Date Picker

`Compose Persian Date Picker` is a library that allows developers to add a Persian date picker to their Jetpack Compose apps. 
The library provides a customizable dialog that lets users select a date using the Persian calendar,
with options for updating the selected date and other settings.


[![](https://jitpack.io/v/razaghimahdi/Compose-Persian-Date-Picker.svg)](https://jitpack.io/#razaghimahdi/Compose-Persian-Date-Picker)


## Quickstart

Here's a quick example of how to use the library:

1. Add the JitPack repository to your project-level build.gradle or settings.gradle file:

```groovy
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the library dependency to your app-level build.gradle file:

```groovy
    dependencies {
            implementation 'com.github.razaghimahdi:Compose-Persian-Date-Picker:1.0.0'
    } 
```

3. Use the PersianDataPickerDialog in your app:

```kotlin

val rememberPersianDataPicker = rememberPersianDataPicker()
val showDialog = remember { mutableStateOf(false) }

if (showDialog.value) {
    PersianDataPickerDialog(
        rememberPersianDataPicker,
        Modifier.fillMaxWidth(),
        onDismissRequest = { showDialog.value = false },
        onDateChanged = { year, month, day ->
            // do something...
        })
}


Button(onClick = { showDialog.value = true }) {
    Text(text = "نمایش")
}
```

4. Customize the settings by calling methods on the rememberPersianDataPicker object:

```Kotlin
val rememberPersianDataPicker = rememberPersianDataPicker()

// 3 ways to update date
rememberPersianDataPicker.updateDate(date=Date())
rememberPersianDataPicker.updateDate(timestamp = Date().time)
rememberPersianDataPicker.updateDate(persianYear = 1401, persianMonth = 12, persianDay = 20)

rememberPersianDataPicker.updateSelectedYear(1400)
rememberPersianDataPicker.updateSelectedDay(10)
rememberPersianDataPicker.updateSelectedMonth(5)

rememberPersianDataPicker.updateMaxYear(1420)
rememberPersianDataPicker.updateMinYear(1350)

rememberPersianDataPicker.updateYearRange(10)
rememberPersianDataPicker.updateDisplayMonthNames(true)

PersianDataPickerDialog(
    rememberPersianDataPicker,
    Modifier.fillMaxWidth(),
    onDismissRequest = { showDialog.value = false },
    onDateChanged = { year, month, day ->
        // do something...
    }
)
```
## Example
For a more detailed example, check out the [example app](https://github.com/razaghimahdi/Compose-Persian-Date-Picker/blob/main/app/src/main/java/mahdidev/composepersiandatepickerexample/MainActivity.kt) included in the repository.

## Screenshots
https://user-images.githubusercontent.com/61207818/220583893-ffcb39e2-5f34-4141-a81d-ddfb0b7339cf.mp4

## Extra Stuff

This library also includes a `NumberPicker` component that developers can use to add a number picker to their Jetpack Compose apps. 
The `NumberPicker` component is complementary to the `PersianDataPickerDialog` and provides a way to easily select a number value in the app.

```Kotlin

var selectedValue by remember { mutableStateOf(0) }

NumberPicker(
    value = selectedValue,
    range = 0..10,
    onValueChange = {
        selectedValue = it
    }
)


```
## Contributing
Contributions are welcome! If you find a bug or would like to create a new feature, please submit a pull request.

## License
This library is licensed under the MIT License. See [LICENSE.txt](https://github.com/razaghimahdi/Compose-Persian-Date)

Developed by Mahdi Razzaghi Ghaleh
