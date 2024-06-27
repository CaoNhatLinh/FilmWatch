# Movie watching application for Android uses Java language

---
### Add dependencies in file `build.gradle.kts` (module:app)
`
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
`
### backend
> api : [backend-api-movies]([https://www.example.com](https://github.com/CaoNhatLinh/backend-api-movies))
> admin : [MovieAdminLaravel](https://github.com/CaoNhatLinh/MovieAdminLaravel)
