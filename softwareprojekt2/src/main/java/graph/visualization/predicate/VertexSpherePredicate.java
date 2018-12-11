package graph.visualization.predicate;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

public class VertexSpherePredicate<V,E> implements Predicate<Context<Graph<V,E>,V>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        return false;
    }

    public VertexSpherePredicate(){
        // TODO nach was wird gefiltert?
    }
}
