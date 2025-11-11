package cr.una.gymserver.controller;

import cr.una.gymserver.model.GymClass;
import cr.una.gymserver.service.GymClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@CrossOrigin
public class GymClassController {
  private final GymClassService service;

  @GetMapping
  public ResponseEntity<List<GymClass>> list() {
    return ResponseEntity.ok(service.list());
  }
}
