package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("7172fcb5f1888f5fac3dced24caeaa6a","");
  }

  @Test
  public void testCreateIssue() {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test subject RA").withDescription("Test description RA");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId).withState("0"));
    assertEquals(newIssues, oldIssues);
  }

  protected Set<Issue> getIssues() {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=200").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  protected int createIssue(Issue newIssue) {
    String json = RestAssured.given()
            .formParam("subject", newIssue.getSubject())
            .formParam("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
