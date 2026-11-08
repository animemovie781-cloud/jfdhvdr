# KineCut Clone - Transfer & Build Instructions

## 📍 Project Location

```
/data/user/0/com.clozedcode.sun/files/workspace/KineCut/
```

**Total Size**: ~1.0 MB (source code)
**After Build**: ~100-200 MB (with dependencies)

---

## 🔄 How to Transfer Project to Your Computer

### Option A: USB Transfer (Fastest)
```bash
# Connect Android device to computer via USB
# On device: Enable USB file transfer mode
# On computer:
adb pull /data/user/0/com.clozedcode.sun/files/workspace/KineCut ~/Downloads/KineCut
```

### Option B: Cloud Storage
1. Compress project: `tar czf KineCut.tar.gz /data/user/0/com.clozedcode.sun/files/workspace/KineCut/`
2. Upload to Google Drive, Dropbox, or OneDrive
3. Download on computer
4. Extract: `tar xzf KineCut.tar.gz`

### Option C: ADB Export
```bash
# Via terminal on this device
cd /data/user/0/com.clozedcode.sun/files/workspace/KineCut
find . -type f > file_list.txt

# Then use ADB to pull each file
adb pull /data/user/0/com.clozedcode.sun/files/workspace/KineCut ~/KineCut
```

### Option D: Manual File-by-File (via Android Studio)
1. Open Android Studio on this device (if available)
2. Create new project
3. Copy source files manually
4. Update gradle files

---

## 💻 Building on Your Computer

### System Requirements

**Windows 10/11:**
- RAM: 8GB+ (16GB recommended)
- Disk: 30GB+ SSD
- Java: JDK 17+ (included with Android Studio)

**macOS 12+:**
- RAM: 8GB+ (16GB recommended)
- Disk: 30GB+ SSD
- Java: JDK 17+ (included with Android Studio)

**Linux (Ubuntu 20.04+):**
- RAM: 8GB+ (16GB recommended)
- Disk: 30GB+ SSD
- Java: JDK 17 (`sudo apt-get install openjdk-17-jdk`)

### Installation Steps

#### 1. Install Android Studio
```bash
# Download from: https://developer.android.com/studio
# Then install following on-screen instructions
```

#### 2. Install Android SDK
```
Android Studio → Tools → SDK Manager
→ Install API 35, Build Tools 35.x
→ Install NDK (optional, for native libs)
```

#### 3. Create Signing Key (Optional, for Release)
```bash
keytool -genkey -v -keystore ~/kinecut-key.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias kinecut-key
# Enter password and info when prompted
```

#### 4. Open Project
```
Android Studio → File → Open
→ Select KineCut folder
→ Wait for Gradle sync (5-10 minutes)
```

### Building APK

#### Debug APK (for Testing)
```bash
# Via Android Studio GUI:
Build → Build Bundle(s)/APK(s) → Build APK(s)

# Or via terminal:
cd ~/KineCut
./gradlew assembleDebug

# Output: app/build/outputs/apk/debug/app-debug.apk
```

#### Release APK (for Distribution)
```bash
# Edit: app/build.gradle.kts
# Add signing config:
signingConfigs {
    create("release") {
        storeFile = file("~/kinecut-key.jks")
        storePassword = "your-password"
        keyAlias = "kinecut-key"
        keyPassword = "your-password"
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.getByName("release")
    }
}

# Then build:
./gradlew assembleRelease

# Output: app/build/outputs/apk/release/app-release.apk
```

### Install to Device

```bash
# Connect Android device via USB
# Enable USB Debugging (Settings → Developer Options → USB Debugging)

# Then:
adb install app/build/outputs/apk/debug/app-debug.apk

# Or via Android Studio:
Run → Run 'app' (with device connected)
```

---

## 🔧 Configuration

### Firebase Setup (Optional)

1. Go to https://console.firebase.google.com
2. Create new project named "KineCut"
3. Click "Create app" → Android
4. Enter package name: `com.kinecut.app`
5. Download `google-services.json`
6. Copy to: `app/google-services.json`
7. Rebuild project

### API Keys Setup (Optional)

After app installation, go to Settings and add:

**Groq API Key:**
1. Visit https://console.groq.com/keys
2. Create API key
3. Copy the key
4. In app: Settings → Add Groq Key → Paste

**Gemini API Key:**
1. Visit https://aistudio.google.com/app/apikey
2. Create API key
3. Copy the key
4. In app: Settings → Add Gemini Key → Paste

---

## 📋 Build Troubleshooting

### "SDK not found"
```bash
# Open SDK Manager and install:
# - API Level 35
# - Build Tools 35.x
# - Android SDK Platform
```

### "Gradle Sync Failed"
```bash
# Try:
./gradlew clean
./gradlew sync

# Or delete cache:
rm -rf ~/.gradle
./gradlew sync
```

### "Gradle Daemon Timeout"
```bash
# Edit: gradle.properties (create if doesn't exist)
org.gradle.jvmargs=-Xmx4g
org.gradle.daemon=true

# Restart Android Studio
```

### "Insufficient Memory"
```bash
# Allocate more RAM to Gradle:
# gradle.properties:
org.gradle.jvmargs=-Xmx6g

# Or close other apps while building
```

### "APK Won't Install"
```bash
# Check device compatibility:
adb shell getprop ro.build.version.release    # Need Android 6.0+
adb shell getprop ro.product.cpu.abilist      # Need arm64-v8a

# Uninstall old version:
adb uninstall com.kinecut.app

# Then install:
adb install app/build/outputs/apk/debug/app-debug.apk
```

