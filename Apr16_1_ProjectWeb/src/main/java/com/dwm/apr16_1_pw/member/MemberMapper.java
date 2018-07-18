package com.dwm.apr16_1_pw.member;

import java.util.List;

public interface MemberMapper {
	public abstract int join(Member m);
	public abstract Member getMemberById(Member m);
	public abstract List<Member> getMemberById2(Member m);
	public abstract List<Member> getMemberById3(Member m);
	public abstract List<Member> getMemberByIdnPw(Member m);
	public abstract int updateMember(Member m);
	public abstract int updateMemberOpen(Member m);
	public abstract int deleteMember(Member m);
}
