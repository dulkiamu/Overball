package entity;

import java.util.ArrayList;
import java.util.List;

public class ScoreList {
    private List<String> completedSets = new ArrayList<>();

    public void addSet(int p1Score, int p2Score) {
        completedSets.add("Player 1: " + p1Score + " - Player 2: " + p2Score);
    }

    public List<String> getCompletedSets() {
        return completedSets;
    }

    public void printSavedSets() {

        List<String> finishedSets = getCompletedSets();

        System.out.println("Completed Sets:");
        for (String set : finishedSets) {
            System.out.println(set);
         
        }

    }
}
