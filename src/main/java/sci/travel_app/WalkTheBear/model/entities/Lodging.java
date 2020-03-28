package sci.travel_app.WalkTheBear.model.entities;

import sci.travel_app.WalkTheBear.model.misc.LodgingSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Lodging")
public class Lodging extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private LodgingSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

    public Lodging() {}

    public Lodging(String name, String city, String county, String address, String phoneNumber, String email, String description, AppUser user, LodgingSubCategory subcategory) {
        super(name, city, county, address, phoneNumber, email, description, user);
        Subcategory = subcategory;
    }

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
    public LodgingSubCategory getSubcategory() {
        return Subcategory;
    }
    public void setSubcategory(LodgingSubCategory subcategory) {
        Subcategory = subcategory;
    }

}
