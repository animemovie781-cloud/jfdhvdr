# KineCut ProGuard rules

-keep class com.kinecut.app.** { *; }
-keep class com.pairip.** { *; }

-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

-dontwarn org.bouncycastle.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**
