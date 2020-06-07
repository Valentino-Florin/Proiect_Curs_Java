package sci.travel_app.walkthebear.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sci.travel_app.walkthebear.controller.HomePageController;

@SpringBootTest
public class HomePageControllerTest {

    @Autowired
    private HomePageController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
