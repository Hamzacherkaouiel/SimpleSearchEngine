package serach.engine.demo.CrawlingData;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class Crawler {
    @Autowired
    RepositoryCrawling RC;

    public void Crawling(int level, String Path, ArrayList<String> Visited){
          if(level<=1){
              Document D=this.Requested(Path,Visited);
              for(Element E:D.select("a[href]")){
                  String url=E.absUrl("href");
                  if(!Visited.contains(url)){
                      if (url.startsWith("http://") || url.startsWith("https://"))
                          Crawling(level++,url,Visited);
                  }
              }
          }
    }

    public Document Requested(String Path,ArrayList<String> Visited){
        Connection C= Jsoup.connect(Path);
        try {
            Document D=C.timeout(5000).get();
            Visited.add(Path);
            MetaData MD=new MetaData();
            MD.setTitle(D.title());
            MD.setUrl(Path);
            MD.setText(D.text());
            this.RC.save(MD);
            System.out.println(D.title());
            return D;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
