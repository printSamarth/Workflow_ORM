import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String passwd;
    private String role;
    //@ManyToMany(mappedBy = "users")
    //private List<Workflow> workflows;

//    @OneToMany
//    @JoinColumn(name="User_id")
//    private List<Event> events;

//    @OneToMany
//    @JoinColumn(name="User_id")
//    private List<WorkflowInstance> workflowInstance;

//    @OneToMany
//    @JoinColumn(name="User_id")
//    private List<EventInstance> eventInstances ;
//
//    public List<EventInstance> getEventInstances() {
//        return eventInstances;
//    }
//
//    public void setEventInstances(List<EventInstance> eventInstances) {
//        this.eventInstances = eventInstances;
//    }

//    public List<WorkflowInstance> getWorkflowInstance() {
//        return workflowInstance;
//    }
//
//    public void setWorkflowInstance(List<WorkflowInstance> workflowInstance) {
//        this.workflowInstance = workflowInstance;
//    }

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
