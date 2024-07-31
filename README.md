# Android Toast Helper

Toast Helper is a library designed to facilitate the use of Toast notifications while hiding Android dependencies.

## Necessity

Although Toasts are frequently used in many apps and screens, they can be challenging to use in layers that aim to
exclude framework dependencies, such as MVVM's ViewModel.

For instance, after adding or deleting data in an app, you might use a Toast to notify the user. Typically, to achieve
this in a ViewModel, you would define a state or event for the Toast execution and observe it in the UI to handle.
However, event-based implementations like SharedFlow risk losing events, and state-based implementations like StateFlow
can lead to unintended duplicate processing during reconstructions, such as screen rotations. Therefore, additional
measures are needed to prevent these issues.

The purpose of this library is to simplify the cumbersome process of executing Toasts.

## Install

```kotlin
dependencyResolutionManagement {
    ..
    repositories {
        ..
        maven("https://maven.pkg.github.com/dylan-kwon/android-toast-helper") {
            credentials {
                username = INPUT_YOUR_USER_NAME
                password = INPUT_YOUR_GITHUB_TOKEN
            }
        }
    }
}
```

### Without Framework Dependency

It is a core module without Android dependencies.

```kotlin
implementation("dylan.kwon:toast-helper-core:$version")
```

### With Android Dependency

It includes an implementation of the core module for actual Toast execution.

```kotlin
implementation("dylan.kwon:toast-helper-android:$version")
```

### Testing

It includes rules and test components that can be used in testing.

```kotlin
testImplementation("dylan.kwon:toast-helper-test:$version")
```

## How To Use

### Initialize

ToastHelper is automatically initialized at startup in an Android environment.

However, if you do not want it to be initialized automatically for any reason, you can configure it as follows:

#### manifest.xml

Exclude AndroidToastHelperInitializer during startup initialization.

```xml

<provider
    android:name="androidx.startup.InitializationProvider"
    android:authorities="${applicationId}.androidx-startup"
    android:exported="false"
    tools:node="merge">
    <meta-data
        android:name="dylan.kwon.toasthelper.android.startup.AndroidToastHelperInitializer"
        android:value="androidx.startup"
        tools:node="remove"/>
</provider>

```

#### Call the setFactory function

```kotlin
ToastHelper.setFactory(
    AndroidToastFactory(applicationContext)
)
```

> In addition to the AndroidToastFactory provided by the library, you can create and set custom objects. For more
> information on customization, please refer [here](.#Custom).

### Basic

The basic usage is as follows:

```kotlin
class YourViewModel : ViewModel() {
    ..
    fun somethings() {
        // only message.
        ToastHelper.show("message")

        // message with duration.
        ToastHelper.show("message", ToastDuration.SHORT)
    }
}
```

### Cancel

The displayed Toast can be canceled immediately.

```kotlin
class YourViewModel : ViewModel() {
    ..
    fun somethings() {
        val cancellation = ToastHelper.show("message")

        // To cancel a Toast, call the following.
        cancellation.cancel()
    }
}
```

### Listener

You can attach listeners to the appearance and disappearance of the Toast.
The creation and execution of a Toast consist of two steps.

```Kotlin
class YourViewModel : ViewModel() {
    ..
    fun somethings() {
        // 1. Create Toast.
        val toast = ToastHelper.create(message).apply {
            addShownCallback {
                // Toast is shown.
            }
            addHiddenCallback {
                // Toast is Hidden. 
            }
        }

        // 2. Show Toast.
        toast.show()
    }
}
```

### Position And Margin

It is possible to specify the position and margin of the Toast.

```kotlin
class YourViewModel : ViewModel() {
    ..
    fun somethings() {
        ToastHelper.show(
            message = "message",
            duration = ToastDuration.SHORT,

            // set position.
            position = ToastPosition(
                gravity = ToastGravity.CLIP_VERTICAL,
                xOffset = 0,
                yOffset = 0
            ),

            // set margin.
            margin = ToastMargin(
                horizontalFraction = 0.5f,  // 0.5 is 50% of screen width.
                verticalFraction = 0.5f     // 0.5 is 50% of screen height.
            )
        )
    }
}
```

> **Warning**
> This feature is not supported on Android-R or higher.

### Testing

For testing, the following ToastHelperTestRule is provided.

This rule configures the ToastHelper to use println instead
of Android Toast in the test environment.

```kotlin
class MyTest {
    @get:Rule
    val rule = ToastHelperTestRule()

    // or
    val rule = ToastHelperTestRule(YourCustomFactory)

    ..

    @Test
    fun test() {
        viewModel.somthings()
    }
}
```

> **Output**
> [TOAST][SHORT] Hello World

### Custom

Some people might want to modify the behavior of ToastHelper.

For example, in a test environment, it uses println instead of Toast to output in the following format:

```kotlin
println("[TOAST][$durationName] $toastMessage")
```

> **Output**
> [TOAST][SHORT] Hello World

If you want to output only the Toast message, configure it as follows:

#### 1. Extend the Toast class to create a custom Toast object.

```kotlin
class YourCustomToast(
    override val message: String,
) : Toast() {

    override fun show(): ToastCancellation {
        println(message)
        return EmptyToastCancellation
    }
}
```

#### 2. Extend the ToastFactory interface to create a custom factory.

```kotlin
class YourCustomToastFactory : ToastFactory {
    override fun create(
        message: String,
        duration: ToastDuration,
        position: ToastPosition?,
        margin: ToastMargin?
    ): Toast {
        return YourCustomToast(message)
    }
}
```

#### 3. Set the custom factory in ToastHelper.

```kotlin
ToastHelper.setFactory(yourCustomFactory)
```

## License

This project is licensed under the Apache License, Version 2.0. - see the [LICENSE](app/LICENSE.txt)
file for details.