package pro.tremblay.jiramigrate.jira;

/**
 * @author Henri Tremblay
 */
public class Component {
    private String self;
    private String id;
    private String name;
    private String description;
    private User lead;
    private String assigneeType;
    private User assignee;
    private String realAssigneeType;
    private User realAssignee;
    private boolean isAssigneeTypeValid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getLead() {
        return lead;
    }

    public void setLead(User lead) {
        this.lead = lead;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getRealAssigneeType() {
        return realAssigneeType;
    }

    public void setRealAssigneeType(String realAssigneeType) {
        this.realAssigneeType = realAssigneeType;
    }

    public User getRealAssignee() {
        return realAssignee;
    }

    public void setRealAssignee(User realAssignee) {
        this.realAssignee = realAssignee;
    }

    public boolean isAssigneeTypeValid() {
        return isAssigneeTypeValid;
    }

    public void setIsAssigneeTypeValid(boolean isAssigneeTypeValid) {
        this.isAssigneeTypeValid = isAssigneeTypeValid;
    }
}
