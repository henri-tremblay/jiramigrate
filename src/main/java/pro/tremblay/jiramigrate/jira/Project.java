package pro.tremblay.jiramigrate.jira;

public class Project {
    private String self;
    private String id;
    private String key;
    private String name;

    private ProjectCategory projectCategory;

    public Project() {}

    private Project(String self, String id, String key, String name,
            String categoryName) {
        this.self = self;
        this.id = id;
        this.key = key;
        this.name = name;
        this.projectCategory = new ProjectCategory();
        projectCategory.setName(categoryName);
    }

    public static Project create(String id, String key, String name, String url, String categoryName) {
        return new Project(url, id, key, name, categoryName);
    }

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

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

}
