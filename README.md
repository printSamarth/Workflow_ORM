It has 4 classes. Designer, Workflow, Events, User.
Relation between them are as follows:
Designer ManyToOne Workflow.
Workflow ManyToMany User.
Workflow ManyToOne Events.
User ManyToOne Events. (User does the work).
