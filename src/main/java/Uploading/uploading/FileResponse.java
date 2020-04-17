/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uploading.uploading;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author developer_backend
 */
@Data
@NoArgsConstructor
public class FileResponse {
    private String name;
    private String uri;
    private String type;
    private long size;

    public FileResponse(String name, String uri, String type, long size) {
        this.name = name;
        this.uri = uri;
        this.type = type;
        this.size = size;
    }

    
    // getters and setters removed for the sake of brevity
}