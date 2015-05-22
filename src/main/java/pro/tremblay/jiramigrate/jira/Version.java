package pro.tremblay.jiramigrate.jira;

import org.joda.time.LocalDate;

/**
 * @author Henri Tremblay
 */
public class Version {

    private String self;
    private String id;
    private String description;
    private String name;
    private boolean archived;
    private boolean released;
    private LocalDate releaseDate;
    private LocalDate userReleaseDate;
    private int projectId;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getUserReleaseDate() {
        return userReleaseDate;
    }

    public void setUserReleaseDate(LocalDate userReleaseDate) {
        this.userReleaseDate = userReleaseDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
