package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class StepDefs {
    private String addURI;
    private MultiValueMap<String, String> headers;
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;

    @Given("I Set POST student service api endpoint")
    public void iSetPOSTStudentServiceApiEndpoint() {
        addURI = "http://localhost:8080/student";
    }

    @When("I Set request HEADER")
    public void iSetRequestHEADER() {
        headers = new HttpHeaders();
        headers.add("Accept-Language", "en-US");
        headers.add("Content-Type", "application/json");
    }

    @And("Send a POST HTTP request")
    public void sendAPOSTHTTPRequest() {
        String requestBody = "{\n" +
                "\"id\":2134495,\n" +
                "\"firstName\": \"CucumberTest\",\n" +
                "\"lastName\": \"CucumberTest2\",\n" +
                "\"classRoom\":\"3 A\",\n" +
                "\"nationality\": \"Singapore\"\n" +
                "}";


        HttpEntity<String>entity = new HttpEntity<String>(requestBody, headers);

        restTemplate = new RestTemplate();
        response = restTemplate.postForEntity(addURI, entity, String.class);

    }

    @Then("I recieve valid Response")
    public void iRecieveValidResponse() {
        String responseBody = response.getBody().toString();
        System.out.println("responseBody --->" + responseBody);

        Assert.hasText(responseBody,"New Student Succesfully Added");
        Assert.isTrue(response.getStatusCode()== HttpStatus.CREATED);
    }
}
