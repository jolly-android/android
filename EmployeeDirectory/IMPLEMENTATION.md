# Employee Directory - Implementation Documentation

## Overview
This is a native Android application built with Kotlin and Jetpack Compose that displays a list of employees fetched from a JSON API endpoint. The app follows modern Android development best practices and implements clean architecture principles.

## Features Implemented

### Core Features
✅ **Employee List Display**: Shows all employees with their photo, name, team, and employment type  
✅ **Pull-to-Refresh**: Users can refresh the employee list by pulling down or tapping the refresh button  
✅ **Loading State**: Displays a loading indicator while fetching data  
✅ **Empty State**: Shows a friendly message when no employees are found  
✅ **Error Handling**: Displays error messages with retry functionality when network requests fail  
✅ **Image Caching**: Implements disk caching for employee photos using Coil  
✅ **Efficient Networking**: Images are loaded on-demand, not all at once  
✅ **Configuration Change Handling**: Properly handles device rotation without redundant network calls  

### Technical Implementation

#### Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture pattern with a Repository layer:

```
UI Layer (Compose) → ViewModel → Repository → API Service
```

#### Key Components

**1. Data Layer** (`data/`)
- `model/Employee.kt`: Data model for employee entities
- `model/EmployeeResponse.kt`: API response wrapper
- `api/EmployeeApiService.kt`: Retrofit API interface
- `api/ApiClient.kt`: Singleton Retrofit client configuration
- `repository/EmployeeRepository.kt`: Data repository for employee operations

**2. UI Layer** (`ui/`)
- `viewmodel/EmployeeViewModel.kt`: Manages UI state and business logic
- `viewmodel/EmployeeUiState.kt`: Sealed class representing different UI states
- `screens/EmployeeListScreen.kt`: Main screen composable
- `components/EmployeeListItem.kt`: Individual employee card
- `components/LoadingState.kt`: Loading indicator
- `components/EmptyState.kt`: Empty state UI
- `components/ErrorState.kt`: Error state with retry

**3. Application Configuration**
- `EmployeeDirectoryApplication.kt`: Configures Coil image loader with disk caching

#### Dependencies

**Networking**
- Retrofit 2.9.0 - REST API client
- OkHttp 4.12.0 - HTTP client with logging interceptor
- Gson - JSON serialization

**Image Loading**
- Coil 2.5.0 - Modern image loading library with Compose support

**Coroutines**
- Kotlin Coroutines 1.7.3 - Asynchronous programming

**UI**
- Jetpack Compose - Modern declarative UI toolkit
- Material3 - Material Design 3 components
- Compose BOM 2024.09.00

**Testing**
- JUnit 4.13.2 - Unit testing framework
- MockK 1.13.8 - Kotlin mocking library
- Turbine 1.0.0 - Flow testing library
- Coroutines Test 1.7.3 - Coroutine testing utilities

## Image Caching Strategy

The app implements a comprehensive image caching strategy to minimize network bandwidth usage:

1. **Memory Cache**: 25% of available app memory
2. **Disk Cache**: 2% of available disk space
3. **Cache Keys**: Uses photo URLs as unique cache keys
4. **No HTTP Caching**: Explicitly disabled to use custom caching logic
5. **On-Demand Loading**: Images are loaded only when they become visible in the list

Once an image is cached, it will never be re-downloaded unless the cache is cleared.

## Network Efficiency

The app is designed to minimize network usage:

1. **Single API Call**: Employee data is fetched once on app launch
2. **Manual Refresh**: Data is only re-fetched when the user explicitly requests a refresh
3. **Image Caching**: Photos are permanently cached on disk
4. **Lazy Loading**: Images load only as they scroll into view
5. **Configuration Change Resilience**: ViewModel survives configuration changes, preventing redundant API calls on rotation

## Error Handling

The app handles various error scenarios:

- **Network Errors**: Displays error message with retry button
- **Empty Response**: Shows empty state with refresh instructions
- **Image Load Failures**: Displays placeholder image
- **Unknown Errors**: Generic error message with retry option

