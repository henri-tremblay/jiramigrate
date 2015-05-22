package pro.tremblay.jiramigrate.jira;

import com.google.common.base.Objects;

public class ProjectCategory {
    private String self;
    private String id;
    private String description;
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("self", self)
            .add("id", id)
            .add("description", description)
            .add("name", name)
            .toString();
    }
}
