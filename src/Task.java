
public class Task<T> {
    private Integer id;
    private String name;
    private T deadline;
    private String textTask;
    private T createDateTime;
    
    public Task(Integer id, String name, T deadline, String textTask) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.textTask = textTask;
    }
    
    public Task(Integer id, String name, T deadline, String textTask, T createDateTime ) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.textTask = textTask;
        this.createDateTime = createDateTime;
        
    }

    public T getCreateDateTime() {
        return createDateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTextTask() {
        return textTask;
    }

    public T getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        
        return String.format("id - %d, ФИО - %s, Задача: %s, Дата создания - %s, Дедлайн - %s" , this.id, this.name, this.textTask, this.createDateTime, this.deadline);
    }
}
