
package com.AlphaDevs.cloud.web.Extra;

/**
 *
 * @author Mihindu Karunarathne 
 */
public class GenaricKeyValue {
    
    private String key;
    private String value;

    public GenaricKeyValue() {
    }

    public GenaricKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
