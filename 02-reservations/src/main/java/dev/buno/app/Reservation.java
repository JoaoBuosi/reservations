package dev.buno.app;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Reservation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String resourceName;
 @Column(nullable=false) public LocalDateTime startsAt;
 @Column(nullable=false) public LocalDateTime endsAt;
 @Version public Long version;
 protected Reservation(){}
 public Reservation(String r,LocalDateTime s,LocalDateTime e){resourceName=r;startsAt=s;endsAt=e;}
}
