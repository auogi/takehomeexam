package takehomeexam.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import takehomeexam.model.Student;
import takehomeexam.repository.StudentRepository;
import takehomeexam.service.StudentServiceImpl;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(StudentController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase
public class StudentControllerIT {

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentServiceImpl studentServiceImpl;

    @InjectMocks
    private StudentController studentController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddStudentAndReturnSuccessResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String student = "{\n" +
                "\"id\":2134491,\n" +
                "\"firstName\": \"fdasfas1fdsdsasfda\",\n" +
                "\"lastName\": \"Wong\",\n" +
                "\"classRoom\":\"3 A\",\n" +
                "\"nationality\": \"American\"\n" +
                "}";

                mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(student)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
    }

    @Test
    public void shouldNotAddStudentWhenFirstNameIsNotPresentAndReturnErrorResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String student = "{\n" +
                "\"id\":1,\n" +
                "\"lastName\": \"Test1\",\n" +
                "\"classRoom\":\"3 A\",\n" +
                "\"nationality\": \"Singapore\"\n" +
                "}";;

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(student)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("REQUIRED_FIELD_VALIDATION"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("first_name Cannot be blank"));
    }

    @Test
    public void shouldUpdateStudentAndReturnSuccessResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String student = "{\n" +
                "\"id\":31313131,\n" +
                "\"firstName\": \"Test1\",\n" +
                "\"lastName\": \"Test1\",\n" +
                "\"classRoom\":\"3 A\",\n" +
                "\"nationality\": \"Singapore\"\n" +
                "}";;

        mockMvc.perform(MockMvcRequestBuilders.put("/student")
                        .content(student)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    public void shouldDeleteStudentAndReturnSuccessResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String student = "{\n" +
                "\"id\":1,\n" +
                "\"lastName\": \"Test1\",\n" +
                "\"classRoom\":\"3 A\",\n" +
                "\"nationality\": \"Singapore\"\n" +
                "}";;


        mockMvc.perform(MockMvcRequestBuilders.delete("/student")
                        .content(student)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

}