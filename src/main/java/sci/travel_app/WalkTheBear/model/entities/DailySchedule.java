package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class DailySchedule {
    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToOne
    @JoinColumn(name = "CREATED_BY", nullable = false)
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "ITINERARY_ID")
    private Itinerary itinerary;
    @Column(name = "NAME")
    private String name;
    @ElementCollection
    @MapKeyColumn(name = "TIME_SLOT")
    @Column(name = "PLACE")
    @CollectionTable(name = "DAY_MAPPING")
    public Map<String, Place> day = new HashMap<>();

    public DailySchedule(){}

    public DailySchedule(String name) {
    }
    public DailySchedule(AppUser user, Itinerary itinerary, String name) {
        this.user = user;
        this.itinerary = itinerary;
        this.name = name;
        this.day = new HashMap<>();
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
    public Map<String, Place> getDay() {
        return day;
    }
    public void setDay(Map<String, Place> day) {
        this.day = day;
    }
}
