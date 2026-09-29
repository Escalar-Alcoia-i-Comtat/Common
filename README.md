# Escalar Alcoià i Comtat · Common

Kotlin code shared by the [app](https://codeberg.org/Escalar-Alcoia-i-Comtat/App) and the
[backend](https://github.com/Escalar-Alcoia-i-Comtat/BackendKotlin) of Escalar Alcoià i Comtat.

Anything both projects need to agree on lives here, instead of being copied into each of them. Copies drift apart
when one side is updated and the other isn't; with a single definition, a change reaches both projects at once.

## How it's used

Both projects include this repository as a git submodule in a `common` directory, and compile its sources as part of
their own code:

```kotlin
// App (composeApp/build.gradle.kts)
kotlin.sourceSets.commonMain { kotlin.srcDir(rootProject.file("common/src/commonMain/kotlin")) }

// Backend (build.gradle.kts)
kotlin.sourceSets.main { kotlin.srcDir("common/src/commonMain/kotlin") }
```

The sources are compiled by each project, rather than published as a library, because the app is multiplatform
(Android, iOS, desktop and web) and each project uses its own Kotlin version.

After cloning the app or the backend, fetch the submodule with:

```shell
git submodule update --init
```

To use a newer version of this repository in one of the projects:

```shell
cd common
git pull origin master
cd ..
git add common
git commit -m "Update common"
```

## Rules for the code here

- **Multiplatform only.** The code must work on every platform of both projects: the JVM, Android, iOS and the web.
  This project builds for all of them to make sure (`./gradlew build`).
- **No new dependencies.** Code may only use the Kotlin standard library and `kotlinx.serialization`, which both projects
  already have. A new dependency must be added to both projects first.
- **Package:** `org.escalaralcoiaicomtat.common`.
- **Compatibility:** the backend and the app are released separately, and old app versions stay in use. Changes to
  serialized classes must stay backwards compatible, for example by adding fields with default values.

## Building

```shell
./gradlew build     # Compiles for all targets, and runs the tests
./gradlew jvmTest   # Runs the tests on the JVM only
```

The Gradle daemon runs on JDK 21.

## License

[GNU General Public License v3.0](LICENSE)
