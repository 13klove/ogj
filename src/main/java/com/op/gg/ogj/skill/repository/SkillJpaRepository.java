package com.op.gg.ogj.skill.repository;

import com.op.gg.ogj.skill.model.entity.Skill;
import com.op.gg.ogj.skill.repository.queryDsl.dto.SkillDtoDslRepository;
import com.op.gg.ogj.skill.repository.queryDsl.entity.SkillDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillJpaRepository extends JpaRepository<Skill, Long>, SkillDtoDslRepository, SkillDslRepository {
}
