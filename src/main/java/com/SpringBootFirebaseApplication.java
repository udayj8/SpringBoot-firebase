package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class SpringBootFirebaseApplication {

    public static void main(String[] args) throws IOException {

        // Check if Firebase has already been initialized
        if (FirebaseApp.getApps().isEmpty()) {
            ClassLoader classLoader = SpringBootFirebaseApplication.class.getClassLoader();

            File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }

        SpringApplication.run(SpringBootFirebaseApplication.class, args);
    }
}