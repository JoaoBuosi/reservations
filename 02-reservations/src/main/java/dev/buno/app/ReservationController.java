package dev.buno.app;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
@RestController @RequestMapping("/api/reservations")
public class ReservationController {
 private final ReservationRepository repo;
 public ReservationController(ReservationRepository repo){this.repo=repo;}
 public record Request(String resourceName, LocalDateTime startsAt, LocalDateTime endsAt){}
 @GetMapping public List<Reservation> all(){return repo.findAll();}
 @PostMapping @Transactional public ResponseEntity<Reservation> create(@RequestBody Request r){
  if(r.resourceName()==null||r.startsAt()==null||r.endsAt()==null||!r.startsAt().isBefore(r.endsAt())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Dados/intervalo inválido");
  if(repo.overlaps(r.resourceName(),r.startsAt(),r.endsAt())>0) throw new ResponseStatusException(HttpStatus.CONFLICT,"Recurso já reservado nesse intervalo");
  return ResponseEntity.status(201).body(repo.save(new Reservation(r.resourceName(),r.startsAt(),r.endsAt())));
 }
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND);repo.deleteById(id);}
}
