import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

@Entity
public class WorkflowInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Workflow workflow;

//    @OneToMany
//    @JoinColumn(name="workflowInstanceId")
//    private List<EventInstance> eventInstanceList;
//

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public List<EventInstance> getEventInstanceList() {
//        return eventInstanceList;
//    }
//
//    public void setEventInstanceList(List<EventInstance> eventInstanceList) {
//        this.eventInstanceList = eventInstanceList;
//    }
}
