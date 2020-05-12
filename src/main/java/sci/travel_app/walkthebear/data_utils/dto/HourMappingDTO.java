package sci.travel_app.walkthebear.data_utils.dto;

import sci.travel_app.walkthebear.model.entities.HourMapping;

import java.util.List;

public class HourMappingDTO {
    private List<HourMapping> hourMappingList;

    public HourMappingDTO(){}

    public HourMappingDTO(List<HourMapping> hourMappingList) {
        this.hourMappingList = hourMappingList;
    }

    public List<HourMapping> getHourMappingList() {
        return hourMappingList;
    }

    public void setHourMappingList(List<HourMapping> hourMappingList) {
        this.hourMappingList = hourMappingList;
    }
}
