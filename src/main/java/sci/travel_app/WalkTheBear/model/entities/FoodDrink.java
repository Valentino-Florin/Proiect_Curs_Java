package sci.travel_app.WalkTheBear.model.entities;

import sci.travel_app.WalkTheBear.model.misc.FoodDrinkSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Food and Drink")
public class FoodDrink extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodDrinkSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

    public FoodDrink() {
    }

    public FoodDrink(String name, String city, String county, String address, String phoneNumber, String email, String description, AppUser user, FoodDrinkSubCategory subcategory) {
        super(name, city, county, address, phoneNumber, email, description, user);
        Subcategory = subcategory;
    }

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
    public FoodDrinkSubCategory getSubcategory() {
        return Subcategory;
    }
    public void setSubcategory(FoodDrinkSubCategory subcategory) {
        Subcategory = subcategory;
    }
}
