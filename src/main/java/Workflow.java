import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

//    @OneToMany
//    @JoinColumn(name="wid")
//    private List<Event> events;

    @ManyToOne
    @JoinColumn(name="designer_id")
    private Designer designer;
//    @OneToMany
//    @JoinColumn(name="wid")
//    private List<WorkflowInstance> workflowInstances;

    //@ManyToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="Workflow_User", joinColumns={@JoinColumn(referencedColumnName="id")},inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    //private List<User> users;
    private Integer number_of_events;

    public Workflow(){}

//    public List<WorkflowInstance> getWorkflowInstances() {
//        return workflowInstances;
//    }
//
//    public void setWorkflowInstances(List<WorkflowInstance> workflowInstances) {
//        this.workflowInstances = workflowInstances;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getNumber_of_events() {
        return number_of_events;
    }

    public void setNumber_of_events(Integer number_of_events) {
        this.number_of_events = number_of_events;
    }

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }
}
