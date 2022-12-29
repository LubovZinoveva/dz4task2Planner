/*
 * Класс с самым низким приоритетом задачи
 */
public class LowPriority extends Task<String> {
    
        private Integer dayToDeadline = 3;
        
        public LowPriority(Integer id, String name, String deadline, String textTask, String createDateTime) {
        super(id, name, deadline, textTask, createDateTime);
}

        public Integer getDayToDeadline() {
            return dayToDeadline;
        }
    
    
}
