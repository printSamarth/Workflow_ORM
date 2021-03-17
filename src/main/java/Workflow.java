import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany
    @JoinColumn(name="wid")
    private List<Event> events;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Workflow_User", joinColumns={@JoinColumn(referencedColumnName="id")},inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    private List<User> users;
    private Integer number_of_events;
    public Workflow(){}

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getNumber_of_events() {
        return number_of_events;
    }

    public void setNumber_of_events(Integer number_of_events) {
        this.number_of_events = number_of_events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
