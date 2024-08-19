package graph.travel;

import graph.Node;
import graph.road.RoadContext;

public class TrainTravelStrategy implements ITravelStrategy {

    private RoadContext context;

    public TrainTravelStrategy(RoadContext context) {
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
        context.getGraph().bfs(srcNode);
        if (dstNode.isVisited()) return dstNode.getDistance() * context.getTrainTime();
        return -1;
    }
}
