package pro.tremblay.jiramigrate.github;

import java.util.List;

/**
 * @author Henri Tremblay
 */
public class RateLimit {
    private List<Limit> resources;
    private Limit rate;

    public List<Limit> getResources() {
        return resources;
    }

    public void setResources(List<Limit> resources) {
        this.resources = resources;
    }

    public Limit getRate() {
        return rate;
    }

    public void setRate(Limit rate) {
        this.rate = rate;
    }
}
