package dataaccess;

import business.Member;

public interface DataAccess {
	public void saveLibraryMember(String name, Member member);
	public Member readLibraryMember(String name);
}
