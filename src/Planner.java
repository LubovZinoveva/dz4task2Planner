import java.util.HashSet;
import java.util.LinkedHashSet;

/*
 * Сам планнер
 */
public class Planner {
    public static void main(String[] args) {
        // создадим несколько задач и запишем в файл my_plans.json
        MyData myData = new MyData();
        String text1 = "Написать отчет";
        String text2 = "Разобрать статью";
        String text3 = "Провести серию опытов";
        Task t1 = new Task(1, "Шитиков Илья Васильевич", myData.createDeadline(2023, "03", "12"), text1);
        Task t2 = new Task(2, "Громова Мария Александровна", myData.createDeadline(2023, "01", "16"), text2);
        Task t3 = new Task(3, "Шитиков Илья Васильевич", myData.createDeadline(2023, "01", "5"), text2);
        Task t4 = new Task(4, "Шитиков Илья Васильевич", myData.createDeadline(2023, "01", "02"), text3);
        Task t5 = new Task(5, "Шитиков Илья Васильевич", myData.createDeadline(2023, "01", "30"), text3);
        Task t6 = new Task(6, "Громова Мария Александровна", myData.createDeadline(2022, "12", "30"), text1);
        Task t7 = new Task(7, "Громова Мария Александровна", myData.createDeadline(2023, "01", "14"), text2);
        Task t8 = new Task(8, "Шитиков Илья Васильевич", myData.createDeadline(2023, "01", "11"), text3);
        Task t9 = new Task(9, "Шитиков Илья Васильевич", myData.createDeadline(2023, "01", "01"), text1);

        HashSet<Task> tasks = new HashSet<>();
        tasks.add(t1); tasks.add(t2); tasks.add(t3); tasks.add(t4); tasks.add(t5); 
        tasks.add(t6); tasks.add(t7); tasks.add(t8); tasks.add(t9); 

        myData.write(tasks);

        // Теперь распарсим и выведем в консоль
        LinkedHashSet<HashSet<Task>> allTask = myData.read("my_plans.json");
        
        System.out.println("Задачи разбиты по блокам, от высокого приоритета к низкому");
        System.out.println();
        int count = 1;
        for(HashSet<Task> item : allTask){
            System.out.printf("БЛОК %d", count);
            System.out.println();
            for(Task obj : item){
                System.out.println(obj);
            }
            count++; 
            System.out.println("---------------");
            System.out.println();
        }
        
    }
}
