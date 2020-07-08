package sci.travel_app.walkthebear.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLANNER_LIST")
public class UnplannedPlacesList {

    @Id
    @Column(name = "LIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listId;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;

    //    @OneToMany
//    @JoinColumn(name = "LIST_ID")
    @ElementCollection
    @CollectionTable(name="PLACE_LIST", joinColumns=@JoinColumn(name="LIST_ID"))
    @Column(name = "PLACE")
    private List<Place> unplannedPlacesTemp;

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Place> getUnplannedPlacesTemp() {
        return unplannedPlacesTemp;
    }

    public void setUnplannedPlacesTemp(List<Place> unplannedPlacesTemp) {
        this.unplannedPlacesTemp = unplannedPlacesTemp;
    }


}
