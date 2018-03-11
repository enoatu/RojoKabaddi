package com.example.atsushi.rk;

/**
 * Created by atsushi on 2018/03/11.
 */
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class ResourceBundleRead {
    private static final String FILENAME = "configure";

    /**
     * Resource bundle.
     */
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);
    /**
     * Method to read the property value.
     */
    void getProperty() {
        String str = "";
        if (resourceBundle != null) {
            str = resourceBundle.getString("seekBarMax");
            System.out.println("Value found: " + str + " for key: " + "seekBarMax");
        } else {
            System.out.println("Properties file was not loaded correctly!!");
        }

        System.out.println(str);
    }
}