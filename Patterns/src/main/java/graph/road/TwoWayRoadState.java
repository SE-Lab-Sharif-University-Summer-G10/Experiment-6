package graph.road;

import graph.Edge;
import graph.Node;

public class TwoWayRoadState extends RoadState {
    @Override
    public void updateContext(RoadContext context) {
        for (Node node : context.getGraph().getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(false);
            }
        }
        context.setRoadState(this);
    }
}
