
// Creator: Luke Heary
// Date: 6/22/18

import com.Headliner.App;
import com.Headliner.service.Initializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={App.class})
public class HeadlinerTests {

    @Autowired
    private Initializer initializer;

    @Test
    public void run() throws Exception {
        initializer.init();
    }
}
