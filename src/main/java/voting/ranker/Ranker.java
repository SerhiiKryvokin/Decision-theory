package voting.ranker;

import voting.model.Candidate;
import voting.model.VotingTable;

public interface Ranker {
    Candidate[] rank(VotingTable votingTable);
}
