package pro.tremblay.jiramigrate.github;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Henri Tremblay
 */
public class Gist {
    private String url;
    private String description;
    private boolean isPublic;
    private Map<String, File> files = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }
}
