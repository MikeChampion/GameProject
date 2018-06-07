package me.mikechampion.GameProject.controllers.models.data;

import me.mikechampion.GameProject.controllers.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlayerDao extends CrudRepository<Player, Integer> {

}