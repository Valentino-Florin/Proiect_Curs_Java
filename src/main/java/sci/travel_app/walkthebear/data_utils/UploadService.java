package sci.travel_app.walkthebear.data_utils;

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

    /**
     * uploads image to a folder on disk
     * @param savedPlace place for which the image is uploaded
     * @param multipartFile the image file
     * @param fileNameT name of the image file
     */
    public void uploadImageFile(Place savedPlace, MultipartFile multipartFile, String fileNameT) throws IOException {
        String uploadDir = "src/main/resources/static/files/img/" +  savedPlace.getId() + "/";
                Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileNameT);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file: " + fileNameT);
            }
    }

}
