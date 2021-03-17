import javax.persistence.*;
import java.util.List;

@Entity
public class Designer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String passwd;

    @OneToMany
    @JoinColumn(name="designer_id")
    private List<Workflow> workflow;

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

    public List<Workflow> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(List<Workflow> workflow) {
        this.workflow = workflow;
    }
}
