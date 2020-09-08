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

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    /**
     * uses a list of places to create a pdf
     * @param placeList list of places to add to the pdf
     * @param filename name of the pdf file
     * @param title title on the first page of the pdf
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public void createPdfList(List<Place> placeList, String filename, String title) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/static/files/pdf/" + filename));

        document.open();

        document.add(new Paragraph(title));
        document.add(new Paragraph("Name, County, City"));
        com.itextpdf.text.List list = new com.itextpdf.text.List();
        List<String> strings = stringify(placeList);
        for (String s : strings){
            list.add(s);
        }
        document.add(list);

        document.close();
    }

    /**
     * takes a list of places and
     * @param placeList a list of places to convert
     * @return list of strings
     */
    public List<String> stringify(List<Place> placeList) {
        List<String> strings = new ArrayList<>();
        int i = 1;
        for (Place place : placeList) {
            strings.add(i + ". " + place.toString());
            i++;
        }

        return strings;
    }

    /**
     * creates a json file based on a map
     * @param id id of itinerary
     * @param map map of objects to add to json file
     */
//    public void mapToJson(long id, Map<String, List<HourMapping>> map) {
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

    /**
     * creates a json file based on a list
     * @param name name of the file
     * @param list list of objects to add to json file
     */
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
