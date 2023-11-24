package data;

import member.Member;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members;
    private Member member;

    public void registerNewMember(String name, String address, int age, int memberID, String email, boolean membershipType, boolean isActive) {
        Member member = new Member(name, address, age, memberID, email, membershipType, isActive);
        members.add(member);
    }

    public void editMember(Member memberToEdit){
        for (int i = 0; i < members.size(); i++){
            Member member = members.get(i);
            if (member.isActive()) {
                members.set(i, memberToEdit);
            }
        }
    }

    public ArrayList<Member> getAllMembers() {
        return members;
    }


}