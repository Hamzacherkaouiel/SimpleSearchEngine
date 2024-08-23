package serach.engine.demo.CrawlingData;


import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
@Service
public class Crawler {
    public WebGraph webGraph;
   // private ArrayList<String> visitedPages;
    //private Queue<String> toVisitQueue;

    public Crawler() {
        this.webGraph = new WebGraph();
      //  this.visitedPages = new ArrayList<>();
        //this.toVisitQueue = new LinkedList<>();
    }

    public void Crawling(int level, String Path, ArrayList<String> Visited){
        Document D = this.Requested(Path, Visited);
        if(D!=null){
            webGraph.addPage(Path);
            webGraph.setPage(Path, D.title(), D.text());

        }
        if(level<1) {


            for (Element E : D.select("a[href]")) {
                String url = E.absUrl("href");
                if (!Visited.contains(url)) {
                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        try {
                            Document nextDoc = null;

                            nextDoc = Jsoup.connect(url).get();

                            if (nextDoc != null) {
                                webGraph.addoutlink(Path, url);
                                Crawling(level + 1, url, Visited);
                            }
                            //webGraph.addPage(url);

                        } catch (IOException e) {
                            System.out.println("Skipping inaccessible URL: " + url + " - " + e.getMessage());
                        }

                    }
                }
            }
        }
            ;
        }

    public Document Requested(String url, ArrayList<String> visited) {
        Connection C=Jsoup.connect(url);
        try {
            Document doc = C.get();
            String title = doc.select("head > title").text();
            String text=doc.text();
            System.out.println(text.length());
            System.out.println(title);
            System.out.println(doc.title());

            visited.add(url);
            if(text.length()<=1){
                System.out.println("ong");
            }
            return doc;
        } catch (IOException e) {
            System.out.println("Erreur lors de la connexion à l'URL : " + url + " - " + e.getMessage());
            return null;
        }
    }

    private boolean isValidUrl(String url) {
        return url != null && !url.isEmpty() &&
                (url.startsWith("http://") || url.startsWith("https://")) &&
                !url.startsWith("javascript:"); // Exclure les URLs JavaScript
    }


}
