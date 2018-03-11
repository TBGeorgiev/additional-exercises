import javax.swing.*;
import java.util.ArrayList;

public class GenericMain {

    private static boolean defaultTeams = true;

//    private static boolean corruptedList = false;

    private static String teamOneName;
    private static String teamTwoName;
    private static String teamThreeName;

    public static void main(String[] args) {


        JOptionPane.showMessageDialog(null, "Welcome to LeagueBuilder v0.001 pre-Alpha!", "Hello World!", JOptionPane.INFORMATION_MESSAGE);

        String team1Name = "Football Team";
        String team2Name = "Basketball Team";
        String team3Name = "VolleyBall Team";


        G_League<G_TeamList> league = entryMenu();

        //league.loadFiles();                 //loading for test only

        int defaultOrNewTeams = getDefaultOrNewTeams();
        if (defaultOrNewTeams == JOptionPane.YES_OPTION) {
            defaultTeams = false;
        }
        G_TeamList<G_FirstTeam> firstTeamTeamList = new G_TeamList<>(team1Name);
        teamOneName = team1Name;
        G_TeamList<G_SecondTeam> secondTeamTeamList = new G_TeamList<>(team2Name);
        teamTwoName = team2Name;
        G_TeamList<G_ThirdTeam> thirdTeamTeamList = new G_TeamList<>(team3Name);
        teamThreeName = team3Name;
        if (!defaultTeams) {
            team1Name = JOptionPane.showInputDialog("Enter the name of Team 1:");
            firstTeamTeamList = new G_TeamList<>(team1Name);
            teamOneName = team1Name;
            team2Name = JOptionPane.showInputDialog("Enter the name of Team 2:");
            secondTeamTeamList = new G_TeamList<>(team2Name);
            teamTwoName = team2Name;
            team3Name = JOptionPane.showInputDialog("Enter the name of Team 3:");
            thirdTeamTeamList = new G_TeamList<>(team3Name);
            teamThreeName = team3Name;
        }

        mainMenuMethods(league, firstTeamTeamList, secondTeamTeamList, thirdTeamTeamList);

    }

