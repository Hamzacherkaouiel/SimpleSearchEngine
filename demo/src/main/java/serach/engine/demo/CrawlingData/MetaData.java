package serach.engine.demo.CrawlingData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {
    @Id
    @Column(length = 1000)
    public String Title;
    @Column(length = 1000)
    public  String Url;
    @Column(columnDefinition = "TEXT")
    public  String Text;


}
