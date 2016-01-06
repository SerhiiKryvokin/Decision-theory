package voting.ranker;

import voting.model.Candidate;
import voting.model.VotingTable;

import java.util.Arrays;

public class RelativeMajorityRanker implements Ranker {

    @Override
    public Candidate[] rank(VotingTable votingTable) {
        int size = votingTable.getPriorities().length;
        Candidate[] candidates = new Candidate[size];
        for (int i = 0; i < size; i++) {
            Candidate candidate = new Candidate((char) ('a' + i), 0);
            candidates[i] = candidate;
        }

        int n = votingTable.getVotesCount().length;
        for (int i = 0; i < n; i++) {
            char name = votingTable.getPriorities()[0][i];
            int votesCount = votingTable.getVotesCount()[i];
            Candidate candidate = candidates[name - 'a'];
            candidate.setRating(candidate.getRating() + votesCount);
        }
        Arrays.sort(candidates, (c1, c2) -> Integer.compare(c2.getRating(), c1.getRating()));
        return candidates;
    }
}
