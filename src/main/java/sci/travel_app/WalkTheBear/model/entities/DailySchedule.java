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
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "ITINERARY_ID")
    private Itinerary itinerary;
    @Column(name = "NAME")
    private String name;
//    @ElementCollection
//    @MapKeyColumn(name = "TIME_SLOT")
//    @Column(name = "PLACE")
//    @CollectionTable(name = "DAY_MAPPING")
//    private Map<String, Place> day;
////    public Map<String, Place> day = new HashMap<>();

    public DailySchedule(){}

    public DailySchedule(String name) {
    }
    public DailySchedule(AppUser user, Itinerary itinerary, String name) {
        this.user = user;
        this.itinerary = itinerary;
        this.name = name;
//        this.day = new HashMap<>();
//        //maybe remove those later
//        day.put("00:00", null);
//        day.put("01:00", null);
//        day.put("02:00", null);
//        day.put("03:00", null);
//        day.put("04:00", null);
//        day.put("05:00", null);
//        day.put("07:00", null);
//        day.put("08:00", null);
//        day.put("09:00", null);
//        day.put("10:00", null);
//        day.put("11:00", null);
//        day.put("12:00", null);
//        day.put("13:00", null);
//        day.put("14:00", null);
//        day.put("15:00", null);
//        day.put("16:00", null);
//        day.put("17:00", null);
//        day.put("18:00", null);
//        day.put("19:00", null);
//        day.put("20:00", null);
//        day.put("21:00", null);
//        day.put("22:00", null);
//        day.put("23:00", null);
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
//    public Map<String, Place> getDay() {
//        return day;
//    }
//    public void setDay(Map<String, Place> day) {
//        this.day = day;
//    }
}
