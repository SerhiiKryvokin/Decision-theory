package voting.model;

public class Candidate {
    char name;
    int rating;

    public Candidate() {
    }

    public Candidate(char name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return name == candidate.name;

    }

    @Override
    public int hashCode() {
        return (int) name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
