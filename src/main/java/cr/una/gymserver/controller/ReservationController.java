package cr.una.gymserver.controller;

import cr.una.gymserver.model.Reservation;
import cr.una.gymserver.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {
  private final ReservationService service;

  @PostMapping
  public ResponseEntity<?> create(
      @RequestParam("memId") Long memId,
      @RequestParam("clsId") Long clsId) {
    try {
      Reservation r = service.reserve(memId, clsId);
      return ResponseEntity.ok(r);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/available/{clsId}")
  public ResponseEntity<Integer> available(@PathVariable("clsId") Long clsId) {
    return ResponseEntity.ok(service.available(clsId));
  }
  
 @GetMapping
public ResponseEntity<?> list() {
  return ResponseEntity.ok(service.findAll());
}

@DeleteMapping("/{rsvId}")
public ResponseEntity<?> cancel(@PathVariable("rsvId") Long rsvId) {
  service.cancel(rsvId);
  return ResponseEntity.noContent().build(); // 204
}

}
