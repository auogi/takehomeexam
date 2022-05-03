package takehomeexam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
// Test class added ONLY to cover main() invocation no assertion necessary here
class TakeHomeExamApplicationTests {

    @Test
    void contextLoads() {
        TakeHomeExamApplication.main(new String[] {});
    }
}
