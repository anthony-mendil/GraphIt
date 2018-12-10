package graph.visualization.predicate;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

public class EdgeArrowPredicate<V,E> implements Predicate<Context<Graph<V,E>,E>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphEContext) {
        return false;
    }
}
