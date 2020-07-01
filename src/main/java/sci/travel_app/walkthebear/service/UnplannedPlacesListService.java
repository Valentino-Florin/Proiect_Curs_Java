package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.UnplannedPlacesList;
import sci.travel_app.walkthebear.repository.UnplannedPlacesListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnplannedPlacesListService {
    @Autowired
    private UnplannedPlacesListRepository unplannedPlacesListRepository;

    /**
     * creates new list, adds current user as author
     * @param user logged-in user
     */
    public void createList(AppUser user){
        UnplannedPlacesList created = new UnplannedPlacesList();
        created.setUser(user);
        created.setUnplannedPlacesTemp(new ArrayList<>());
        unplannedPlacesListRepository.save(created);
    }

    /**
     * adds a place to an existing list
      * @param place place to add to the list
     * @param list existing list to update
     */
    public void addToList(Place place, UnplannedPlacesList list){
        List<Place> tempList = list.getUnplannedPlacesTemp();
        tempList.add(place);
        list.setUnplannedPlacesTemp(tempList);
        unplannedPlacesListRepository.save(list);
    }

    /**
     * removes a place from an existing list
     * @param place place to remove from the list
     * @param list existing list to update
     */
    public void removeFromList(Place place, UnplannedPlacesList list) {
        List<Place> tempList = list.getUnplannedPlacesTemp();
        tempList.remove(place);
        list.setUnplannedPlacesTemp(tempList);
        unplannedPlacesListRepository.save(list);
    }

    /**
     * checks if a user has a list created
     * @param user logged-in user
     * @return true if there is a record
     */
    public boolean hasList(AppUser user){
        return unplannedPlacesListRepository.findByUser(user) != null;
    }

    /**
     * checks if a user has a list created and if the place is in the list
     * @param place place to check if present
     * @param user logged-in user
     * @return returns "true" if place is already added, "false" if it's not or if the user has no list yet
     */
    public boolean isAdded(Place place, AppUser user) {
        if (hasList(user)) {
            return unplannedPlacesListRepository.findByUser(user).getUnplannedPlacesTemp().contains(place);
        } else return false;
    }

    /**
     * finds all UnplannedPlacesList objects in the database
     * @return list of objects
     */
    public List<UnplannedPlacesList> getAll(){
        return unplannedPlacesListRepository.findAll();
    }

    /**
     * finds all UnplannedPlacesList objects in the database created by the current user
     * @param user logged-in user
     * @return list of objects
     */
    public UnplannedPlacesList findByUser(AppUser user){
        return unplannedPlacesListRepository.findByUser(user);
    }


}
