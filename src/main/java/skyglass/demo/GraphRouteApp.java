package skyglass.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

import com.fasterxml.jackson.databind.ObjectMapper;

import skyglass.demo.exception.BusinessRuleValidationException;
import skyglass.demo.exception.PathNotFoundException;
import skyglass.demo.model.GraphXml;
import skyglass.demo.model.RoutePath;
import skyglass.demo.repository.EdgeRepository;
import skyglass.demo.repository.EdgeRepositoryFactory;
import skyglass.demo.service.RouteService;
import skyglass.demo.service.impl.RouteServiceImpl;

public class GraphRouteApp {

	private static String GRAPH_JSON = "graph.json";

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Usage: java -jar skyglass.demo.GraphRouteApp.jar startNode endNode");
			System.exit(-1);
		}
		String startNode = args[0];
		String endNode = args[1];

		ObjectMapper mapper = new ObjectMapper();
		String xml = null;
		try (InputStream inputStream = new FileInputStream(new File(GRAPH_JSON))) {
			GraphXml graphXml = mapper.readValue(inputStream, GraphXml.class);
			xml = graphXml.getBpmn20Xml();
			BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
			FlowNode startFlowNode = (FlowNode) modelInstance.getModelElementById(startNode);

			if (startFlowNode == null) {
				throw new PathNotFoundException(String.format("Node with id %s doesn't exist", startNode));
			}

			EdgeRepository edgeRepository = EdgeRepositoryFactory.create(startFlowNode);
			RouteService routeService = new RouteServiceImpl(edgeRepository);
			RoutePath routePath = routeService.getMinimalRoute(startNode, endNode);
			System.out.println(routePath.getPath());

		} catch (IOException e) {
			System.out.println("Unable to parse KamundaXml: " + e.getMessage());
			System.exit(-1);
		} catch (BusinessRuleValidationException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

	}

}
