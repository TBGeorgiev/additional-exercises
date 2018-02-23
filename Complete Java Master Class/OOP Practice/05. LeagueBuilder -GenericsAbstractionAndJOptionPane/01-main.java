import javax.swing.JOptionPane;

public class GenericMain {

    public static void main(String[] args) {

        try {
            String team1Name = "Football Team";
            String team2Name = "Basketball Team";
            String team3Name = "VolleyBall Team";

            G_League league = entryMenu();
            int yesOrNo = JOptionPane.showConfirmDialog(null,
                    "Do you want to keep the default Teams (Football, " +
                    "Basketball and Volleyball) or do you want to create new ones?\nYes (create) / No (keep default)",
                    "Confirm selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            G_TeamList<G_FirstTeam> firstTeamTeamList = new G_TeamList<>(team1Name);
            G_TeamList<G_SecondTeam> secondTeamTeamList = new G_TeamList<>(team2Name);
            G_TeamList<G_ThirdTeam> thirdTeamTeamList = new G_TeamList<>(team3Name);
            if (yesOrNo == JOptionPane.YES_OPTION) {
                team1Name = JOptionPane.showInputDialog("Enter the name of Team 1:");
                firstTeamTeamList = new G_TeamList<>(team1Name);
                team2Name = JOptionPane.showInputDialog("Enter the name of Team 2:");
                secondTeamTeamList = new G_TeamList<>(team2Name);
                team3Name = JOptionPane.showInputDialog("Enter the name of Team 3:");
                thirdTeamTeamList = new G_TeamList<>(team3Name);
            }

            boolean backToMainMenu;

            boolean quit = false;

            while (!quit) {
                String[] firstPossibilities = {"1: Add a player", "2: List all teams and their results",
                        "3: Quit the application"};
                String menuInputInGui = (String) JOptionPane.showInputDialog(null,
                        "Please choose an option from the menu below:", "Selection",
                        JOptionPane.INFORMATION_MESSAGE, null, firstPossibilities, firstPossibilities[0]);
                if (menuInputInGui == null) {
                    JOptionPane.showMessageDialog(null, "Please make a selection!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                int menuInput = Integer.valueOf(menuInputInGui.substring(0, 1));
                switch (menuInput) {
                    case 1:
                        backToMainMenu = false;
                        while (!backToMainMenu) {
                            String name = JOptionPane.showInputDialog(
                                    "Enter player name or press \"Cancel\" to return to the main menu:");
                            if (name == null) {
                                break;
                            }
                            Object[] possibilities = {team1Name, team2Name, team3Name};
                            String toSelect = (String) JOptionPane.showInputDialog(null,
                                    "Choose a team: ", "Selection", JOptionPane.INFORMATION_MESSAGE,
                                    null, possibilities, possibilities[0]);

                            if(toSelect == null) {
                                backToMainMenu = true;
                            }
                            else if (toSelect.equals(team1Name)) {
                                firstTeamTeamList.addPlayer(new G_FirstTeam(name), name);
                            }
                            else if (toSelect.equals(team2Name)) {
                                secondTeamTeamList.addPlayer(new G_SecondTeam(name), name);
                            }
                            else if (toSelect.equals(team3Name)) {
                                thirdTeamTeamList.addPlayer(new G_ThirdTeam(name), name);
                            }
                            else if (toSelect.equals(team3Name)) {
                                thirdTeamTeamList.addPlayer(new G_ThirdTeam(name), name);
                            }
                        }
                        break;

                    case 2:
                        league.addToLeague(firstTeamTeamList);
                        league.addToLeague(secondTeamTeamList);
                        league.addToLeague(thirdTeamTeamList);
                        league.listTeamsSorted();
                        break;

                    case 3:
                        int exitPoint = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (exitPoint == JOptionPane.YES_OPTION) {
                            quit = true;
                            break;
                        }
                        else {
                            break;
                        }

                }
            }
            System.exit(0);
        } catch (NumberFormatException e) {
            System.exit(0);
        }

    }

    private static G_League entryMenu() {
        String leagueName = JOptionPane.showInputDialog(null, "Please enter a name for the League: ", "League", JOptionPane.INFORMATION_MESSAGE);
        if (leagueName == null) {
            System.exit(0);
        }
        G_League league = new G_League(leagueName);
        return league;
    }
}