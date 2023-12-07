package data;

import member.CompetitiveSwimmer;
import member.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {
    private File memberFile = new File("Members.csv");
    private File competitiveFile = new File("CompetitiveSwimmers.csv");
    private ArrayList<Member> memberFiles = new ArrayList<>();
    private ArrayList<CompetitiveSwimmer> competitiveFiles = new ArrayList<>();

    public ArrayList<Member> loadMembers() throws IOException {
        ArrayList<Member> temp = new ArrayList<>();
        try (Scanner myReader = new Scanner(memberFile, StandardCharsets.ISO_8859_1)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] memberData = data.split(",");

                if (memberData.length >= 7) {
                    String name = memberData[0].trim();
                    String address = memberData[1].trim();
                    LocalDate birthday = LocalDate.parse(memberData[2].trim());
                    int memberID = Integer.parseInt(memberData[3].trim());
                    String email = memberData[4].trim();
                    boolean isCompetitiveSwimmer = Boolean.parseBoolean(memberData[5].trim());
                    boolean isActive = Boolean.parseBoolean(memberData[6].trim());
                    int arrears = Integer.parseInt(memberData[7].trim());

                    Member member = new Member(name, address, birthday, memberID, email, isCompetitiveSwimmer, isActive, arrears);
                    temp.add(member);

                }
            }
        }
        this.memberFiles.addAll(temp);
        return temp;
    }

    public ArrayList<CompetitiveSwimmer> loadCompetitiveSwimmers() throws IOException {
        ArrayList<CompetitiveSwimmer> temp = new ArrayList<>();
        try (Scanner myReader = new Scanner(competitiveFile, StandardCharsets.ISO_8859_1)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] memberData = data.split(",");

                if (memberData.length >= 13) {
                    String name = memberData[0].trim();
                    String address = memberData[1].trim();
                    LocalDate birthday = LocalDate.parse(memberData[2].trim());
                    int memberID = Integer.parseInt(memberData[3].trim());
                    String email = memberData[4].trim();
                    boolean isCompetitive = Boolean.parseBoolean(memberData[5].trim());
                    boolean isActive = Boolean.parseBoolean(memberData[6].trim());
                    int arrears = Integer.parseInt(memberData[7].trim());
                    String coach = memberData[8].trim();
                    String discipline = memberData[9].trim();
                    String meet = memberData[10].trim();
                    String placement = memberData[11].trim();
                    //LocalDate dateWhenAchieved = LocalDate.now();
                    LocalDate dateWhenAchieved = LocalDate.parse(memberData[12].trim());
                    /*int minutes = Integer.parseInt(memberData[11].trim());
                    int seconds = Integer.parseInt(memberData[12].trim());
                    int hundredths = Integer.parseInt(memberData[13].trim());*/
                    String[] tid = memberData[13].split(":");
                    int minutes = Integer.parseInt(tid[0].trim());
                    int seconds = Integer.parseInt(tid[1].trim());
                    int hundredths = Integer.parseInt(tid[2].trim());


                    CompetitiveSwimmer competitiveSwimmer = new CompetitiveSwimmer(name, address, birthday, memberID, email, isCompetitive, isActive, restance, coach, discipline, meet, placement, dateWhenAchieved, minutes, seconds, hundredths);
                    temp.add(competitiveSwimmer);
                }
            }
        }
        this.competitiveFiles.addAll(temp);
        System.out.println("antal " + temp.size());
        return temp;
    }

//    public void saveMembers1(ArrayList<Member> members) {
//        try (PrintStream out = new PrintStream(memberFile)) {
//            for (Member member : members) {
//                out.println(member.toString());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveCompetitiveSwimmers(ArrayList<CompetitiveSwimmer> competitiveSwimmers) {
//        try (PrintStream out = new PrintStream(competitiveFile)) {
//            for (CompetitiveSwimmer swimmer : competitiveSwimmers) {
//                out.println(swimmer.toString());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<Member> getMemberFiles() {
        return  memberFiles;
    }

    public ArrayList<CompetitiveSwimmer> getCompetitiveFiles() {
        return competitiveFiles;
    }
}
