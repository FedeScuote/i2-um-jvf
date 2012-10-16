package propertiesDAO;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class ResourceBundleP {







       public String getStringProperty(String bundleName, String keyName) {
               try {
                       return ResourceBundle.getBundle(bundleName).getString(keyName);
               } catch (MissingResourceException e) {
                       displayError(bundleName, keyName, e);
                       return null;
               }

       }

       private void displayError(String bundleName, String keyName,
                       MissingResourceException e) {
               String key = e.getKey();
               String fileName = bundleName.replace('.', '/') + ".propertiesDAO";
               if (key != null && key.length() > 0) {
                       System.err.println("No hay una clave \"" + keyName
                                       + "\" en el archivo " + fileName);

               } else {
                       System.err.println("No encontre el archivo " + fileName);
                       System.err.println("Lo busqué en los siguientes directorios:");
                       displayClasspath();

               }

       }

       private void displayClasspath() {
               String[] classPath = getClassPath();
               for (int i = 0; i < classPath.length; i++) {
                       System.err.println(classPath[i]);
               }
       }

       private String[] getClassPath() {
               String classPath = System.getProperty("java.class.path");
               return classPath.split(";");
       }



}
