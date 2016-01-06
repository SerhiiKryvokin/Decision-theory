package voting.ranker;

import com.google.common.collect.MinMaxPriorityQueue;
import voting.model.Candidate;
import voting.model.VotingTable;

import java.util.ArrayList;
import java.util.Arrays;


public class AlternativeVotesRanker implements Ranker {
    @Override
    public Candidate[] rank(VotingTable votingTable) {
        int[] votesCount = votingTable.getVotesCount();
        char[][] priorities = votingTable.getPriorities();
        int prioritiesSize = priorities.length;
        Candidate[] candidates = new Candidate[prioritiesSize];
        for (int i = 0; i < prioritiesSize; i++) {
            Candidate candidate = new Candidate((char) ('a' + i), 0);
            candidates[i] = candidate;
        }

        int votesCountSize = votesCount.length;
        ArrayList<Character>[] prioritiesPerVotes = new ArrayList[votesCountSize];
        for (int i = 0; i < votesCountSize; i++) {
            prioritiesPerVotes[i] = new ArrayList<>();
            for (int j = 0; j < prioritiesSize; j++) {
                prioritiesPerVotes[i].add(priorities[j][i]);
            }

            char leaderName = priorities[0][i];
            Candidate leader = candidates[leaderName - 'a'];
            int votes = votesCount[i];
            leader.setRating(leader.getRating() + votes);
        }


        MinMaxPriorityQueue<Candidate> pq =
                MinMaxPriorityQueue.orderedBy(
                        (Candidate c1, Candidate c2) -> Integer.compare(c1.getRating(), c2.getRating())
                )
                        .create();

        pq.addAll(Arrays.asList(candidates).subList(0, prioritiesSize));

        while (pq.peekFirst().getRating() != pq.peekLast().getRating()) {
            Character name = pq.pollFirst().getName();
            for (int i = 0; i < votesCountSize; i++) {
                for (int j = 0; j < prioritiesPerVotes[i].size(); j++) {
                    if (prioritiesPerVotes[i].get(0) == name) {
                        prioritiesPerVotes[i].remove(0);
                        char nextName = prioritiesPerVotes[i].get(0);
                        Candidate nextCandidate = candidates[nextName - 'a'];
                        pq.remove(nextCandidate);
                        nextCandidate.setRating(nextCandidate.getRating() + votesCount[i]);
                        pq.add(candidates[nextName - 'a']);
                    } else {
                        prioritiesPerVotes[i].remove(name);
                    }
                }
            }
        }

        Arrays.sort(candidates, (c1, c2) -> Integer.compare(c2.getRating(), c1.getRating()));
        return candidates;
    }
}
