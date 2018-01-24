package me.mikechampion.controllers.models.data;

import me.mikechampion.controllers.models.Mechanic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MechanicDao extends CrudRepository<Mechanic, Integer> {
}