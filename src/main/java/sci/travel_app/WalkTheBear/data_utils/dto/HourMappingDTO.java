package sci.travel_app.WalkTheBear.data_utils.dto;

import sci.travel_app.WalkTheBear.model.entities.HourMapping;

import java.util.List;

public class HourMappingDTO {
    private List<HourMapping> hourMappingList;

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
