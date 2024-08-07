
# FilmWatch
 Movie watching application for Android uses Java language
## Features
* Login/Registration
* Change Password
* Edit Personal Information
* Watch Movies
* Movie Details
* Movie List (All, Releases, Top)
* Favorite Movies
* Viewing History
* Vote


## Backend src
- API : [backend-api-movies](https://github.com/CaoNhatLinh/backend-api-movies)
- Management for Admin : [MovieAdminLaravel](https://github.com/CaoNhatLinh/MovieAdminLaravel)

## Project Overview

* Authentication
  <p align="left">
  <img src="https://github.com/user-attachments/assets/20787907-a0c8-454e-88ab-5d241243846e" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/9a5f5932-fe0a-40f2-8983-21a591329fe3" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/dd2a66ff-c945-43ad-adce-228802df4c98" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/1a7a4172-47ea-4dd2-bf81-845acc626011" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/d4c85656-888f-4887-9ac6-33b2993d68a9" width="200" /> &nbsp;
</p>

* Movies
  <p align="left">
  <img src="https://github.com/user-attachments/assets/bfb3f2e1-e448-437a-aff3-ea1a5753156e" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/0564844f-ff27-4ae3-b3b6-15cf4351d9fc" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/718e9438-b480-4022-bb72-69a5d37bc856" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/9c87ed4b-7145-4d5a-83e7-a4741aa465e1" width="200" /> &nbsp;
   <img src="https://github.com/user-attachments/assets/36fce058-e51e-4bd2-ae8f-4cea58c55973" width="200" /> &nbsp;
</p>

* Search, history, favorites and voted 
  <p
  <img src="https://github.com/user-attachments/assets/c54f8318-57ce-47c7-8229-9892e2ebac44" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/30ca753c-8d7c-48cc-a389-93b64e797e8f" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/f3459697-8543-48ad-b826-2be13cf06d08" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/9b697ef1-d85a-4135-8a17-4dd6fc5c30dd" width="200" /> &nbsp;
  <img src="https://github.com/user-attachments/assets/f09f3510-30ba-4231-bc0d-cd072ad7e3bd" width="200" /> &nbsp;
</p>

## How to run the App
1. Clone this project.
2. Open android studio.
3. Add dependencies.
4. Add your `API_KEY` into ***Api/apiClient*** file.
6. Build Project: Sync the project with Gradle files and build the project.
7. Run on Emulator/Device: Run the application on an emulator or a physical device.
---
   Add dependencies in file `build.gradle.kts (module:app)`
```
dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.1.0")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.picasso:picasso:2.5.2")
    implementation ("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation ("com.github.norulab:android-exoplayer-fullscreen:1.2.1")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0" )
}
```

