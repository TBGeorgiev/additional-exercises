import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class G_League<T extends G_TeamList> {
    private String leagueName;
    private ArrayList<T> teams;

    public G_League(String leagueName) {
        this.leagueName = leagueName;
        this.teams = new ArrayList<>();
    }

    public void addToLeague(T team) {
        if (teams.contains(team)) {
            teams.set(teams.indexOf(team), team);
        } else {
            teams.add(team);
        }
    }

    public void listTeamsSorted() {
        JOptionPane.showMessageDialog(null, "League: " + leagueName +
                "\nTeams: " + teams.get(0).getName() + ", " + teams.get(1).getName() + ", " +
                teams.get(2).getName());
        Collections.sort(teams);
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).randomizeResults();
            JOptionPane.showMessageDialog(null, "" +
                    teams.get(i).getName() + " - " + teams.get(i).numPlayers() +
                    "\n" + teams.get(i).getResultsFromRand() + "\nPlayers:\n" +
                    teams.get(i).getNames());
        }
    }
}
