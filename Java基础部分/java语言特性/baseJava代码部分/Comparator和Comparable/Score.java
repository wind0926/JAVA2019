package base;

public class Score implements Comparable<Score> {
    public int score;
    public int time;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Score(int score, int time) {
        super();
        this.score = score;
        this.time = time;
    }

    @Override
    public int compareTo(Score o) {
        if(this.time>o.time) return 1;
        else if(this.time==o.time) return 0;
        else return -1;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", time=" + time +
                '}';
    }
}
