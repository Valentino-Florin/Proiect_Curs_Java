package sci.travel_app.walkthebear.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HourMapping {
    @Id
    @Column(name = "HOURMAPPING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hourId;
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


    public Long getHourId() {
        return hourId;
    }

    public void setHourId(Long hourID) {
        hourId = hourId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourMapping that = (HourMapping) o;
        return hourId.equals(that.hourId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hourId);
    }
}
