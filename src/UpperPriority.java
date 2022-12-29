

public class UpperPriority extends Task<String> {
    private Integer dayToDeadline = 3; 

    public UpperPriority(Integer id, String name, String deadline, String textTask, String createDateTime) {
        super(id, name, deadline, textTask, createDateTime);
    }

    public Integer getDayToDeadline() {
        return dayToDeadline;
    }


}