    private static void mainMenuMethods(G_League<G_TeamList> league, G_TeamList<G_FirstTeam> firstTeamTeamList, G_TeamList<G_SecondTeam> secondTeamTeamList, G_TeamList<G_ThirdTeam> thirdTeamTeamList) {
        boolean backToMainMenu;

        boolean quit = false;

        while (!quit) {
            String menuInputInGui = mainMenuOptions();
            if (menuInputInGui == null) {
                JOptionPane.showMessageDialog(null, "Please make a selection!", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            int menuInput = Integer.valueOf(menuInputInGui.substring(0, 1));
            switch (menuInput) {
                case 1:
                    league.setLoadedResults(false);
                    backToMainMenu = false;
                    while (!backToMainMenu) {
                        String name = JOptionPane.showInputDialog(
                                "Enter player name or press \"Cancel\" to return to the Main Menu:");
                        if (name == null) {
                            break;
                        } else if (name.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Cannot add an empty player!", "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        Object[] possibilities = {teamOneName, teamTwoName, teamThreeName};
                        String toSelect = (String) JOptionPane.showInputDialog(null,
                                "Choose a team: ", "Selection", JOptionPane.INFORMATION_MESSAGE,
                                null, possibilities, possibilities[0]);

                        if (toSelect == null) {
                            JOptionPane.showMessageDialog(null, "Player NOT added!", "Cancel", JOptionPane.WARNING_MESSAGE);
                            backToMainMenu = true;
                        } else if (toSelect.equals(teamOneName)) {
                            firstTeamTeamList.addPlayer(new G_FirstTeam(name), name, teamOneName, false);
                        } else if (toSelect.equals(teamTwoName)) {
                            secondTeamTeamList.addPlayer(new G_SecondTeam(name), name, teamTwoName, false);
                        } else if (toSelect.equals(teamThreeName)) {
                            thirdTeamTeamList.addPlayer(new G_ThirdTeam(name), name, teamThreeName, false);
                        }
                    }
                    break;

                case 2:
                    listingOfResults(league, firstTeamTeamList, secondTeamTeamList, thirdTeamTeamList);
                    break;

                case 3:
                    int exitPoint = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (exitPoint == JOptionPane.YES_OPTION) {
                        league.doYouWantToSave();
                        if (league.isToSave()) {
                            listingOfResultsNew(league, firstTeamTeamList, secondTeamTeamList, thirdTeamTeamList);
                            league.saveLeague();
                        }
                        if (league.isSaveComplete()) {
                            JOptionPane.showMessageDialog(null, "Save complete");
                        }
                        quit = true;
                        break;
                    } else {
                        break;
                    }
//                case 4:
//                    listingOfResultsNew(league, firstTeamTeamList, secondTeamTeamList, thirdTeamTeamList);
//                    league.saveLeague();
//                    JOptionPane.showMessageDialog(null, "League saved");
//                    quit = true;
//                    break;

            }
        }
        System.exit(0);
    }

    private static void listingOfResults(G_League<G_TeamList> league, G_TeamList<G_FirstTeam> firstTeamTeamList, G_TeamList<G_SecondTeam> secondTeamTeamList, G_TeamList<G_ThirdTeam> thirdTeamTeamList) {
        if (!firstTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(firstTeamTeamList);
        }
        if (!secondTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(secondTeamTeamList);
        }
        if (!thirdTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(thirdTeamTeamList);
        }
        league.listTeamsSorted();
    }

    private static void listingOfResultsNew(G_League<G_TeamList> league, G_TeamList<G_FirstTeam> firstTeamTeamList, G_TeamList<G_SecondTeam> secondTeamTeamList, G_TeamList<G_ThirdTeam> thirdTeamTeamList) {
        if (!firstTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(firstTeamTeamList);
        }
        if (!secondTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(secondTeamTeamList);
        }
        if (!thirdTeamTeamList.getMembers().isEmpty()) {
            league.addToLeague(thirdTeamTeamList);
        }
    }

    private static String mainMenuOptions() {
        String[] firstPossibilities = {"1: Add a player", "2: List all teams and their results",
                "3: Quit the application"};
        return (String) JOptionPane.showInputDialog(null,
                "Please choose an option from the menu below:", "Main Menu",
                JOptionPane.INFORMATION_MESSAGE, null, firstPossibilities, firstPossibilities[0]);
    }

    private static int getDefaultOrNewTeams() {
        return JOptionPane.showConfirmDialog(null,
                "Do you want to keep the default Teams (Football, " +
                        "Basketball and Volleyball) or do you want to create new ones?\nYes (create) / No (keep default)",
                "Confirm selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    private static G_League<G_TeamList> entryMenu() {
        String leagueName;
        Object[] selection = {"Create new League", "Load previous League"};
        String toSelect = (String) JOptionPane.showInputDialog(null,
                "Please choose an option ", "Main", JOptionPane.INFORMATION_MESSAGE,
                null, selection, selection[0]);
        if (toSelect == null) {
            System.exit(0);
        }
        if (toSelect.equals("Load previous League")) {
            if (!G_League.checkList()) {
                JOptionPane.showMessageDialog(null, "Save file is missing or corrupted!");
//                corruptedList = true;
                System.exit(0);
            }
            String nameOfLeague = G_League.getLeagueName();
            G_League<G_TeamList> league = new G_League<>(nameOfLeague);
            league.loadFiles();
            enterLoadedInformation(league);
            return league;

        }
        while (true) {
            leagueName = JOptionPane.showInputDialog(null, "Please enter a name for the League: ", "League", JOptionPane.INFORMATION_MESSAGE);
            if (leagueName == null) {
                int exitPoint = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (exitPoint == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else if (leagueName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Cannot add a League without a name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                break;
            }
        }
        return new G_League<>(leagueName);
    }

    private static void enterLoadedInformation(G_League<G_TeamList> league) {
        ArrayList<String> teams = new ArrayList<>(league.getTeamNames());
        int teamSize = teams.size();
        if (teamSize < 1) {
            JOptionPane.showMessageDialog(null, "Load file is empty!");
            entryMenu();
            return;
        }

        ArrayList<String> allTeamsPlayers = new ArrayList<>(league.getPlayerNames());
        ArrayList<Integer> allAges = new ArrayList<>(league.getPlayerAges());
        ArrayList<Integer> allScores = new ArrayList<>(league.getPlayerMatches());

        ArrayList<Integer> team1Matches = new ArrayList<>();
        ArrayList<Integer> team2Matches = new ArrayList<>();
        ArrayList<Integer> team3Matches = new ArrayList<>();

        G_TeamList<G_FirstTeam> firstTeamTeamList = new G_TeamList<>(teams.get(0));
        teamOneName = teams.get(0);
        if (teams.get(0) != null) {
        }
        G_TeamList<G_SecondTeam> secondTeamTeamList = new G_TeamList<>(teams.get(1));
        teamTwoName = teams.get(1);
        if (teams.get(1) != null) {
        }
        G_TeamList<G_ThirdTeam> thirdTeamTeamList = new G_TeamList<>(teams.get(2));
        teamThreeName = teams.get(2);
        if (teams.get(2) != null) {
        }





        int counter = 0;
        int numberOfTeamOnePlayers = league.getTeamOnePlayerCount();
        for (int i = 0; i < numberOfTeamOnePlayers; i++) {
            G_FirstTeam firstPlayer = new G_FirstTeam(allTeamsPlayers.get(counter));
            firstPlayer.setAge(allAges.get(counter));
            firstTeamTeamList.addPlayer(firstPlayer, allTeamsPlayers.get(counter), teams.get(0), true);
            counter++;

        }
        int numberOfTeamTwoPlayers = league.getTeamTwoPlayerCount();
        for (int i = 0; i < numberOfTeamTwoPlayers; i++) {
            G_SecondTeam secondPlayer = new G_SecondTeam(allTeamsPlayers.get(counter));
            secondPlayer.setAge(allAges.get(counter));
            secondTeamTeamList.addPlayer(secondPlayer, allTeamsPlayers.get(counter), teams.get(1), true);
            counter++;
        }
        int numberOfTeamThreePlayers = league.getTeamThreePlayerCount();
        for (int i = 0; i < numberOfTeamThreePlayers; i++) {
            G_ThirdTeam thirdPlayer = new G_ThirdTeam(allTeamsPlayers.get(counter));
            thirdPlayer.setAge(allAges.get(counter));
            thirdTeamTeamList.addPlayer(thirdPlayer, allTeamsPlayers.get(counter), teams.get(2), true);

            counter++;
        }
        for (int i = 0; i < 4; i++) {
            team1Matches.add(allScores.get(0));
            allScores.remove(0);
        }
        for (int i = 0; i < 4; i++) {
            team2Matches.add(allScores.get(0));
            allScores.remove(0);
        }
        for (int i = 0; i < 4; i++) {
            team3Matches.add(allScores.get(i));
        }
        firstTeamTeamList.setPlayed(team1Matches.get(0));
        firstTeamTeamList.setWon(team1Matches.get(1));
        firstTeamTeamList.setLost(team1Matches.get(2));
        firstTeamTeamList.setTied(team1Matches.get(3));

        secondTeamTeamList.setPlayed(team2Matches.get(0));
        secondTeamTeamList.setWon(team2Matches.get(1));
        secondTeamTeamList.setLost(team2Matches.get(2));
        secondTeamTeamList.setTied(team2Matches.get(3));

        thirdTeamTeamList.setPlayed(team3Matches.get(0));
        thirdTeamTeamList.setWon(team3Matches.get(1));
        thirdTeamTeamList.setLost(team3Matches.get(2));
        thirdTeamTeamList.setTied(team3Matches.get(3));

        league.setLoadedResults(true);

        int totalPlayers = numberOfTeamOnePlayers + numberOfTeamTwoPlayers + numberOfTeamThreePlayers;
        int numberOfTeams = teams.size();
        JOptionPane.showMessageDialog(null, "Load complete.\n" + numberOfTeams + " teams added. Total of: " + totalPlayers + " players.");
        mainMenuMethods(league, firstTeamTeamList, secondTeamTeamList, thirdTeamTeamList);
    }
}
