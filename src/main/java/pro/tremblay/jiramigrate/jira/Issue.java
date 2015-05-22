package pro.tremblay.jiramigrate.jira;

/**
 * Created by htrembl2 on 1/12/15.
 */
public class Issue {
    private String id;
    private String self;
    private String key;
    private IssueFields fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public IssueFields getFields() {
        return fields;
    }

    public void setFields(IssueFields fields) {
        this.fields = fields;
    }
}
