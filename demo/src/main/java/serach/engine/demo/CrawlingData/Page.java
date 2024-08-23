package serach.engine.demo.CrawlingData;

import java.util.ArrayList;
import java.util.List;

public class Page {
    String url;
    String tile;
    List<Page> Pointed=new ArrayList<>();
    String contenu;

    public Page(String url) {
        this.url = url;

    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Page(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public List<Page> getPointed() {
        return Pointed;
    }

    public void setPointed(List<Page> pointed) {
        Pointed = pointed;
    }

   /* public double getRankPage() {
        return RankPage;
    }

    public void setRankPage(double rankPage) {
        RankPage = rankPage;
    }*/
    public void addPointer(Page P){
        this.Pointed.add(P);
    }
}
