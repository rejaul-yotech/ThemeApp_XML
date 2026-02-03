# 🌓 Android Dynamic Theme Switcher (Kotlin + XML)

This is a professional-grade Android application demonstrating the implementation of a 3-state theme switcher (Light, Dark, and System Default) using **Kotlin**, **View Binding**, and **Material 3**.

## 📖 Features
- **Smart Initial Selection:** Automatically detects and applies the System Default theme on first run.
- **Data Persistence:** Saves the user's theme preference locally using `SharedPreferences`.
- **Zero-Flicker Boot:** Theme application occurs before `super.onCreate()` to ensure a smooth launch experience.
- **Modern Transitions:** Implements the latest `overrideActivityTransition` API (API 34+) with backward compatibility.
- **Dynamic Icons:** Vector assets use `?attr` references to change colors instantly when the theme toggles.

## 🛠️ Tech Stack
- **Language:** Kotlin
- **UI Architecture:** XML (View Binding)
- **Theme Engine:** AppCompatDelegate & Material Components
- **Persistence:** SharedPreferences
- **Design System:** Material 3 (M3)

## 📂 Project Structure
```text
app/
├── java/.../
│   ├── ThemeManager.kt       # Logic for storing and applying themes
│   └── MainActivity.kt       # UI Controller and transition logic
├── res/
│   ├── values/               # Default (Light) colors and themes
│   ├── values-night/         # Dark mode color and theme overrides
│   ├── color/                # StateLists for interactive UI elements
│   └── layout/               # Activity XML definitions
