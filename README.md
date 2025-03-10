# Passport Seva Android App

## Overview
Passport Seva is a modern Android application that provides a digital interface for accessing passport services offered by the Ministry of External Affairs, India. This app simplifies the passport application process, appointment booking, and application status tracking with an intuitive user interface.

## Features

### User Authentication
- Login and registration with email/password
- Social login options (Google and DigiLocker)
- Secure password management with recovery options

### Appointment Management
- Check appointment availability across various Passport Seva Kendras
- Book appointments for different passport services
- View and manage existing appointments
- Location-based PSK recommendations

### Document Advisor
- Comprehensive document requirements guide for different passport services
- Detailed information about required document formats and specifications
- Interactive checklist for document preparation

### Status Tracking
- Track application status using application number
- Timeline view of application progress
- Detailed status information with expected completion dates

### Fee Calculator
- Calculate fees for different passport services
- Support for various passport types (normal, tatkal)
- Age-based fee calculation
- Additional services fee estimation

### Reference Materials
- Access to annexures and affidavits required for passport applications
- Downloadable PDF forms
- Detailed FAQs organized by categories
- Help and support resources

### Profile Management
- User profile management
- Personal information updates
- Security settings
- Notification preferences

## Technical Architecture

The application follows Clean Architecture principles with a modular approach:

### Layers
1. **Presentation Layer**
   - UI components built with Jetpack Compose
   - ViewModels for UI state management
   - Screen-specific components and navigation

2. **Domain Layer**
   - Business logic encapsulation
   - Use cases for specific application features
   - Repository interfaces
   - Domain models

3. **Data Layer**
   - Repository implementations
   - Local and remote data sources
   - Data transfer objects
   - Data mapping and transformation

### Key Technologies

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material 3** - Latest Material Design components
- **MVVM Architecture** - Clear separation of concerns
- **Coroutines & Flow** - Asynchronous programming
- **Dagger Hilt** - Dependency injection
- **Navigation Component** - In-app navigation
- **Room Database** - Local data persistence (placeholder)
- **Retrofit** - API communication (placeholder)

## Project Structure

```
com.harshsinghio.passportseva/
├── data/
│   ├── di/
│   ├── repository/
│   └── source/
│       ├── local/
│       │   ├── dao/
│       │   └── preferences/
│       └── remote/
│           ├── api/
│           └── dto/
├── domain/
│   ├── di/
│   ├── model/
│   ├── repository/
│   └── usecase/
│       ├── annexure/
│       ├── appointment/
│       ├── auth/
│       ├── services/
│       └── status/
└── presentation/
    ├── common/
    │   ├── components/
    │   ├── theme/
    │   └── util/
    ├── di/
    ├── navigation/
    └── screens/
        ├── annexures/
        ├── appointment/
        ├── appointmentdetails/
        ├── documentadvisor/
        ├── faq/
        ├── feecalculator/
        ├── home/
        ├── login/
        ├── profile/
        ├── register/
        ├── services/
        └── status/
```

## Screen Designs

The application features a modern, Material 3-based design with:

- Consistent color scheme with primary blue accents
- Clean, accessible typography
- Responsive layouts for different screen sizes
- Intuitive navigation with bottom navigation bar
- Card-based UI components for structured information display

## Getting Started

### Prerequisites
- Android Studio Arctic Fox (2021.3.1) or newer
- Minimum SDK: API 21 (Android 5.0)
- Target SDK: API 33 (Android 13)
- JDK 11

### Setup Instructions
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## Development

### Build Variants
- Debug: For development and testing
- Release: For production deployment

### Testing
The project supports different types of tests:
- Unit tests for domain and data layers
- UI tests with Compose testing framework
- Integration tests for end-to-end functionality

## Current Status

This application is in development phase with mockup data implemented for UI demonstration. API integration will be implemented in future updates to connect with real Passport Seva services.

## Acknowledgments
- Material Design for UI components and guidelines
- Ministry of External Affairs documentation for passport service references

## License
This project is licensed under the MIT License - see the LICENSE file for details.