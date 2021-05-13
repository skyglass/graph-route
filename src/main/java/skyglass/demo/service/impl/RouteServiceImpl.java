package skyglass.demo.service.impl;

import java.util.List;
import java.util.Objects;

import skyglass.demo.exception.BusinessRuleValidationException;
import skyglass.demo.model.RouteEdge;
import skyglass.demo.model.RoutePath;
import skyglass.demo.repository.EdgeRepository;
import skyglass.demo.service.RouteService;

public class RouteServiceImpl implements RouteService {

	private EdgeRepository edgeRepository;

	public RouteServiceImpl(EdgeRepository edgeRepository) {
		this.edgeRepository = edgeRepository;
	}

	@Override
	public RoutePath getMinimalRoute(String startNode, String endNode) throws BusinessRuleValidationException {
		RoutePath result = getMinimalRoute(new RoutePath(), null, startNode, endNode);
		if (result == null) {
			return new RoutePath();
		}
		return result;
	}

	//recursive method
	private RoutePath getMinimalRoute(RoutePath currentPath, RoutePath currentMinPath, String startNode, String endNode)
			throws BusinessRuleValidationException {
		if (Objects.equals(startNode, endNode)) {
			return currentPath;
		}
		List<RouteEdge> edges = edgeRepository.find(startNode);
		for (RouteEdge edge : edges) {
			if (currentPath.alreadyBeenThere(edge)) {
				continue;
			}
			RoutePath nextRoutePath = currentPath.copy();
			nextRoutePath.addEdge(edge);
			if (Objects.equals(edge.getEndNode(), endNode)) {
				return nextRoutePath;
			}
			currentMinPath = findNextMinPath(nextRoutePath, currentMinPath, edge, endNode);
		}

		return currentMinPath;
	}

	private RoutePath findNextMinPath(RoutePath currentPath, RoutePath currentMinPath,
			RouteEdge currentEdge, String endNode) throws BusinessRuleValidationException {
		if (currentMinPath != null && currentPath.getLength() >= currentMinPath.getLength()) {
			//stop search in this direction, because there is already a better way
			return currentMinPath;
		}

		RoutePath path = getMinimalRoute(currentPath, currentMinPath,
				currentEdge.getEndNode(), endNode);
		if (path == null) {
			//dead end
			return currentMinPath;
		}
		if (currentMinPath == null || path.getLength() < currentMinPath.getLength()) {
			return path;
		}
		return currentMinPath;
	}

}
