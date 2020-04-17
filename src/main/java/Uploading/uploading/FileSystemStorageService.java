package Uploading.uploading;

import java.io.File;
import java.io.FileInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println("Storsge initilized !");
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());

            long myFile=new Date().getTime();
            String filename =String.valueOf(myFile);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " );
            }
            System.out.println("File name : " + filename);
            System.out.println("root location : " + rootLocation.resolve(filename));

            Files.write(this.rootLocation.resolve(filename), file.getBytes());

        } catch (IOException e) {
            throw new StorageException("Failed to store file " +  e);
        }

        return filename;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public String load(String filename) {
        rootLocation.resolve(filename);
        String image = "";

        File file = new File(rootLocation.resolve(filename).toString());
        String encodedBase64 = null;
        System.out.println("file name : " + file.getName());
        if (file != null) {
            String extention = FilenameUtils.getExtension(file.getName());
            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(file);
            } catch (java.io.FileNotFoundException ex) {
                Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = new byte[(int) file.length()];
            try {
                fileInput.read(bytes);
            } catch (IOException ex) {
                Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
            }
            encodedBase64 = Base64.getEncoder().encodeToString(bytes);
            image = "data:image/" + extention + ";base64," + encodedBase64;
            try {
                fileInput.close();
            } catch (IOException ex) {
                Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return image;
        }
        return "Image not found !";
//        return Paths.get("/home/developer_backend/Documents/Demos/Java/uploading/src/main/resources/uploads/" + filename + ".jpg");
    }

    @Override
    public Resource loadAsResource(String filename) {
//        String file = load(filename);
//        Resource resource = new UrlResource(file.toUri());
//        if (resource.exists() || resource.isReadable()) {
//            return resource;
//        } else {
//            throw new FileNotFoundException(
//                    "Could not read file: " + filename);
//        }
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public String delete(String fileName) {
        Path path = Paths.get(rootLocation.resolve(fileName).toString());
        try {
            Files.delete(path);
        } catch (IOException ex) {
            Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Deleted succesfull .";
    }
}
