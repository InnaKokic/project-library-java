package member;

import java.util.List;

public class MemberServices {

    MemberRepository memberRepository = new MemberRepository();

    public List<Member> showMemberProfile(String memberEmail){


        return memberRepository.showMemberProfile(memberEmail);

    }


    public void editProfileEmail(String memberEmail, String newEmail ) {

       memberRepository.editProfileEmail(newEmail, memberEmail);
    }
    public void editProfileName(String newFirstName, String newLastName, String memberEmail ) {

       memberRepository.editProfileName(newFirstName,newLastName, memberEmail);
    }
}