### "Build Fails: Symbol not found"
```bash
# Update dependencies:
./gradlew dependencies
./gradlew clean build

# Or update Kotlin:
# build.gradle.kts: id("org.jetbrains.kotlin.android") version "2.0.0"
```

---

## ⚡ Build Performance Tips

### Faster Builds
```bash
# gradle.properties:
org.gradle.jvmargs=-Xmx4g                    # More RAM
org.gradle.parallel=true                      # Parallel tasks
org.gradle.workers.max=8                      # Max workers
org.gradle.caching=true                       # Build cache
```

### Instant Run
```
Android Studio → Run → Edit Configurations
→ Enable "Deploy as instant dynamic feature"
```

### Skip Tests
```bash
./gradlew assembleDebug -x test
```

### Verbose Output (if debugging)
```bash
./gradlew assembleDebug --info
./gradlew assembleDebug --debug
```

---

## 📱 Running on Device

### Basic Run
```bash
# Connect device and run from Android Studio:
Run → Run 'app'

# Or terminal:
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.kinecut.app/.MainActivity
```

### Run with Logs
```bash
# Clear logs:
adb logcat -c

# Run app:
adb shell am start -n com.kinecut.app/.MainActivity

# View logs:
adb logcat | grep kinecut
adb logcat | grep -i error
```

### Debug Build
```bash
# With debugger:
Run → Debug 'app'

# Set breakpoints in Android Studio and inspect state
```

---

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Code Coverage
```bash
./gradlew testDebugUnitTestCoverage
./gradlew connectedAndroidTestCoverage
```

---

## 📦 Releasing to Google Play

### Prerequisites
1. Google Play Developer Account ($25 one-time)
2. Signed release APK
3. App privacy policy
4. App screenshots and description

### Steps
1. Create signed APK (see Release Build above)
2. Go to https://play.google.com/console
3. Create new app
4. Fill in app details and screenshots
5. Upload APK
6. Submit for review

```bash
# Build signed release APK:
./gradlew bundleRelease  # Creates AAB (recommended)
# OR
./gradlew assembleRelease  # Creates APK
```

---

## 📊 Project Structure After Build

```
KineCut/
├── app/
│   ├── build/
│   │   ├── outputs/
│   │   │   ├── apk/
│   │   │   │   ├── debug/app-debug.apk          ← Debug APK
│   │   │   │   └── release/app-release.apk      ← Release APK
│   │   │   └── bundle/
│   │   │       └── release/app-release.aab      ← Play Store bundle
│   │   ├── intermediates/
│   │   └── reports/
│   ├── src/
│   ├── build.gradle.kts
│   └── ...
├── .gradle/                                      ← Cache (auto-generated)
├── build.gradle.kts
└── ...
```

---

## 🔍 Verification Checklist

After build completes, verify:

- [ ] APK file exists at `app/build/outputs/apk/debug/app-debug.apk`
- [ ] APK size is 5-20 MB (depends on build)
- [ ] App installs without errors
- [ ] App launches without crashing
- [ ] All 5 screens are accessible
- [ ] Dark theme loads correctly
- [ ] No permission warnings on install

---

## 📞 Quick Support

| Issue | Solution |
|-------|----------|
| Build fails | Run `./gradlew clean build` |
| Sync fails | Delete `.gradle` folder, retry |
| APK won't install | Check device API level (need 24+) |
| App crashes | Check `adb logcat` output |
| Slow build | Allocate more RAM in `gradle.properties` |

---

## 🎓 Next Learning Steps

After successfully building:

1. **Explore Source Code**
   - Study MVVM architecture
   - Review Compose UI patterns
   - Check API service implementations

2. **Add Features**
   - Custom filters
   - Additional effects
   - More export formats
   - Cloud project sync

3. **Optimize Performance**
   - Profile with Android Profiler
   - Optimize video encoding
   - Reduce APK size
   - Add ProGuard obfuscation

4. **Release Preparation**
   - Add privacy policy
   - Create app screenshots
   - Write app description
   - Set up beta testing

---

## 📚 Resources

**Official Documentation:**
- https://developer.android.com/
- https://kotlinlang.org/docs/
- https://developer.android.com/jetpack/compose

**Build Tools:**
- https://gradle.org/
- https://gradle.org/guides/

**Advanced Topics:**
- https://developer.android.com/guide/topics/media/media3
- https://firebase.google.com/docs
- https://developer.android.com/guide/navigation/navigation-compose

---

## ✅ Final Checklist

Before starting build:

- [ ] Android Studio 2024.1+ installed
- [ ] JDK 17+ available
- [ ] 8GB+ RAM available
- [ ] 30GB+ disk space available
- [ ] Project copied to computer
- [ ] Internet connection stable
- [ ] Device plugged in (if testing on device)
- [ ] USB Debugging enabled (if testing on device)

---

## 🚀 You're Ready!

The KineCut clone is complete and ready to build. Follow these steps:

1. **Transfer** the project to your computer (see above)
2. **Open** in Android Studio
3. **Wait** for Gradle sync (5-10 minutes)
4. **Build** APK (5-15 minutes)
5. **Install** and test on device
6. **Enjoy** your video editor!

---

**Questions?** Refer to BUILD_GUIDE.md or README.md for more details.

**Ready to begin?** Start by copying the project to your computer!

---

Generated: 2026-11-08
Project: KineCut v3.9.6 Clone
Status: ✅ Complete and Build-Ready
