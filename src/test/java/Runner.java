import java.util.Map;

import com.fusioncharts.fusionboard.SqlConverter;
import com.fusioncharts.fusionboard.utils.Utils;

public class Runner {

  public static void main(String[] args) {
    String jsonString = "";
    Map<String, Object> jsonMap;

    try {
      jsonString = Utils.readJson("test1.json");
    } catch (Exception e) {
      e.printStackTrace();
    }

    if(!jsonString.isEmpty()) {
      SqlConverter sqlConverter = new SqlConverter(jsonString);
      String selectStatement = sqlConverter.convert();
      System.out.println(selectStatement);
    }
  }
}
