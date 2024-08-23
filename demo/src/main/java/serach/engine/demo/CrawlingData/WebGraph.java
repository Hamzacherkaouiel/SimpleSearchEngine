package serach.engine.demo.CrawlingData;

import java.util.*;
import java.util.function.Function;

public class WebGraph {
    Map<String,Page> M=new HashMap<>();

    public Page addPage(String url){
        return this.M.computeIfAbsent(url, Page::new);
    }
    public void addoutlink(String fromurl,String tourl){
        Page FromPage=addPage(fromurl);
        Page ToPage=addPage(tourl);
        //Page ToPage=new Page(tourl);
        FromPage.addPointer(ToPage);
        System.out.println("added");
        //System.out.println(FromPage.tile);
    }
    public Page getPage(String url){
        return this.M.get(url);
    }
    public void setPage(String url,String title,String text){
        this.M.get(url).setContenu(text);
        this.M.get(url).setTile(title);
        System.out.println(M.get(url).tile);
    }
    public List<Page> getAllGraph(){
        return new ArrayList<>(this.M.values());
    }
    public boolean ifPointed(String fromurl,String tourl){
        Page From=this.addPage(fromurl);
        Page To=addPage(tourl);
        if(From.getPointed().contains(To)){
            return true;
        }

        return false;
    }
}
