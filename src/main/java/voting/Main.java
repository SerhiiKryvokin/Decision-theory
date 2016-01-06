package voting;

import voting.model.Candidate;
import voting.model.VotingTable;
import voting.ranker.AlternativeVotesRanker;
import voting.ranker.CondorcetRanker;
import voting.ranker.Ranker;
import voting.ranker.RelativeMajorityRanker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int CANDIDATES_COUNT = 4;
    private static final int VOTES_SIZE = 3;
    private static final String INPUT_DATA_FILE_NAME = "inputDataVoting";
    Scanner in;
    PrintWriter out;

    private void run() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(INPUT_DATA_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fileReader);
        in = new Scanner(br);
        out = new PrintWriter(System.out);


        int[] votesCount = readIntArr(VOTES_SIZE);
        char[][] priorities = readPriorities();

        VotingTable votingTable = new VotingTable(votesCount, priorities);

        List<Ranker> rankers = new ArrayList<Ranker>() {
            {
                add(new RelativeMajorityRanker());
                add(new CondorcetRanker());
                add(new AlternativeVotesRanker());
            }
        };


        for (int i = 0; i < rankers.size(); i++) {
            Ranker ranker = rankers.get(i);
            Candidate[] candidates = ranker.rank(votingTable);

            String rankerName = ranker.getClass().getSimpleName();
            out.println(rankerName + ":");
            for (int j = 0; j < candidates.length; j++) {
                out.println(candidates[j].getName() + ": " + candidates[j].getRating());
            }
            out.println("---------------------------------------------------------");
        }

        out.close();
    }

    private char[][] readPriorities() {
        char[][] priorities = new char[CANDIDATES_COUNT][VOTES_SIZE];
        for (int i = 0; i < CANDIDATES_COUNT; i++) {
            for (int j = 0; j < VOTES_SIZE; j++) {
                priorities[i][j] = in.next().charAt(0);
            }
        }
        return priorities;
    }

    private int[] readIntArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
