package Uploading.uploading;

//import Uploading.uploading.
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.CommandLineRunner;

//import org.apache.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@EnableConfigurationProperties(StorageProperties.class)
@RestController
@RequestMapping("/data")
@SpringBootApplication
public class UploadingApplication implements CommandLineRunner {

//     MyController myCont;
    public static void main(String[] args) {
        SpringApplication.run(UploadingApplication.class, args);
    }
//    private final String filPath = "/home/developer_backend/Documents/1/Hospitality/Hospitality Backend/backend/src/main/resources/images/";
//
//    @GetMapping("/h")
//    public String get() {
//      
//        return "Welcome";
//    }

//    @Value("${images.address}")
//    String imageAdd;
//    private FileSystemStorageService storageService;
//
//    public UploadingApplication(FileSystemStorageService storageService) {
//        this.storageService = storageService;
//    }
//    @PostMapping("/upload-file")
//    @ResponseBody
//    public Object uploadFile(@RequestParam("file") MultipartFile file) {
//        String name = storageService.store(file);
//
////        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path("/download/")
////                .path(name)
////                .toUriString();
//        return name;
////        return new FileResponse(name, uri, file.getContentType(), file.getSize());
//    }
//    @DeleteMapping("/delete")
//    public Object delete(@RequestHeader("name") String name) throws IOException {
//        try {
//            Path path = Paths.get("/home/developer_backend/Documents/Demos/Java/uploading/src/main/resources/images/" + name + ".jpg");
//            Files.delete(path);
//            return "Image saved successfully !";
//        } catch (Exception ex) {
//            System.out.println("Error deleting : " + ex);
//            return null;
//        }
//
//    }
//    @GetMapping("/get")
//    public String getImageByName(@RequestHeader("name") String name) throws IOException {
//
//        String image = "";
//        File file = new File(filPath + name + ".jpg");
//        String encodedBase64 = null;
//        System.err.println("file name : " + file.getName());
//        if (file != null) {
//            String extention = FilenameUtils.getExtension(file.getName());
//            FileInputStream fileInput = new FileInputStream(file);
//            byte[] bytes = new byte[(int) file.length()];
//            fileInput.read(bytes);
//            encodedBase64 = Base64.getEncoder().encodeToString(bytes);
//            image = "data:image/" + extention + ";base64," + encodedBase64;
//            fileInput.close();
//        }
//        try {
//            Files.delete(path);
//        Path myPath = Paths.get(filPath + name + ".jpg");
//            
//            return "Image saved successfully !";
//        } catch (Exception ex) {
//            System.out.println("Error deleting : " + ex);
//            return null;
//        }
//        return image;
//    }
//    @CrossOrigin(origins = "**")
//    @GetMapping("/get-image")
//    public List<String> getImage() {
//        List<String> images = new ArrayList<>();
//
//        String filePath = filPath;
//        File fileFolder = new File(filePath);
//        if (fileFolder != null) {
//            for (final File file : fileFolder.listFiles()) {
//                if (!file.isDirectory()) {
//
//                    String encodedBase64 = null;
//                    try {
//                        String extention = FilenameUtils.getExtension(file.getName());
//                        FileInputStream fileInput = new FileInputStream(file);
//                        byte[] bytes = new byte[(int) file.length()];
//                        fileInput.read(bytes);
//                        encodedBase64 = Base64.getEncoder().encodeToString(bytes);
//                        images.add("data:image/" + extention + ";base64," + encodedBase64);
//                        fileInput.close();
//                    } catch (Exception ex) {
//
//                        System.out.println("Error : " + ex);
//                    }
//
//                }
//            }
//
//        }
//        return images;
//    }
    @Override
    public void run(String... args) throws Exception {

        
      
SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        
        System.out.println("Days betweee : " +DateConverter11.daysCalculator("2020/04/01", f.format(new Date()), "yyyy/MM/dd"));

    }
}

//            BufferedImage image = ImageIO.read(myFile);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(image, "jpg", baos);
//            byte[] hh = baos.toByteArray();
//            return hh;
