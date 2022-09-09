package com.ascenders.base.dao;

import org.springframework.data.repository.CrudRepository;

import com.ascenders.base.entity.AscendersTeam;

public interface AscendersTeamDao extends CrudRepository<AscendersTeam, Integer> {
	
	public AscendersTeam findByMemberId(int id);

}
