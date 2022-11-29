package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Member;
import model.dao.MemberDAO;

public class MemberManager {
	private static MemberManager userMan = new MemberManager();
	private MemberDAO memberDAO;
	private MemberAnalysis memberAanlysis;

	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
			memberAanlysis = new MemberAnalysis(memberDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return userMan;
	}
	
	public int create(Member member) throws SQLException, ExistingMemberException {
		if (memberDAO.existingUser(member.getId()) == true) {
			throw new ExistingMemberException(member.getId() + "는 존재하는 아이디입니다.");
		}
		return memberDAO.create(member);
	}

	public Member findMember(int memberId)
		throws SQLException, MemberNotFoundException {
		Member member = memberDAO.findMember(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException(memberId + "는 존재하지 않는 아이디입니다.");
		}		
		return member;
	}


	public boolean login(int userId, String password)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member member = findMember(userId);

		if (!member.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	
	
	public MemberDAO getUserDAO() {
		return this.memberDAO;
	}
}
