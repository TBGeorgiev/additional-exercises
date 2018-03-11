import javax.swing.*;
import java.io.*;
import java.util.*;

public class G_League<T extends G_TeamList> {
    private static String leagueName;
    private ArrayList<T> teams;
    private boolean toSave = false;
    private boolean saveComplete = false;
    private int teamNumber = 0;

    private boolean loadedResults = false;

    private int numberOfPlayers = 0;

    private int teamOnePlayerCount;
    private int teamTwoPlayerCount;
    private int teamThreePlayerCount;
    private ArrayList<String> teamNames = new ArrayList<>();
    private ArrayList<String> playerNames = new ArrayList<>();
    private ArrayList<Integer> playerAges = new ArrayList<>();
    private ArrayList<Integer> playerMatches = new ArrayList<>();

    private static ArrayList<String> savedLeagueNames = new ArrayList<>();

    public void setLoadedResults(boolean loadedResults) {
        this.loadedResults = loadedResults;
    }


    public int getTeamOnePlayerCount() {
        return teamOnePlayerCount;
    }

    public int getTeamTwoPlayerCount() {
        return teamTwoPlayerCount;
    }

    public int getTeamThreePlayerCount() {
        return teamThreePlayerCount;
    }

    public ArrayList<String> getTeamNames() {
        return teamNames;
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public ArrayList<Integer> getPlayerAges() {
        return playerAges;
    }

    public ArrayList<Integer> getPlayerMatches() {
        return playerMatches;
    }

    public void loadFiles() {
        int count = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(leagueName + ".txt")))) {
            scanner.nextLine(); //skip the first line containing the loaded league's name

            while (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                String[] firstSplit = firstLine.split(": ");
                String teamName = findName(firstSplit[1]);
                teamNames.add(teamName);
                switch (count) {
                    case 1: teamOnePlayerCount = numberOfPlayers; break;
                    case 2: teamTwoPlayerCount = numberOfPlayers; break;
                    case 3: teamThreePlayerCount = numberOfPlayers; break;
                }
                count++;
                for (int i = 0; i < numberOfPlayers; i++) {
                    String memberLine = scanner.nextLine();
                    String[] split = memberLine.split(": ");
                    String playerName = split[1].substring(0, split[1].length() - 4);
                    playerNames.add(playerName);
                    int playerAge = Integer.parseInt(split[2]);
                    playerAges.add(playerAge);

                }
                scanner.nextLine();
                String total = scanner.nextLine();
                String[] totalSplit = total.split(": ");
                int totalMatches = Integer.parseInt(totalSplit[1]);
                playerMatches.add(totalMatches);
                String stringWon = scanner.nextLine();
                int won = numberOfMembersFromString(stringWon);
                playerMatches.add(won);
                String stringLost = scanner.nextLine();
                int lost = numberOfMembersFromString(stringLost);
                playerMatches.add(lost);
                String stringDraw = scanner.nextLine();
                int draw = numberOfMembersFromString(stringDraw);
                playerMatches.add(draw);
                scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkList() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("leagueList.txt")))) {
            if (!scanner.hasNextLine()) {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getLeagueName() {
        loadAndPopulateLeagueList();
        String[] possibilities = new String[savedLeagueNames.size()];
        for (int i = 0; i < savedLeagueNames.size(); i++) {
            possibilities[i] = savedLeagueNames.get(i);
        }
        leagueName = (String) JOptionPane.showInputDialog(null,
                "Please choose an option from the menu below:", "Main Menu",
                JOptionPane.INFORMATION_MESSAGE, null, possibilities, possibilities[0]);
        return leagueName;
    }



    private int numberOfMembersFromString(String string) {
        String[] split = string.split("\\s+");
        int number = Integer.parseInt(split[2]);
        return number;
    }

    private String findName(String string) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != '(') {
                sb.append(string.charAt(i));
            } else {
                numberOfPlayers = Character.getNumericValue(string.charAt(i + 1));
                break;
            }
        }
        sb.setLength(sb.length() -1);
        return sb.toString();

    }

    public void saveLeague() {
        String leagueSaveFileName = leagueName + ".txt";
        Collections.sort(teams);
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter(leagueSaveFileName))) {
            locFile.write("League: " + leagueName + "\n");
            for (int i = 0; i < teams.size(); i++) {
                locFile.write("Team name: " + teams.get(i).getName() + " ("
                        + teams.get(i).getMembers().size() + " players) :\n");
                locFile.write(teams.get(i).getNames() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"
                        + teams.get(i).getResultsFromRand() + "\n");
                locFile.write("====================================\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        loadAndPopulateLeagueList();
        savedLeagueNames.add(leagueName);
        Collections.sort(savedLeagueNames);
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("leagueList.txt"))) {
            for (String savedLeagueName : savedLeagueNames) {
                locFile.write(savedLeagueName + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        saveComplete = true;
    }

    private static void loadAndPopulateLeagueList() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("leagueList.txt")))) {
            while (scanner.hasNextLine()) {
                String league = scanner.nextLine();
                savedLeagueNames.add(league);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSaveComplete() {
        return saveComplete;
    }

    public boolean isToSave() {
        return toSave;
    }

    public void doYouWantToSave() {
        int areYouSure = JOptionPane.showConfirmDialog(null, "Do you want to save?",
                "Title", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (areYouSure == JOptionPane.YES_OPTION) {
            this.toSave = true;
        }

    }

    public G_League(String leagueName) {
        this.leagueName = leagueName;
        this.teams = new ArrayList<>();
    }

    public void addToLeague(T team) {
        if (teams.contains(team)) {
            teams.set(teams.indexOf(team), team);
        } else {
            teams.add(team);
            if (!this.loadedResults) {
                teams.get(this.teamNumber).randomizeResults();
            }
        }
        this.teamNumber++;
    }

    public void listTeamsSorted() {
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No teams in the list! Returning to main menu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int teamSize = teams.size();
        Collections.sort(teams);

        switch (teamSize) {
            case 1: JOptionPane.showMessageDialog(null, "League: " + leagueName +
                    "\nTeam: " + teams.get(0).getName()); break;
            case 2: JOptionPane.showMessageDialog(null, "League: " + leagueName +
                    "\nTeams: " + teams.get(0).getName() + ", " + teams.get(1).getName()); break;
            case 3: JOptionPane.showMessageDialog(null, "League: " + leagueName +
                    "\nTeams: " + teams.get(0).getName() + ", " + teams.get(1).getName() + ", " +
                    teams.get(2).getName()); break;
        }

        for (int i = 0; i < teamSize; i++) {
            JOptionPane.showMessageDialog(null, "" +
                    teams.get(i).getName() + " - " + teams.get(i).numPlayers() +
                    "\n" + teams.get(i).getResultsFromRand() + "\nPlayers:\n" +
                    teams.get(i).getNames());
        }

    }
}
