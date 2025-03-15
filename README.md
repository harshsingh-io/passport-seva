# Passport Seva Android App 🛂

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white" alt="Jetpack Compose"/>
  <img src="https://img.shields.io/badge/Material%20Design%203-757575?style=for-the-badge&logo=material-design&logoColor=white" alt="Material Design 3"/>
  <img src="https://img.shields.io/badge/Clean%20Architecture-white?style=for-the-badge&logo=clean-code&logoColor=black" alt="Clean Architecture"/>
</div>

## 📱 Overview

Passport Seva is a modern Android application that provides a digital interface for accessing passport services offered by the Ministry of External Affairs, India. Built with Jetpack Compose and following Clean Architecture principles, this app simplifies the passport application process, appointment booking, and application status tracking with an intuitive user interface.

<div align="center">
  <img src="screenshots/light_theme/homePage.png" width="200"/>
  <img src="screenshots/light_theme/appointmentBookingScreen.png" width="200"/>
  <img src="screenshots/light_theme/feeCalculatorScreen.png" width="200"/>
  <img src="screenshots/dark_theme/documentAdvisorScreen.png" width="200"/>
</div>

## ✨ Key Features

### 🔐 User Authentication
- Login and registration with email/password
- Social login options (Google and DigiLocker)
- Secure password management with recovery options

<div align="center">
  <img src="screenshots/light_theme/loginScreen.png" width="200"/>
  <img src="screenshots/light_theme/registrationScreen.png" width="200"/>
</div>

### 📅 Appointment Management
- Check appointment availability across various Passport Seva Kendras
- Book appointments for different passport services
- View and manage existing appointments
- Location-based PSK recommendations

<div align="center">
  <img src="screenshots/light_theme/appointmentBookingScreen.png" width="200"/>
  <img src="screenshots/light_theme/appointmentBookingDateAndTimeScreen.png" width="200"/>
  <img src="screenshots/light_theme/appointmentDetiailsScreen.png" width="200"/>
</div>

### 📑 Document Advisor
- Comprehensive document requirements guide for different passport services
- Detailed information about required document formats and specifications
- Interactive checklist for document preparation

<div align="center">
  <img src="screenshots/light_theme/documentAdvisorScreen.png" width="200"/>
</div>

### 📋 Annexures & Forms
- Access to annexures and affidavits required for passport applications
- Downloadable PDF forms
- Organized categories for easy navigation

<div align="center">
  <img src="screenshots/light_theme/annexuresScreen.png" width="200"/>
</div>

### 💰 Fee Calculator
- Calculate fees for different passport services
- Support for various passport types (normal, tatkal)
- Age-based fee calculation
- Additional services fee estimation

<div align="center">
  <img src="screenshots/light_theme/feeCalculatorScreen.png" width="200"/>
</div>

### ❓ FAQs & Help
- Detailed FAQs organized by categories
- Searchable content
- Interactive Q&A format

<div align="center">
  <img src="screenshots/light_theme/faqScreen.png" width="200"/>
</div>

### 👤 Profile Management
- User profile management
- Personal information updates
- Security settings
- Notification preferences

<div align="center">
  <img src="screenshots/light_theme/profileScreen.png" width="200"/>
  <img src="screenshots/light_theme/editProfileScreen.png" width="200"/>
</div>

### 🌓 Theme Support
- Light and dark theme support
- Consistent design language across themes
- Material You design principles

<div align="center">
  <img src="screenshots/light_theme/servicesScreen.png" width="200"/>
  <img src="screenshots/dark_theme/homeScreen.png" width="200"/>
</div>

## 🏗️ Technical Architecture

The application follows Clean Architecture principles with a modular approach:

### 🧩 Layers
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

### 🛠️ Key Technologies

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material 3** - Latest Material Design components
- **MVVM Architecture** - Clear separation of concerns
- **Coroutines & Flow** - Asynchronous programming
- **Dagger Hilt** - Dependency injection
- **Navigation Component** - In-app navigation
- **Room Database** - Local data persistence 
- **Retrofit** - API communication

## 📂 Project Structure

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

## 🎨 UI Components & Design System

The application features a modern, Material 3-based design with:

- **Consistent color scheme** with primary blue accents
- **Clean, accessible typography**
- **Responsive layouts** for different screen sizes
- **Intuitive navigation** with bottom navigation bar
- **Card-based UI components** for structured information display
- **Custom components** for specialized functionality

## 💡 Key Implementation Features

### Composable UI Components
Built reusable UI components like:
- Custom AppBar
- SearchBar
- LoadingIndicator
- QuickActionItem 
- ServiceItem
- ExpandableSection

### State Management
- Used ViewModel with StateFlow for UI state management
- Implemented unidirectional data flow
- Proper separation of UI and business logic

### Navigation
- Implemented type-safe navigation with Jetpack Navigation Component
- Screen transitions and deep linking support

### Form Handling
- Built robust form validation
- User-friendly error messages
- Interactive date and time pickers

### Animations
- Smooth transitions between screens
- Micro-interactions for better user experience
- Animated content changes

## 🚀 Getting Started

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

## 📝 Development Notes

### Build Variants
- Debug: For development and testing
- Release: For production deployment

### Testing
The project supports different types of tests:
- Unit tests for domain and data layers
- UI tests with Compose testing framework
- Integration tests for end-to-end functionality

## 📊 Current Status

This application is in development phase with mockup data implemented for UI demonstration. API integration will be implemented in future updates to connect with real Passport Seva services.

## 🙏 Acknowledgments
- Material Design for UI components and guidelines
- Ministry of External Affairs documentation for passport service references
