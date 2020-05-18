package sci.travel_app.walkthebear.data_utils.dto;

import sci.travel_app.walkthebear.model.entities.Place;

import java.util.List;

public class UnplannedPlacesTemp {
    private List<Place> unplannedPlacesTemp;

    public List<Place> getUnplannedPlacesTemp() {
        return unplannedPlacesTemp;
    }

    public void setUnplannedPlacesTemp(List<Place> unplannedPlacesTemp) {
        this.unplannedPlacesTemp = unplannedPlacesTemp;
    }

    public void addToUnplannedPlaces(Place place){
        this.unplannedPlacesTemp.add(place);
    }
}