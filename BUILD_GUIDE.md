# KineCut Clone - Build Guide

## Project Overview
This is a complete Kotlin/Jetpack Compose clone of **KineCut v3.9.6**, a professional video editing app with:
- AI-powered auto captions (Groq Whisper + Google Gemini)
- Background removal (ML Kit)
- Audio editing with MP3 encoding (lame)
- Viral effects (flight map, country map, newspaper)
- Script voice generation
- Video export with Media3

## Prerequisites

### Required Software
1. **Android Studio** (2024.1 or later)
   - Download: https://developer.android.com/studio
2. **Java Development Kit (JDK) 17+**
   - Included with Android Studio or download from Oracle
3. **Android SDK**
   - API level 35 (target), API level 24+ (minimum)
   - Installed automatically by Android Studio

### System Requirements
- **RAM**: 8GB+ recommended
- **Disk space**: 30GB+ for Android SDK, NDK, and build artifacts
- **OS**: Windows, macOS, or Linux

## Setup Instructions

### Step 1: Clone/Extract Project
```bash
# Option A: If you have the project folder
cd /path/to/KineCut

# Option B: Extract from workspace
cp -r /data/user/0/com.clozedcode.sun/files/workspace/KineCut ~/KineCut
cd ~/KineCut
```

### Step 2: Open in Android Studio
1. Launch **Android Studio**
2. Select **File → Open**
3. Navigate to the `KineCut` folder and click **OK**
4. Wait for Gradle sync to complete (this may take 5-10 minutes on first run)

### Step 3: Configure Firebase (Optional but Recommended)
1. Go to https://console.firebase.google.com
2. Create a new project named "KineCut"
3. Register your Android app with package name `com.kinecut.app`
4. Download the `google-services.json` file
5. Copy it to `app/google-services.json` in your project
6. Rebuild the project

### Step 4: Configure API Keys
The app uses two optional AI services. Add them in Settings:

**Groq Transcription:**
- Get free API key: https://console.groq.com/keys
- Max 100k tokens/month free tier

**Google Gemini:**
- Get free API key: https://aistudio.google.com/app/apikey
- Max 60 calls/minute on free tier

### Step 5: Build the APK

#### Option A: Debug APK (Recommended for Testing)
```bash
# Using Android Studio:
# 1. Click Build → Build Bundle(s) / APK(s) → Build APK(s)
# OR use terminal:
./gradlew assembleDebug

# APK will be at: app/build/outputs/apk/debug/app-debug.apk
```

#### Option B: Release APK (For Production/Signing)
```bash
# First create a signing key (one-time):
keytool -genkey -v -keystore ~/kinecut-release-key.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias kinecut-key

# Build release APK:
./gradlew assembleRelease

# APK will be at: app/build/outputs/apk/release/app-release.apk
```

#### Option C: Using Android Studio UI
1. Click **Build → Build Bundles/APK → Build APK**
2. Wait for build to complete (5-15 minutes depending on hardware)
3. A notification will show the output path
4. Click **Locate** to open the output folder

## Installation

