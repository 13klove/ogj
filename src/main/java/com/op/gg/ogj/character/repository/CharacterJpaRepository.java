package com.op.gg.ogj.character.repository;

import com.op.gg.ogj.character.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterJpaRepository extends JpaRepository<Character, Long> {
}
