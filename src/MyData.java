
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyData implements I_Data<HashSet<Task>, LinkedHashSet<HashSet<Task>>>  {

    Date date = new Date();

    @Override
    public LinkedHashSet<HashSet<Task>> read(String path) {
        LinkedHashSet<HashSet<Task>> result = new LinkedHashSet<>();
        HashSet<Task> low = new HashSet<>();
        HashSet<Task> medium = new HashSet<>();
        HashSet<Task> upper = new HashSet<>();
        try {
    
            JSONParser jsonParser = new JSONParser();
           
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("my_plans.json"), "UTF8"));
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
                String deadline1 = (String) jsonObject.get("deadline");
                
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 = dateFormat.parse(deadline1);
                Date date2 = dateFormat.parse(getDate2().toString());

                long milliseconds = date2.getTime() - date1.getTime();
                int days = (((int)milliseconds) / (24 * 60 * 60 * 1000));

                Integer idRead = (int) (long) (jsonObject.get("id"));
                
                if(days < 4){
                    
                    UpperPriority u = new UpperPriority(idRead, 
                    (String) jsonObject.get("ФИО"), (String) jsonObject.get("deadline"), 
                    (String) jsonObject.get("textTask"), (String) jsonObject.get("dateTime"));
                    upper.add(u);
                } else if (days < 8 && days > 3){
                    MediumPriority m = new MediumPriority(idRead, (String) jsonObject.get("ФИО"),
                                    (String) jsonObject.get("deadline"), (String) jsonObject.get("textTask"), 
                                    (String) jsonObject.get("dateTime"));
                    medium.add(m);
                }
                else if(days > 7){
                    LowPriority l = new LowPriority(idRead, (String) jsonObject.get("ФИО"), 
                                    (String) jsonObject.get("deadline"), (String) jsonObject.get("textTask"), 
                                    (String) jsonObject.get("dateTime"));
                    low.add(l);
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        result.add(upper);
        result.add(medium);
        result.add(low);
        return result;
    }

    @Override
    public void write(HashSet<Task> data) {
        JSONObject js = new JSONObject();
        JSONArray list = new JSONArray();
        

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("my_plans.json"), "UTF8"))) {
            for(Task t : data){
                int count = 0;
                String dateTime = getDate().toString() + getTime();

                js.put("id", t.getId());
                js.put("dateTime", dateTime);
                js.put("deadline", t.getDeadline().toString());
                js.put("textTask", t.getTextTask());
                js.put("ФИО", t.getName());
                list.add(js);
                bw.write(js.toJSONString());
                bw.write("\n");
                js.clear();
                
            }
            
            bw.flush();
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
             ex.getStackTrace();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    } 
    
    public StringBuilder getDate() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder st = new StringBuilder();
        st.append(calendar.get(Calendar.DAY_OF_MONTH));
        st.append("-");
        st.append(calendar.get(Calendar.MONTH));
        st.append("-");
        st.append(calendar.get(Calendar.YEAR));
        st.append("T");

        return st;
    }
    public StringBuilder getDate2() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder st = new StringBuilder();
        st.append(calendar.get(Calendar.DAY_OF_MONTH));
        st.append("-");
        st.append(calendar.get(Calendar.MONTH));
        st.append("-");
        st.append(calendar.get(Calendar.YEAR));

        return st;
    }


    public StringBuilder getTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder st = new StringBuilder();
        st.append(calendar.get(Calendar.HOUR));
        st.append(":");
        st.append(calendar.get(Calendar.MINUTE));
        st.append(":");
        st.append(calendar.get(Calendar.SECOND));
        st.append("Z");

        return st;
    }

    public StringBuilder createDeadline(Integer year, String month, String day) {
        
        StringBuilder dl = new StringBuilder();
        dl.append(day);
        dl.append("-");
        dl.append(month);
        dl.append("-");
        dl.append(year);

        return dl;
    }

}
