package dev.buno.app;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
 @Query("select count(r) from Reservation r where r.resourceName=:resource and r.startsAt < :end and r.endsAt > :start")
 long overlaps(@Param("resource") String resource,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
}
