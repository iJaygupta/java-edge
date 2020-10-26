/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.firebase;

import static co.admis.config.ServerConfiguration.FIREBASE_DATABASE_URL;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import org.springframework.web.context.ContextLoaderListener;

/**
 *
 * @author admis
 */
@WebListener
public class FirebaseInitialization extends ContextLoaderListener {
    @Override
  public void contextDestroyed(ServletContextEvent  arg0) {
    //Notification that the servlet context is about to be shut down.   
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
        try {
            // do all the tasks that you need to perform just after the server starts
            
            //Notification that the web application initialization process is starting
            InputStream serviceAccount = getClass().getClassLoader()
                    .getResourceAsStream("/serviceAccountKey.json");
            
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(FIREBASE_DATABASE_URL) 
                    .build();
            
            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            Logger.getLogger(FirebaseInitialization.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
}
