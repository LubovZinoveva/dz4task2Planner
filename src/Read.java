import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс Read реализует интерфейс I_Load. 
 * Получая путь к файлу(path), через метод read() построчно считывает файл в ArrayList<String>.
 */
public class Read{
    String path;

    public Read(String path) {
        this.path = path;
    }

    
    public ArrayList<String> read(){
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return result;
    }

    public String getPath() {
        return path;
    }
}