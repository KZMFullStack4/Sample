/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uploading.uploading;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author developer_backend
 */
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
      
}
