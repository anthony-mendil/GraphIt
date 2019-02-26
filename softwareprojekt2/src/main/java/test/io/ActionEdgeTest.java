package test.io;

import actions.edit.color.EditEdgesColorLogAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import log_management.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.swing.*")
@PrepareForTest(DatabaseManager.class)
public class ActionEdgeTest {
    private Syndrom syndrom;
    private SyndromGraph<Vertex, Edge> graph;
    private VisualizationViewer<Vertex, Edge> vv;
    private DatabaseManager database;


    @Before
    public void prepare(){
        syndrom = Syndrom.getInstance();
        graph = new SyndromGraph<>();
        syndrom.generateNew();
        GraphObjectsFactory factory = graph.getGraphObjectsFactory();
        //syndrom.generateNew();
        Sphere sphere1 = factory.createSphere(new Point2D.Double(10,10));
        Sphere sphere2 = factory.createSphere(new Point2D.Double(50,50));
        List<Sphere> spheres = Stream.of(sphere1, sphere2).collect(Collectors.toList());
        graph.getSpheres().addAll(spheres);

        Vertex vertex1 = graph.addVertex(new Point2D.Double(11,11), sphere1);
        Vertex vertex2 = graph.addVertex(new Point2D.Double(15,15), sphere1);
        Vertex vertex3 = graph.addVertex(new Point2D.Double(20,20), sphere1);


        Edge edge1 = factory.createEdge();
        Edge edge2 = factory.createEdge();
        Edge edge3 = factory.createEdge();
        graph.addEdge(edge1, vertex1, vertex2);
        graph.addEdge(edge2, vertex2, vertex3);
        graph.addEdge(edge3, vertex3, vertex1);

        syndrom.getVv().getGraphLayout().setGraph(graph);


    }




    @Test
    public void editEdgesColor(){
        /*DatabaseManager databaseManager = DatabaseManager.getInstance();
        try {
            PowerMockito.whenNew(DatabaseManager.class).withNoArguments().thenReturn(databaseManager);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*Color newColor = new Color(70,70,200);

        DatabaseManager database = DatabaseManager.getInstance();


        PowerMockito.when(DatabaseManager.getInstance()).thenReturn(database);
*/
        Whitebox.setInternalState(DatabaseManager.class, "instance", DatabaseManager.getInstance());
        PowerMockito.whenNew(DatabaseManager.class);

        //PowerMockito.doNothing().when(database).addEntryDatabase(any(Log.class));



        //HelperFunctions helperFunctions = PowerMockito.mock(HelperFunctions.class);
        Color newColor = new Color(70,70,200);
        EditEdgesColorLogAction action = new EditEdgesColorLogAction(newColor);
        //PowerMockito.doNothing().when(database).addEntryDatabase(action.createLog());
        //PowerMockito.doNothing().when(helperFunctions).setActionText("", true, true);

        PickedState<Edge> pickedState = syndrom.getVv().getPickedEdgeState();
        List<Edge> edges =  new ArrayList<>(graph.getEdges());
        Edge edge = edges.get(0);
        Color edgeColor = edge.getColor();

        Assert.assertNotSame(newColor, edgeColor);

        pickedState.pick(edge, true);


        action.action();

        Assert.assertEquals(newColor, edgeColor);

    }
}