## Unit Tests

Comprehensive unit tests are provided for critical components:

### RepositoryTest
- ✅ Successful API response handling
- ✅ Empty list handling
- ✅ Network exception handling
- ✅ Runtime exception handling

### ViewModelTest
- ✅ Initial loading state
- ✅ Success state with data
- ✅ Empty state
- ✅ Error state
- ✅ Refresh functionality
- ✅ State flow emissions
- ✅ Null exception message handling

Tests use MockK for mocking and Coroutines Test for testing async operations.

## UI/UX Features

### Employee Card
Each employee card displays:
- Circular profile photo (64dp)
- Full name (bold, primary text)
- Team name (blue accent color)
- Email address
- Employment type badge (color-coded: Full-Time = Green, Part-Time = Orange, Contractor = Blue)

### Loading State
- Centered circular progress indicator
- "Loading employees..." message

### Empty State
- Person icon
- "No employees found" message
- Pull-to-refresh instruction

### Error State
- Warning icon
- "Oops! Something went wrong" title
- Error message description
- Retry button

### Refresh Options
1. Pull-to-refresh gesture (Material Design standard)
2. Refresh button in top app bar

## Building and Running

### Prerequisites
- Android Studio Hedgehog or later
- JDK 11 or later
- Android SDK 24+ (minimum)
- Android SDK 36 (target)

### Build Commands
```bash
# Build the project
./gradlew build

# Run unit tests
./gradlew test

# Install debug APK
./gradlew installDebug

# Run instrumented tests
./gradlew connectedAndroidTest
```

## API Endpoint

The app fetches employee data from:
```
https://s3.amazonaws.com/sq-mobile-interview/employees.json
```

Expected response format:
```json
{
  "employees": [
    {
      "uuid": "string",
      "full_name": "string",
      "phone_number": "string",
      "email_address": "string",
      "biography": "string",
      "photo_url_small": "string",
      "photo_url_large": "string",
      "team": "string",
      "employee_type": "FULL_TIME" | "PART_TIME" | "CONTRACTOR"
    }
  ]
}
```

## Project Structure

```
app/src/main/java/com/example/employeedirectory/
├── data/
│   ├── api/
│   │   ├── ApiClient.kt
│   │   └── EmployeeApiService.kt
│   ├── model/
│   │   ├── Employee.kt
│   │   └── EmployeeResponse.kt
│   └── repository/
│       └── EmployeeRepository.kt
├── ui/
│   ├── components/
│   │   ├── EmptyState.kt
│   │   ├── EmployeeListItem.kt
│   │   ├── ErrorState.kt
│   │   └── LoadingState.kt
│   ├── screens/
│   │   └── EmployeeListScreen.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── viewmodel/
│       ├── EmployeeUiState.kt
│       ├── EmployeeViewModel.kt
│       └── EmployeeViewModelFactory.kt
├── EmployeeDirectoryApplication.kt
└── MainActivity.kt

app/src/test/java/com/example/employeedirectory/
├── data/repository/
│   └── EmployeeRepositoryTest.kt
└── ui/viewmodel/
    └── EmployeeViewModelTest.kt
```

## Permissions

Required permissions in AndroidManifest.xml:
- `android.permission.INTERNET` - For making network requests
- `android.permission.ACCESS_NETWORK_STATE` - For checking network connectivity

## Future Enhancements

Potential improvements for future iterations:
- Search and filter employees by name or team
- Sort options (by name, team, employee type)
- Employee detail screen
- Offline mode with local database (Room)
- Dependency injection with Hilt
- Pagination for large employee lists
- Accessibility improvements
- Dark theme support
- Localization support

## Notes

- The app does NOT persist employee data to disk (as per requirements)
- Images ARE cached on disk using Coil (as per requirements)
- The ViewModel survives configuration changes, preventing redundant network calls
- All network operations run on background threads using Kotlin Coroutines
- The UI is built entirely with Jetpack Compose (no XML layouts)

