package serach.engine.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import serach.engine.demo.CrawlingData.MetaData;

import java.util.List;

@Controller
public class SearchEngine {
    @Autowired
    ServiceSide SS;
    @RequestMapping("/Searching")
    public String Printit(@RequestParam("userInput") String url, Model MM){
        String loweUrl=url.toLowerCase();
        List<MetaData> M=this.SS.getResults(loweUrl);
        MM.addAttribute("result",M);

        return "data";

    }
}
