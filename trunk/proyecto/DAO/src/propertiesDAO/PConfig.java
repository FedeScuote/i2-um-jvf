package propertiesDAO;

public class PConfig {


	 private ResourceBundleP RBN;
	 private static String ConexionBundle = "conf.db"; //dirección del archivo "propertiesDAO.db"
	 private static String driverKey = "driver";
	 private static String urlKey = "url";
	 private static String userKey= "user";
	 private static String passKey= "pass";


	 private String driver;
	 private String url;
	 private String user;
	 private String pass;

	 public PConfig() {
	   RBN = new ResourceBundleP();
	   cargarProperties();
	 }







	 private void cargarProperties() {
		   driver = RBN.getStringProperty(ConexionBundle, driverKey);
		   url = RBN.getStringProperty(ConexionBundle, urlKey);
		   user= RBN.getStringProperty(ConexionBundle, userKey);
		   pass= RBN.getStringProperty(ConexionBundle, passKey);

		 }

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}









	public static String getUrlKey() {
		return urlKey;
	}





	public static void setUrlKey(String urlKey) {
		PConfig.urlKey = urlKey;
	}





	public static String getUserKey() {
		return userKey;
	}





	public static void setUserKey(String userKey) {
		PConfig.userKey = userKey;
	}







	public String getDriver() {
		return driver;
	}







	public void setDriver(String driver) {
		this.driver = driver;
	}







	public String getUser() {
		return user;
	}







	public void setUser(String user) {
		this.user = user;
	}


}
