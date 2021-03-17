import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    public static void main(String args[])
    {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory=meta.getSessionFactoryBuilder().build();
        Session session=factory.openSession();
        Transaction t=session.beginTransaction();

        Event e1 = new Event();
        e1.setPre_event(0);
        e1.setNext_event(2);
        e1.setWhat("Approve");

        Event e2 = new Event();
        e2.setWhat("Approve/Reject");
        e2.setNext_event(3);
        e2.setPre_event(2);

        Event e3 = new Event();
        e3.setWhat("Fill");
        e3.setNext_event(2);
        e3.setPre_event(0);

        ArrayList<Event> list1=new ArrayList<Event>();
        ArrayList<Event> list2=new ArrayList<Event>();
        list1.add(e1);
        list1.add(e2);

        list2.add(e3);
        ArrayList<Event> doneByUser1 = new ArrayList<Event>();
        ArrayList<Event> doneByUser2 = new ArrayList<Event>();

        doneByUser1.add(e1);
        doneByUser1.add(e2);

        doneByUser2.add(e3);

        User user1 = new User();
        user1.setName("Samarth");
        user1.setPasswd("s");
        user1.setRole("Clerk");
        user1.setEvents(doneByUser1);


        User user2 = new User();
        user2.setName("Parul");
        user2.setPasswd("p");
        user2.setRole("Admin");
        user2.setEvents(doneByUser2);

        ArrayList<User> l=new ArrayList<User>();
        l.add(user1);
        l.add(user2);

        ArrayList<User> l2=new ArrayList<User>();
        l2.add(user1);

        ArrayList<Workflow> managedByDesigner1 = new ArrayList<Workflow>();


        Workflow workflow1 = new Workflow();
        workflow1.setNumber_of_events(3);
        workflow1.setName("Loan Application");
        workflow1.setEvents(list1);
        workflow1.setUsers(l);

        Workflow workflow2 = new Workflow();
        workflow2.setNumber_of_events(1);
        workflow2.setName("Approval process");
        workflow2.setEvents(list2);
        workflow2.setUsers(l2);

        managedByDesigner1.add(workflow1);
        managedByDesigner1.add(workflow2);

        Designer designer = new Designer();
        designer.setName("Abcd");
        designer.setPasswd("abcd");
        designer.setWorkflow(managedByDesigner1);

        session.persist(workflow1);
        session.persist(workflow2);
        session.persist(designer);
        session.persist(e1);
        session.persist(e2);
        session.persist(e3);
        session.persist(user1);
        session.persist(user2);
        t.commit();
        session.close();
        System.out.println("success");
    }
}
