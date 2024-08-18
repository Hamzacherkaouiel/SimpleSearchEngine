package serach.engine.demo.CrawlingData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryCrawling extends JpaRepository<MetaData,String> {
    @Query(value = "SELECT title, url, (length(lower(text)) - length(replace(lower(text), :keyword, ' '))) / length(:keyword) AS concurrence " +
            "FROM meta_data " +
            "ORDER BY concurrence DESC " +
            "LIMIT 30", nativeQuery = true)
    List<Object[]> findTopConcurrence(@Param("keyword") String keyword);
}
