package voting.ranker;

import voting.model.Candidate;
import voting.model.VotingTable;

import java.util.Arrays;


public class CondorcetRanker implements Ranker {

    @Override
    public Candidate[] rank(VotingTable votingTable) {
        int prioritiesSize = votingTable.getPriorities().length;

        int votesCountSize = votingTable.getVotesCount().length;
        int[][] vs = new int[prioritiesSize][prioritiesSize];
        for (int i = 0; i < votesCountSize; i++) {
            boolean was[] = new boolean[prioritiesSize];
            for (int j = 0; j < prioritiesSize - 1; j++) {
                char cur = votingTable.getPriorities()[j][i];
                int index = cur - 'a';
                was[index] = true;
                for (int k = 0; k < prioritiesSize; k++) {
                    if (!was[k]) {
                        vs[index][k] += votingTable.getVotesCount()[i];
                    }
                }
            }
        }

        Candidate[] candidates = new Candidate[prioritiesSize];
        for (int i = 0; i < prioritiesSize; i++) {
            Candidate candidate = new Candidate((char) ('a' + i), 0);
            candidates[i] = candidate;
            int wins = 0;
            for (int j = 0; j < prioritiesSize; j++) {
                if (i != j && vs[i][j] > vs[j][i])
                    wins++;
            }
            candidate.setRating(wins);
        }

        Arrays.sort(candidates, (c1, c2) -> Integer.compare(c2.getRating(), c1.getRating()));
        return candidates;
    }
}
