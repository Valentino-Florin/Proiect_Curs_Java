package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
@Entity
public class HourMapping {
    @Id
    @Column(name = "HOURMAPPING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne
    @JoinColumn(name = "SCHEDULE_ID")
    private DailySchedule dailySchedule;
    @Column(name = "TIME")
    private String time;
    @OneToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    public HourMapping() {

    }
    public HourMapping(DailySchedule dailySchedule, String time, Place place) {
        this.dailySchedule = dailySchedule;
        this.time = time;
        this.place = place;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(DailySchedule dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
