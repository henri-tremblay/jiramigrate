package pro.tremblay.jiramigrate.github;

import com.google.common.base.Objects;

/**
 * @author Henri Tremblay
 */
public class Milestone {
    private int number;
    private String title;
    private String state;
    private String description;
    private String due_on; //"2012-10-09T23:39:01Z"

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDue_on() {
        return due_on;
    }

    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("title", title)
                .add("state", state)
                .add("description", description)
                .add("due_on", due_on)
                .toString();
    }
}
