package airYMAGIN;

import java.beans.Beans;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	// Constructor
	private Messages() {
		// do not instantiate
	}

	// Bundle access
	private static final String BUNDLE_NAME = "airYMAGIN.messages"; //$NON-NLS-1$
	private static final String BUNDLE_EN = "airYMAGIN.messages_en_CA"; //$NON-NLS-1$
	private static final String BUNDLE_FR = "airYMAGIN.messages_fr_CA"; //$NON-NLS-1$
	private static ResourceBundle RESOURCE_BUNDLE = loadBundle();
	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME);
	}

	// Strings access
	public static String getString(String key) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
	
	public static void changeLanguage(String lang){
		if (lang.equals("EN")){ RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_EN); }
		else if (lang.equals("FR")){ RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_FR); }
		else { RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME); }
	}
}
