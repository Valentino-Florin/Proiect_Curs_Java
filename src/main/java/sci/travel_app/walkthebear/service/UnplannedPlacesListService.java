package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.UnplannedPlacesList;
import sci.travel_app.walkthebear.repository.UnplannedPlacesListRepository;

import java.util.List;

@Service
public class UnplannedPlacesListService {
    @Autowired
    private UnplannedPlacesListRepository unplannedPlacesListRepository;

    public void createList(AppUser user){
        UnplannedPlacesList created = new UnplannedPlacesList();
        created.setUser(user);
        unplannedPlacesListRepository.save(created);
    }
    public void addToList(Place place, UnplannedPlacesList list){
        List<Place> tempList = list.getUnplannedPlacesTemp();
        tempList.add(place);
        list.setUnplannedPlacesTemp(tempList);
        unplannedPlacesListRepository.save(list);
    }

    public void removeFromList(Place place, UnplannedPlacesList list) {
        List<Place> tempList = list.getUnplannedPlacesTemp();
        tempList.remove(place);
        list.setUnplannedPlacesTemp(tempList);
        unplannedPlacesListRepository.save(list);
    }
    public boolean isAdded(Place place, AppUser user) {
        return unplannedPlacesListRepository.findByUser(user).getUnplannedPlacesTemp().contains(place);
    }

    public List<UnplannedPlacesList> getAll(){
        return unplannedPlacesListRepository.findAll();
    }

    public UnplannedPlacesList findByUser(AppUser user){
        return unplannedPlacesListRepository.findByUser(user);
    }
}
