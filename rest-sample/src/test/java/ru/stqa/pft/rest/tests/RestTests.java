package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(19);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test subject YB 2").withDescription("Test description YB 2");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId).withState("0"));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssue() throws IOException {
    Set<Issue> issue = getIssue(2);
    System.out.println(issue);
  }

}
