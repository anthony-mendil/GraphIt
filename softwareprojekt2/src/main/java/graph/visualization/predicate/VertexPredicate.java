package graph.visualization.predicate;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

public class VertexPredicate<V,E> implements Predicate<Context<Graph<V,E>,V>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        return false;
    }

    public VertexPredicate(){
        // TODO nach was wird gefiltert?
    }
}
