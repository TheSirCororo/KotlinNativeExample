# KotlinNativeExample
This is example for coding with Kotlin/Native, linking C libraries and comparsion java.net.* with libcurl

## Running
### Running mingw64 (Windows x64) target
1) Run `gradlew runDebugExecutableMingw`
2) Stop task after getting error
3) Copy `libcurl_win/bin/libcurl-x64.dll` and `cacert.pem` to run directory (this is `build/bin/mingw/debugExecutable` by default for mingw target)
4) Again run `gradlew runDebugExecutableMingw`
#### Change checking URL
1) Go to `build.gradle.kts`
2) Find lines 
```kt
binaries {
    executable {
        runTask.let { task ->
            task?.args("https://example.org/")
        }
        entryPoint = "main"
    }
}
```
3) Change `task?.args("https://example.org/")` to `task?.args("[YOUR CHEKING URL]")`
4) Run
### Running JVM target
1) Run `gradlew jvmJar`
2) Go to `build/libs` directory
3) Open terminal and run `java -jar KotlinNativeApp <url>`
## Used Libraries
1) [libcurl for Windows](https://curl.se/windows/) for mingw and linux (to-do) target
2) java.net.* for JVM target
## TO-DO
- [ ] Add linux target support
- [ ] Add macos target support
