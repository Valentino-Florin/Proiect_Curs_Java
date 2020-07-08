package sci.travel_app.walkthebear.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sci.travel_app.walkthebear.model.entities.Place;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    public void uploadThumbnailFile(Place savedPlace, MultipartFile multipartFile, String fileNameT) throws IOException {
        String uploadDir = "./user-images/" + savedPlace.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileNameT);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileNameT);
        }

    }
}
