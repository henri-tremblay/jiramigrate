package pro.tremblay.jiramigrate.jira;

/**
 * Created by htrembl2 on 2/17/15.
 */
public class IssusStatusCategory {
    private String self;
    private String id;
    private String key;
    private String name;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
