import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class G_TeamList<T extends G_AbstractTeam> implements Comparable<G_TeamList<T>> {
    private String name;
    private int played = 0;
    private int won = 0;
    private int lost = 0;
    private int tied = 0;

    private ArrayList<T> members;

    public ArrayList<T> getMembers() {
        return members;
    }

    public G_TeamList(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getNames() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < members.size(); i++) {
            String name = members.get(i).getName();
            String toAppend = "" + (i + 1) + " - Name: " + name + " Age: " + members.get(i).getAge() + "\n";
            sb.append(toAppend);
        }
        return sb.toString();
    }

    public String getResultsFromRand() {
        return "Total matches: " + this.played + "\nWon: " + this.won + "\nLost: " + this.lost + "\nDraw: " + this.tied;
    }

    public void randomizeResults() {
        this.played = ThreadLocalRandom.current().nextInt(5, 10);
        this.won = ThreadLocalRandom.current().nextInt(0, this.played);
        this.lost = ThreadLocalRandom.current().nextInt(0, this.played - won);
        this.tied = this.played - this.won - this.lost;

    }

    public String getName() {
        return name;
    }

    public boolean ifExists(String name) {
        for (T member : members) {
            if (member.getName().equals(name)) {
                JOptionPane.showMessageDialog(null, "" + name +
                        " already exists. Player not added.", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public void addPlayer(T player, String name, String teamName) {
        if (!ifExists(name)) {
            members.add(player);
            JOptionPane.showMessageDialog(null, "" + name + " added successfully to the " + teamName + " team!");
        }
    }

    public int numPlayers() {
        return this.members.size();
    }

    @Override
    public int compareTo(G_TeamList<T> team) {
        if (this.members.size() > team.members.size()) {
            return -1;
        } else if (this.members.size() < team.members.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}
