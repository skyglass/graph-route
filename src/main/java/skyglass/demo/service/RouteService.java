package skyglass.demo.service;

import skyglass.demo.exception.BusinessRuleValidationException;
import skyglass.demo.model.RoutePath;

public interface RouteService {

	RoutePath getMinimalRoute(String startNode, String endNode) throws BusinessRuleValidationException;

}
