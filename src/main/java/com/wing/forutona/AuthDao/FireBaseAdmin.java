package com.wing.forutona.AuthDao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.wing.forutona.AuthDto.Userinfo;
import com.wing.forutona.Prefrerance;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FireBaseAdmin {
    FireBaseAdmin() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream(Prefrerance.serviekeypath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://forutona.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    String GetUserInfoCustomToken(Userinfo item){
        try {
            Map<String, Object> additionalClaims = new HashMap<String, Object>();
            additionalClaims.put("level", 1);
            additionalClaims.put("email", item.getEmail());
            additionalClaims.put("profilepicktureurl", item.getProfilepicktureurl());
            additionalClaims.put("nickname", item.getNickname());

            String customToken = FirebaseAuth.getInstance().createCustomToken(item.getUid(),additionalClaims);
            return customToken;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "";
        }
    }
    String GetUserInfoCustomToken(String uid){
        try {
            String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
            return customToken;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "";
        }
    }
    UserRecord getUser(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid);
    }

}
