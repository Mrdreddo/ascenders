package com.ascenders.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascenders.base.dao.AscendersTeamDao;
import com.ascenders.base.entity.AscendersTeam;

@Service
public class AscendersTeamServiceImpl implements AscendersTeamService {

	@Autowired
	public AscendersTeamDao dao;

	@Override
	public List<AscendersTeam> getAllAscendersTeamMember() {
		List<AscendersTeam> allMembersDetails = (List<AscendersTeam>) dao.findAll();

		if (allMembersDetails.size() <= 0) {
			return null;
		} else {
			return allMembersDetails;
		}
	}

	@Override
	public boolean addMember(AscendersTeam ascendersTeam) {
		boolean isAdded = false;
		AscendersTeam save = dao.save(ascendersTeam);
		if (save != null) {
			isAdded = true;
		}
		return isAdded;
	}

	@Override
	public boolean deleteMember(int id) {
		boolean isDeleted = false;
        AscendersTeam member = dao.findByMemberId(id);
        if(member!=null) {
        	dao.delete(member);
        	isDeleted=true;
        }
		return isDeleted;
	}

}
