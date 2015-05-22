package pro.tremblay.jiramigrate.jira;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by htrembl2 on 2/17/15.
 */
public class IssueFields {

    // custom fields
    // customfield_10210": Number of attachments
    // customfield_10161": Participants
    // customfield_10163": Days since last comment,
    // customfield_10200": Source ID

    private IssueProgress progress;

    private String summary;

    private IssueType issuetype;

    private IssueCustomField customfield_10110;

    private IssueResolution resolution;

    private List<Version> fixVersions;

    private DateTime resolutiondate;

    private User reporter;

    private List<String> customfield_10161; // Participants

    private DateTime updated;

    private DateTime created;

    private String description;

    private IssuePriority priority;

    private DateTime dueDate;

    private IssueStatus status;

    private User assignee;

    private List<Attachment> attachment;

    private String customfield_10200; // Source ID

    private Project project;

    private List<Version> versions;

    private String environment;

    private IssueProgress aggregateprogress;

    private List<Component> components;

    private IssueComment comment;

    public IssueProgress getProgress() {
        return progress;
    }

    public void setProgress(IssueProgress progress) {
        this.progress = progress;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public IssueCustomField getCustomfield_10110() {
        return customfield_10110;
    }

    public void setCustomfield_10110(IssueCustomField customfield_10110) {
        this.customfield_10110 = customfield_10110;
    }

    public IssueResolution getResolution() {
        return resolution;
    }

    public void setResolution(IssueResolution resolution) {
        this.resolution = resolution;
    }

    public List<Version> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(List<Version> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public DateTime getResolutiondate() {
        return resolutiondate;
    }

    public void setResolutiondate(DateTime resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public List<String> getCustomfield_10161() {
        return customfield_10161;
    }

    public void setCustomfield_10161(List<String> customfield_10161) {
        this.customfield_10161 = customfield_10161;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public void setPriority(IssuePriority priority) {
        this.priority = priority;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

    public String getCustomfield_10200() {
        return customfield_10200;
    }

    public void setCustomfield_10200(String customfield_10200) {
        this.customfield_10200 = customfield_10200;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public IssueProgress getAggregateprogress() {
        return aggregateprogress;
    }

    public void setAggregateprogress(IssueProgress aggregateprogress) {
        this.aggregateprogress = aggregateprogress;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public IssueComment getComment() {
        return comment;
    }

    public void setComment(IssueComment comment) {
        this.comment = comment;
    }
}