### Install to Device/Emulator
```bash
# Via Android Studio:
# 1. Connect device or open emulator
# 2. Click Run → Run 'app'

# OR via terminal:
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Install to Real Device
1. Enable **Developer Mode**: Settings → About Phone → tap Build Number 7 times
2. Enable **USB Debugging**: Settings → Developer Options → USB Debugging
3. Connect device via USB
4. Run: `adb install app/build/outputs/apk/debug/app-debug.apk`

## Project Structure

```
KineCut/
├── app/
│   ├── src/main/
│   │   ├── java/com/kinecut/app/
│   │   │   ├── MainActivity.kt                 # Main entry point
│   │   │   ├── KineCutApplication.kt           # App class
│   │   │   ├── data/
│   │   │   │   ├── api/                        # API services (Groq, Gemini)
│   │   │   │   ├── model/                      # Data models
│   │   │   │   └── repository/                 # Data access
│   │   │   ├── engine/                         # Export engine
│   │   │   ├── export/                         # Export service
│   │   │   ├── ui/
│   │   │   │   ├── editor/                     # Main editor
│   │   │   │   ├── captions/                   # Auto captions
│   │   │   │   ├── bgremover/                  # Background removal
│   │   │   │   ├── audioeditor/                # Audio editor
│   │   │   │   ├── landing/                    # Home screen
│   │   │   │   └── theme/                      # Theme & colors
│   │   │   ├── scriptvoice/                    # Voice generation
│   │   │   ├── speech/                         # Speech synthesis
│   │   │   └── viraleffects/                   # Effects (maps, filters)
│   │   ├── res/
│   │   │   ├── values/                         # Strings, colors, themes
│   │   │   ├── layout/                         # XML layouts
│   │   │   ├── mipmap/                         # App icons
│   │   │   ├── drawable/                       # Vector drawables
│   │   │   └── xml/                            # Config files
│   │   └── assets/
│   │       └── shaders/                        # GLSL shaders
│   ├── build.gradle.kts                        # App-level build config
│   ├── proguard-rules.pro                      # Proguard rules
│   └── google-services.json                    # Firebase config
├── build.gradle.kts                            # Project-level build config
├── settings.gradle.kts                         # Gradle settings
└── gradle/wrapper/                             # Gradle wrapper
```

## Key Dependencies

- **Kotlin**: 2.0.0
- **Jetpack Compose**: 1.6.0 (UI framework)
- **Media3**: 1.3.1 (Video playback & export)
- **Firebase**: Analytics, Crashlytics, Remote Config
- **ML Kit**: Subject Segmentation (for BG removal)
- **OkHttp**: HTTP client
- **Retrofit**: REST client
- **Gson**: JSON serialization
- **Google Generative AI**: Gemini API

## Troubleshooting

### Build Fails: "SDK not found"
```bash
# Download SDK components:
# Android Studio → Tools → SDK Manager
# Install: API 35, Build Tools 35.x
```

### Build Fails: "Gradle sync failed"
```bash
# Clean and rebuild:
./gradlew clean
./gradlew build
```

### APK Won't Install: "App not installed"
```bash
# Check device compatibility:
adb shell getprop ro.build.version.release    # Check Android version (need 6.0+)
adb shell getprop ro.product.cpu.abilist      # Check CPU (need arm64-v8a)

# Uninstall old version first:
adb uninstall com.kinecut.app
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Native Library Error: "libkinecut_mp3lame.so not found"
This is expected on non-ARM64 devices. The app will still run but MP3 encoding won't work.
The native library needs to be built separately from the original source or extracted from the original APK.

## Native Libraries (Optional)

The original APK includes compiled native libraries for audio/video processing:
- `libkinecut_mp3lame.so` - MP3 encoding
- `libsherpa-onnx-jni.so` - Speech recognition
- `libonnxruntime.so` - ML inference
- `libxeno_native.so` - Video processing

These must be either:
1. Built from source (requires NDK setup)
2. Extracted from the original APK and added to `app/src/main/jniLibs/arm64-v8a/`

## Running Tests

```bash
# Unit tests:
./gradlew test

# Instrumented tests (on device/emulator):
./gradlew connectedAndroidTest
```

## Signing for Google Play

1. Create a signing keystore:
```bash
keytool -genkey -v -keystore ~/kinecut.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias kinecut
```

2. Update `build.gradle.kts`:
```kotlin
signingConfigs {
    create("release") {
        storeFile = file("/path/to/kinecut.jks")
        storePassword = "your-password"
        keyAlias = "kinecut"
        keyPassword = "your-password"
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.getByName("release")
    }
}
```

3. Build:
```bash
./gradlew bundleRelease
```

4. Upload to Google Play Console

## Performance Tips

- Use **Release builds** for testing (much faster)
- Enable **Instant Run** for quicker iterations
- Use **Gradle daemon**: `org.gradle.daemon=true` in `gradle.properties`
- Allocate more RAM: `org.gradle.jvmargs=-Xmx4g` in `gradle.properties`

## Additional Resources

- Kotlin Docs: https://kotlinlang.org/docs/
- Jetpack Compose: https://developer.android.com/jetpack/compose
- Android Development: https://developer.android.com/
- Gradle Documentation: https://gradle.org/guides/

## License & Attribution

This is a reverse-engineered clone for educational purposes.
Original app: KineCut by Pair IP
