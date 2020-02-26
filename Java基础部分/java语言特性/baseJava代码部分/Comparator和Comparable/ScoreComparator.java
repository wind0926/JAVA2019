package base;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

    @Override
    public int compare(Score o1, Score o2) {
       /* if(o1.time>o2.time) return 1;
        else if(o1.time==o2.time) return 0;
        else return -1;*/
       return o1.score-o2.score;
    }
}
