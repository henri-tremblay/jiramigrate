package pro.tremblay.jiramigrate.github;

/**
 * @author Henri Tremblay
 */
public class IssueResponse {
    private int id;
    private int number;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
