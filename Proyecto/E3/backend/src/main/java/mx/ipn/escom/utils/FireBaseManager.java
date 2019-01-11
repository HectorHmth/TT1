package mx.ipn.escom.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FireBaseManager {
	private static FirebaseApp app;
	
	static {
		try {
			FileInputStream serviceAccount =
					  new FileInputStream(Thread.currentThread().getContextClassLoader()
							  .getResource("coffeeapp-b6431-firebase-adminsdk-0uvdg-2e543f93a8.json")
							  .getFile());
					FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://coffeeapp-b6431.firebaseio.com")
					  .build();
					app = FirebaseApp.initializeApp(options);
					System.out.println("--->FireBase Inicializado" + FirebaseApp.getInstance().toString());
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("--->ERROR AL CARGAR EL ARCHIVO");
			}
	}
	
	public static FirebaseApp getInstance() {
		return app.getInstance();
	}

}
