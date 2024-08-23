package serach.engine.demo.CrawlingData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*@Component
public class Main implements CommandLineRunner {

    @Autowired
    RepositoryCrawling RC;

    @Override
    public void run(String... args) throws Exception {
        Crawler crawler = new Crawler();
        String startUrl = "https://www.oracle.com/fr/java/";
        int maxDepth =0;
        int maxPagesPerLevel = 10;

        crawler.Crawling(maxDepth,startUrl,new ArrayList<String>());
        PageRanking pageRanking = new PageRanking(crawler.webGraph);

        double[][] ranks = pageRanking.CalculateRank();

        System.out.println("PageRanks:");
        List<Page> pages = crawler.webGraph.getAllGraph();
        try {
            System.out.println(pages.size());
            for (int i = 0; i < pages.size(); i++) {
                Page page = pages.get(i);
                System.out.println("Page " + page.getUrl() + " Rank: " + ranks[i][0]);

                MetaData MD = new MetaData();
                MD.setUrl(page.url);
                MD.setText(page.contenu);
                MD.setTitle(page.tile);
                MD.setRank(ranks[i][0]);

                System.out.println(MD.getTitle());

                RC.save(MD);
            }
        } catch (Exception e) {
            System.err.println("Error saving metadata: " + e.getMessage());
            e.printStackTrace();
        }

        // C.Crawling(0,Path,new ArrayList<String>());

        //List<MetaData> M=C.findAll();
       // System.out.println(M.size());
    }
}*/
