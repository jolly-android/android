# Near & Around Me

An Android reference application that showcases nearby points of interest around the user using a clean MVVM + Jetpack Compose stack. The project focuses on location awareness, delightful UI, and testable architecture so it can serve as a solid starting point for local discovery experiences.

## Highlights

- 100% Kotlin with Material 3 + Compose.
- MVVM presentation powered by coroutines and Flow.
- Repository abstraction over a curated local catalog (no external keys needed).
- Runtime location permission guidance and graceful fallbacks.
- Thorough developer log documenting the journey.

## Feature Set

- Responsive home screen with split Google Map + list layout.
- Search-as-you-type filtering across categories, names, and addresses.
- Tap-to-focus markers plus detail view with rich descriptions.
- One-tap directions that launch the user's preferred maps app with GPS routing.
- Phone dialer deep link for quick reservations or questions.

## Project Layout

- `app/` – Android application module.
- `docs/progress-log.md` – Day-by-day build journal.
- `app/src/main/java/com/example/nearandaroundme/` – Kotlin sources.

## Getting Started

1. Open the project in Android Studio Jellyfish or newer.
2. Sync Gradle.
3. Run the `app` configuration on a device/emulator with Google Play Services.

### Google Maps API Key

Add a Maps SDK key to `local.properties` (or export it as an env var) via:

```
MAPS_API_KEY=YOUR_KEY_HERE
```

Gradle automatically wires this value into the manifest placeholder so Google Maps Compose can initialize.

## License

This project is provided for demonstration and interview purposes.


# auto-edit 2025-12-05 12:47:05.200178
