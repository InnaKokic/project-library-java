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

    public void addMember(String firstName, String lastName, String email, String memberType) {

        //validering:
        // Får bara välja member type standard eller premium

        memberRepository.addMember(firstName, lastName, email, memberType);

    }

    public void suspendMember(String memberEmail) {

        //Validering
        // Varning om medlem redan är suspended

        memberRepository.suspendMember(memberEmail);
    }
}

