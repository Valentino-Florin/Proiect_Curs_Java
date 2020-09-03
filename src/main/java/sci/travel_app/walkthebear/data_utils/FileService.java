package sci.travel_app.walkthebear.data_utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.HourMapping;
import sci.travel_app.walkthebear.model.entities.Place;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    public void createPdfList(List<Place> placeList) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream("result.pdf"));
        // 3. Open document
        document.open();
        // 4. Add content
        document.add(new Paragraph("Create Pdf Document with iText in Java"));
        document.addTitle("List");
        com.itextpdf.text.List list = new com.itextpdf.text.List();
        List<String> strings = stringify(placeList);
        list.add(strings.get(0));
        list.add(strings.get(1));

        // 5. Close document
        document.close();
    }

    public List<String> stringify(List<Place> placeList) {
        List<String> strings = new ArrayList<>();

        for (Place place : placeList) {
            strings.add(place.toString());
        }

        return strings;
    }


    public void mapToJson(long id, Map<String, List<HourMapping>> map) {

        String filePath = "src/main/resources/static/files/json/" + "itinerary" + id + ".json";
        Path jsonPath = Paths.get(filePath);
        try (FileWriter writer = new FileWriter(String.valueOf(jsonPath))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(map, writer);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void listToJson(String name, List<Place> list) {

        String filePath = "src/main/resources/static/files/json/" + name + ".json";
        Path jsonPath = Paths.get(filePath);
        try (FileWriter writer = new FileWriter(String.valueOf(jsonPath))) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, writer);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
