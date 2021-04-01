import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String args[]) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Scanner sc = new Scanner(System.in);
        System.out.println("To design workflow enter 1");
        System.out.println("To instantiate workflow enter 2");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Enter designer name ");
            String designer_name = sc.next();

            System.out.println("Enter designer pass ");
            String designer_pass = sc.next();
            System.out.println(designer_name + " " + designer_pass);
            String hql = "SELECT D.id FROM Designer D where D.name = :designer_name";
            Integer designer_id = (Integer) session.createQuery(hql).setParameter("designer_name", designer_name).uniqueResult();
            Designer designer = new Designer();

            if (designer_id != null) {
                designer.setName(designer_name);
                designer.setPasswd(designer_pass);
                designer.setId(designer_id);
                System.out.println("Designer id is: " + designer_id);
                Workflow workflow = new Workflow();
                workflow.setDesigner(designer);
                System.out.println("Enter name of workflow");
                workflow.setName(sc.next());
                System.out.println("Enter number of events ");
                workflow.setNumber_of_events(sc.nextInt());

                System.out.println("Setting events");
                Event e1 = new Event();
                e1.setPre_event(-1);
                e1.setNext_event(2);
                e1.setWhat("Review");
                e1.setWorkflow(workflow);

                Event e2 = new Event();
                e2.setWhat("Approve/Reject");
                e2.setNext_event(-1);
                e2.setPre_event(2);
                e2.setWorkflow(workflow);

                session.persist(workflow);
                session.persist(e1);
                session.persist(e2);


            } else {
                System.out.println("Designer does not exists.");
                return;
            }

        } else {
            String user_name;
            System.out.println("Enter user name ");
            user_name = sc.next();
            String check_user = "FROM User D where D.name = :user_name";
            User user = (User) session.createQuery(check_user).setParameter("user_name", user_name).uniqueResult();
            if (user == null) {
                System.out.println("User not found");
                return;
            }
            System.out.println("User found with id: " + user.getId());
            String hql = "SELECT W.name FROM Workflow W";
            Query query = session.createQuery(hql);
            List results = query.list();
            System.out.println("-------------Select Workflow----------");
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Workflow name: " + results.get(i));
            }

            System.out.println("Enter the workflow name you want to instantiate");
            String w_name = sc.next();
            Workflow workflow = (Workflow) session.createQuery("FROM Workflow W WHERE W.name = :w_name").setParameter("w_name", w_name).uniqueResult();
            if (workflow != null) {
                System.out.println("Workflow id is: " + workflow.getId());
                WorkflowInstance workflowInstance = new WorkflowInstance();
                workflowInstance.setUsers(user);
                workflowInstance.setWorkflow(workflow);
                session.persist(workflowInstance);

                Query events_query = session.createQuery("FROM Event e WHERE e.workflow =: workflow").setParameter("workflow", workflow);
                List events = events_query.list();
                System.out.println("-------------Enter users for each event------------");
                for (int i = 0; i < events.size(); i++) {
                    Event temp = (Event) events.get(i);
                    System.out.println("Enter name of employee who will do this event "+ temp.getWhat());
                    String e_name = sc.next();
                    User emp = (User) session.createQuery("FROM User D where D.name = :e_name").setParameter("e_name", e_name).uniqueResult();

                    EventInstance ev = new EventInstance();
                    ev.setEvent(temp);
                    ev.setUser(emp);
                    ev.setWorkflowInstance(workflowInstance);
                    session.persist(ev);
                }
                System.out.println("Workflow instance will start now");
            }
            else{
                System.out.println("Workflow not found");
                return;
            }


            t.commit();
            session.close();
            System.out.println("Success");

        }
    }
}
