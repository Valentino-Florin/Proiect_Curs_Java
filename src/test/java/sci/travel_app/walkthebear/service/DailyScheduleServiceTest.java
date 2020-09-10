package sci.travel_app.walkthebear.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import sci.travel_app.walkthebear.model.entities.DailySchedule;
import sci.travel_app.walkthebear.model.entities.Itinerary;
import sci.travel_app.walkthebear.repository.DailyScheduleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)

@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DailyScheduleServiceTest {

    @Autowired
//    @MockBean
    private DailyScheduleRepository dailyScheduleRepository;
    @Autowired
//    @InjectMocks
    private DailyScheduleService dailyScheduleService;

    @Test
    void addNewDayTest() {
        Itinerary itinerary = new Itinerary();
        DailySchedule timetable = new DailySchedule();
        timetable.setItinerary(itinerary);
        timetable.setName("Day " + 1);
        dailyScheduleRepository.save(timetable);
        assertEquals(1, dailyScheduleRepository.findAll().size());
        assertEquals("Day 1", dailyScheduleRepository.findById(1).getName());
    }
    @Test
    void getDayTest() {

    }

    @Test
    void getNumberOfDaysTest() {
    }

    @Test
    void saveDayTest() {
    }

    @Test
    void getAllDaysTest() {
    }

    @Test
    void removeDayTest() {
    }

    @Test
    void deleteAllTest() {
    }
}