package graph.travel;

import graph.Node;
import graph.road.RoadContext;

public class BusTravelStrategy implements ITravelStrategy {

    private final RoadContext context;

    public BusTravelStrategy(RoadContext context) {
        this.context = context;
    }

    @Override
    public Integer calculate(Integer src, Integer dst, Integer hated) {
        Node srcNode = context.getGraph().getGraph().get(src);
        Node dstNode = context.getGraph().getGraph().get(dst);
        context.getGraph().resetVisits();
        if (hated != null) {
            Node hatedNode = context.getGraph().getGraph().get(hated);
            hatedNode.setVisited(true);
        }
        context.getGraph().dijkstra(srcNode);
        if (dstNode.isVisited()) return dstNode.getDistance();
        return -1;
    }
}
