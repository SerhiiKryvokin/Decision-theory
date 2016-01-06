package voting.model;

public class VotingTable {
    private int[] votesCount;
    private char[][] priorities;

    public VotingTable() {
    }

    public VotingTable(int[] votesCount, char[][] priorities) {
        this.votesCount = votesCount;
        this.priorities = priorities;
    }

    public int[] getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int[] votesCount) {
        this.votesCount = votesCount;
    }

    public char[][] getPriorities() {
        return priorities;
    }

    public void setPriorities(char[][] priorities) {
        this.priorities = priorities;
    }
}
