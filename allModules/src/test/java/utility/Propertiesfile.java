package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class Propertiesfile {
public static Properties config;

public static String getkey(String key) throws Throwable {
	
	config=new Properties();
	config.load(new FileInputStream("D:\\qedg\\allModules\\file.properties"));
	return config.getProperty(key);
	
    
}


}
