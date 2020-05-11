package sci.travel_app.walkthebear.data_utils.dto;

import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.model.misc.SubCategory;

import java.util.List;

public class PlaceDTO {
    private long placeId;
    private String placeName;
    private String placeCounty;
    private String placeCity;
    private String placeAddress;
    private String placeCoordinates;
    private String placePhoneNumber;
    private String placeEmail;
    private Category placeCategory;
    private SubCategory placeSubcategory;
    private String placeWorkingHours;
    private String placeDescription;

    private List<Rating> ratings;

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCounty() {
        return placeCounty;
    }

    public void setPlaceCounty(String placeCounty) {
        this.placeCounty = placeCounty;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceCoordinates() {
        return placeCoordinates;
    }

    public void setPlaceCoordinates(String placeCoordinates) {
        this.placeCoordinates = placeCoordinates;
    }

    public String getPlacePhoneNumber() {
        return placePhoneNumber;
    }

    public void setPlacePhoneNumber(String placePhoneNumber) {
        this.placePhoneNumber = placePhoneNumber;
    }

    public String getPlaceEmail() {
        return placeEmail;
    }

    public void setPlaceEmail(String placeEmail) {
        this.placeEmail = placeEmail;
    }

    public Category getPlaceCategory() {
        return placeCategory;
    }

    public void setPlaceCategory(Category placeCategory) {
        this.placeCategory = placeCategory;
    }

    public SubCategory getPlaceSubcategory() {
        return placeSubcategory;
    }

    public void setPlaceSubcategory(SubCategory placeSubcategory) {
        this.placeSubcategory = placeSubcategory;
    }

    public String getPlaceWorkingHours() {
        return placeWorkingHours;
    }

    public void setPlaceWorkingHours(String placeWorkingHours) {
        this.placeWorkingHours = placeWorkingHours;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
