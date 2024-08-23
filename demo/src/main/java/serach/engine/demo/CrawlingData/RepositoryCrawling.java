package serach.engine.demo.CrawlingData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryCrawling extends JpaRepository<MetaData,String> {
    @Query(value = "SELECT title, url, rank, keyword_count " +
            "FROM ( " +
            "    SELECT title, url, rank, " +
            "           (LENGTH(lower(text)) - LENGTH(REPLACE(lower(text), lower(:keyword), ''))) / LENGTH(lower(:keyword)) AS keyword_count " +
            "    FROM meta_data " +
            "    WHERE lower(text) LIKE concat('%', lower(:keyword), '%') " +
            ") AS subquery " +
            "WHERE keyword_count >= 10 " +
            "ORDER BY rank DESC " +
            "LIMIT 30", nativeQuery = true)


    List<Object[]> findTopConcurrence(@Param("keyword") String keyword);
}
