package pro.tremblay.jiramigrate.jira;

/**
 * Created by htrembl2 on 2/17/15.
 */
public class IssuePriority {
    private String self;
    private String name;
    private int id;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
