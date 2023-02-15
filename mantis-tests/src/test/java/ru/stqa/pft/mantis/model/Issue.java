package ru.stqa.pft.mantis.model;

public class Issue {

  private int id;
  private String summary;
  private String description;
  private Project project;

  private IssueStatus status;

  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public Project getProject() {
    return project;
  }

  public IssueStatus getStatus() {
    return status;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public Issue withStatus(IssueStatus status) {
    this.status = status;
    return this;
  }

  public enum IssueStatus {
    NEW (10),
    FEEDBACK (20),
    ACKNOWLEDGED (30),
    CONFIRMED (40),
    ASSIGNED (50),
    RESOLVED (80),
    CLOSED (90);

    private final int id;

    IssueStatus(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }

    public static IssueStatus find(int id) {
      IssueStatus[] statuses = values();
      for (IssueStatus status : statuses) {
        if (status.id == id)
          return status;
      }
      return null;
    }

  }

}


