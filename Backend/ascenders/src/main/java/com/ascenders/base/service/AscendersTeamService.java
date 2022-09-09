package com.ascenders.base.service;

import java.util.List;

import com.ascenders.base.entity.AscendersTeam;

public interface AscendersTeamService {

	public List<AscendersTeam> getAllAscendersTeamMember();
	public boolean addMember(AscendersTeam ascendersTeam);
	public boolean deleteMember(int id);
}
