package com.op.gg.ogj.skill.repository;

import com.op.gg.ogj.skill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillJpaRepository extends JpaRepository<Skill, Long> {
}
