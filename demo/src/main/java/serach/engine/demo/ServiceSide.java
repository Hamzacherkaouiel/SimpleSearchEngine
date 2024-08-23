package serach.engine.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serach.engine.demo.CrawlingData.MetaData;
import serach.engine.demo.CrawlingData.RepositoryCrawling;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceSide {
    @Autowired
    RepositoryCrawling RC;

   public List<MetaData> getResults(String KeyWord){
       List<MetaData> M=new ArrayList<>();
        List<Object[]> LO =this.RC.findTopConcurrence(KeyWord);
        for(Object [] O:LO){
            MetaData MD=new MetaData();
            MD.setTitle((String) O[0]);
            MD.setUrl((String) O[1]);
            M.add(MD);

        }
        return M;
      }
}
