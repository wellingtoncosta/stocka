# todo-app-kotlin-multiplatform
A sample project to demonstrate Kotlin Multiplatform running on Android, iOS and backend.

### Building

To build this project, firstly you must run the `./gradlew build` task to build things and produce the binaries for Android and iOS from `:shared` module.

So, to build the iOS project, go to `ios` folder via terminal and run `pod install` to install the `shared.podspec` that contains definitions for binaries generated from `:shared` module, and other dependencies added in `Podfile`.

Then open `HashApp.xcworkspace` and build the project (Command + B).

After these steps you should be able to run both Android and iOS apps on emulator / device.
