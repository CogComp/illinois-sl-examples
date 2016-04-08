package edu.illinois.cs.cogcomp.sl.applications.reranking;

import edu.illinois.cs.cogcomp.core.datastructures.Pair;
import edu.illinois.cs.cogcomp.core.math.MathUtilities;
import edu.illinois.cs.cogcomp.sl.core.IStructure;

import java.util.List;

/**
 * This class stores the individual scores for each candidate for a ranking example.
 *
 * @author Narender Gupta
 */
public class RankingSequence implements IStructure {
    private RankingInstance rankingInstance;
    private List<Double> rawScores;

    public RankingSequence(RankingInstance rankingInstance, List<Double> rawScores) throws IllegalArgumentException {
        if (rankingInstance == null || rawScores == null)
            throw new IllegalArgumentException("Null argument found!");
        else if (rankingInstance.featureList.size() != rawScores.size())
            throw new IllegalArgumentException("Size of RankingInstance and Scores don't match!");
        else if (rankingInstance.featureList.size() == 0)
            throw new IllegalArgumentException("Empty example found: " + rankingInstance.example_id);
        this.rankingInstance = rankingInstance;
        this.rawScores = rawScores;
    }

    public RankingInstance getRankingInstance() {
        return this.rankingInstance;
    }

    public List<Double> getRawScores() {
        return this.rawScores;
    }

    public List<Double> getNormalizedScores() {
        return MathUtilities.softmax(this.rawScores);
    }

    public int getMaxScoreIndex() {
        Pair<Integer, Double> max = MathUtilities.max(this.rawScores);
        return max.getFirst().intValue();
    }
}
