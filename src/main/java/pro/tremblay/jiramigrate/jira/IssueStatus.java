package pro.tremblay.jiramigrate.jira;

/**
 * Created by htrembl2 on 2/17/15.
 */
public class IssueStatus {
    private String self;
    private String description;
    private String name;
    private String id;
    private IssusStatusCategory statusCategory;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IssusStatusCategory getStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(IssusStatusCategory statusCategory) {
        this.statusCategory = statusCategory;
    }
}
