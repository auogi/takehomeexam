package functionalTests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class StepDefs {
    private String addURI;
    private MultiValueMap<String, String> headers;
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;

    JSONObject student = new JSONObject();

    @Given("I initiate Student Service endpoint")
    public void iSetPOSTStudentServiceApiEndpoint() {
        addURI = "http://localhost:8080/student";
        headers = new HttpHeaders();
        headers.add("Accept-Language", "en-US");
        headers.add("Content-Type", "application/json");

    }

    @When("^I set firstName as \"([^\"]*)\"$")
    public void iSetFirstName(String firstName) throws JSONException {
        student.put("firstName", firstName);

    }

    @When("^I set lastName as \"([^\"]*)\"$")
    public void iSetLastName(String lastName) throws JSONException {
        student.put("lastName", lastName);
    }

    @When("^I set classRoom as \"([^\"]*)\"$")
    public void iSetClassRoom(String classRoom) throws JSONException {
        student.put("classRoom", classRoom);
    }

    @When("^I set nationality is \"([^\"]*)\"$")
    public void iSetNationality(String nationality) throws JSONException {
        student.put("nationality", nationality);
    }

    @And("Send a POST HTTP request")
    public void sendAPOSTHTTPRequest() throws JSONException {

        HttpEntity<String>entity = new HttpEntity<String>(student.toString(), headers);

        restTemplate = new RestTemplate();



        try {
            response = restTemplate.postForEntity(addURI, entity, String.class);
        } catch(HttpStatusCodeException e) {
            response = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }

    }

    @Then("I recieve valid Response")
    public void iRecieveValidResponse() {
        String responseBody = response.getBody().toString();
        System.out.println("responseBody --->" + responseBody);

        Assertions.assertEquals(responseBody,"New Student Succesfully Added");
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Then("^I recieve error response \"([^\"]*)\"$")
    public void iRecieveInValidResponse(String errorMessage) {
        String responseBody = response.getBody().toString();
        System.out.println("responseBody --->" + responseBody);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseBody,"{\"errorCode\":\"REQUIRED_FIELD_VALIDATION\",\"message\":\"" + errorMessage + "\"}");
    }
}
