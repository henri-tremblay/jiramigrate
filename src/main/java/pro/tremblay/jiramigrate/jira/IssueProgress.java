package pro.tremblay.jiramigrate.jira;

/**
 * @author Henri Tremblay
 */
public class IssueProgress {

    private int progress;
    private int total;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
