package com.ascenders.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ascenders.base.entity.AscendersTeam;
import com.ascenders.base.jwt.JwtUtil;
import com.ascenders.base.model.Request;
import com.ascenders.base.model.Response;
import com.ascenders.base.model.ResponseMembersDetails;
import com.ascenders.base.service.AdminServiceImpl;
import com.ascenders.base.service.AscendersTeamService;

@RestController
public class AscendersController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	@Autowired
	private AscendersTeamService ascendersTeamService;

	@PostMapping("/authentication")
	public Response authentication(@RequestBody Request request) throws Exception {

		Response response = new Response();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getAdminEmail(), request.getAdminPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("Invalid id and password ", e);
		}

		UserDetails userDetails = adminServiceImpl.loadUserByUsername(request.getAdminEmail());
		String jwtToken = jwtUtil.generateToken(userDetails);

		response.setStatuscode(200);
		response.setMsg("Logged in");
		response.setToken(jwtToken);

		return response;
	}

	@GetMapping("/getAllDetails")
	public ResponseMembersDetails getAllMemberDetails() {
		ResponseMembersDetails membersDetails = new ResponseMembersDetails();
		List<AscendersTeam> allAscendersTeamMember = ascendersTeamService.getAllAscendersTeamMember();

		if (allAscendersTeamMember != null) {
			membersDetails.setStatusCode(200);
			membersDetails.setMsg("All Details for team members");
			membersDetails.setAscendersTeams(allAscendersTeamMember);
		} else {
			membersDetails.setStatusCode(400);
			membersDetails.setMsg("No data Found!!!");
		}
		return membersDetails;
	}

	@PostMapping("/addMember")
	public ResponseMembersDetails addMember(@RequestBody AscendersTeam ascendersTeam) {

		ResponseMembersDetails responseMembersDetails = new ResponseMembersDetails();

		if (ascendersTeamService.addMember(ascendersTeam)) {
			responseMembersDetails.setStatusCode(200);
			responseMembersDetails.setMsg("Member added succesfully!!!");
		} else {
			responseMembersDetails.setStatusCode(400);
			responseMembersDetails.setMsg("Something went wrong!!!!");
		}
		return responseMembersDetails;
	}

	@PostMapping("/deleteMember/{memberId}")
	public ResponseMembersDetails deleteMember(@PathVariable int memberId) {
		ResponseMembersDetails responseMembersDetails = new ResponseMembersDetails();

		if (ascendersTeamService.deleteMember(memberId)) {
			responseMembersDetails.setStatusCode(200);
			responseMembersDetails.setMsg("Member Deleted Succesfully!!");
		} else {
			responseMembersDetails.setStatusCode(400);
			responseMembersDetails.setMsg("Something went wrong!!!");
		}
		return responseMembersDetails;
	}

}
