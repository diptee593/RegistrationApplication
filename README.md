# Registration Application 

This is a simple Android app built that allows a user to register by entering:

- Name  
- Date of Birth  
- Email ID  

After submitting the form:

1. The data is passed from **MainActivity → DisplayActivity**.
2. The data is stored in **SQLite database**.
3. The data is also saved in an **in-memory ArrayList**.

## Features
- Registration form with validation  
- Date Picker for DOB  
- Intent data transfer between activities  
- SQLite database storage  
- Clean and simple UI  

## Project Structure
- **MainActivity.kt** – Takes input, validates, stores data, and sends to next screen  
- **DisplayActivity.kt** – Shows the submitted user details  
- **DatabaseHelper.kt** – Handles SQLite create, insert, and fetch operations  
- **User.kt** – Data class for user model  
- **XML Layouts** – UI design for both activities

  file path
  app/
└── src/
    └── main/
        ├── AndroidManifest.xml
        ├── java/
        │   └── com/example/registrationapplication/
        │       ├── MainActivity.kt
        │       ├── DisplayActivity.kt
        │       ├── DatabaseHelper.kt
        │       └── User.kt
        └── res/
            ├── layout/
            │   ├── activity_main.xml
            │   └── activity_display.xml
            ├── drawable/
            ├── values/
            │   ├── colors.xml
            │   ├── themes.xml
            │   └── strings.xml

## How to Run
- Open project in Android Studio  
- Let Gradle sync  
- Connect mobile device or use emulator  
- Run the app  

