package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    skipIfNotFixed(1);
    long now = System.currentTimeMillis();
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary(String.format("Test issue %s", now))
            .withDescription(String.format("Test description %s", now)).withProject(projects.iterator().next());
    Issue createdIssue = app.soap().addIssue(issue);
    assertEquals(createdIssue.getSummary(), issue.getSummary());
    assertEquals(createdIssue.getDescription(), issue.getDescription());
    assertEquals(createdIssue.getProject().getId(), issue.getProject().getId());
    assertEquals(createdIssue.getStatus().getId(), Issue.IssueStatus.NEW.getId());
  }

  @Test
  public void testGetIssue() throws MalformedURLException, ServiceException, RemoteException {
    Issue issue = app.soap().getIssue(3);
    System.out.println(issue.getId() + " - " + issue.getSummary());
  }

}
