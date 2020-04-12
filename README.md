# todo-app-kotlin-multiplatform
A sample project to demonstrate Kotlin Multiplatform running on Android, iOS and backend.

### Building

To build this project, firstly you must run the `./gradlew build` task to build things and produce the binaries for Android and iOS from `:shared` module.

Then, go to ios folder in terminal and run `pod install` command to refresh Pods, open `TodoApp.xcworkspace` and build the project.

After these steps you can run both Android and iOS apps on emulator / device.

__Disclaimer: if you run the `./gradlew podspec` to generate a new `.podspec` file, don't forget to edit the value of `spec.libraries` to be equals to `"c++", 'sqlite3'`. Otherwise you'll probably get some stranger errors in Xcode build due `sqlite3` binary linker missings.__
