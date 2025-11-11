package cr.una.gymserver.service;

import cr.una.gymserver.model.GymClass;
import cr.una.gymserver.repository.GymClassRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymClassService {
  private final GymClassRepo repo;

  public List<GymClass> list() {
    return repo.findAll();
  }
}
