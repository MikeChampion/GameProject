package me.mikechampion.controllers.models.data;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GameDao extends CrudRepository<Game, Integer> { }