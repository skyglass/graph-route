package skyglass.demo.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;

import skyglass.demo.model.RouteEdge;

public class EdgeRepositoryFactory {

	public static EdgeRepository create(FlowNode startNode) {
		Collection<RouteEdge> edges = getRecursivelyAllEdges(startNode);
		return new EdgeRepository(edges);
	}

	public static Collection<RouteEdge> getRecursivelyAllEdges(FlowNode startNode) {
		Set<RouteEdge> allEdges = new HashSet<>();
		addRecursivelyAllEdges(startNode.getOutgoing(), allEdges);
		return allEdges;
	}

	private static void addRecursivelyAllEdges(Collection<SequenceFlow> nodes, Set<RouteEdge> allEdges) {
		for (SequenceFlow node : nodes) {
			allEdges.add(new RouteEdge(node.getSource().getId(), node.getTarget().getId()));
			if (CollectionUtils.isNotEmpty(node.getTarget().getOutgoing())) {
				Collection<SequenceFlow> outgoing = node.getTarget().getOutgoing();
				boolean isCyclic = true;
				for (SequenceFlow outgoingNode : outgoing) {
					RouteEdge outgoingEdge = new RouteEdge(outgoingNode.getSource().getId(), outgoingNode.getTarget().getId());
					//If "allEdges" result doesn't contain at least one outgoing edge, then we should go recursively one level deeper,
					//until all outgoing edges are added to "allEdges"
					if (!allEdges.contains(outgoingEdge)) {
						isCyclic = false;
						break;
					}
				}
				if (!isCyclic) {
					addRecursivelyAllEdges(outgoing, allEdges);
				}
			}
		}
	}

}
