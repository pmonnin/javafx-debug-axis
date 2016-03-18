package javafx.debug.axis;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

/**
 * Classes representing the three axis
 * x-axis is green
 * y-axis is red
 * z-axis is blue
 * @author Pierre Monnin
 */
public class DebugAxis extends Group {
    private Box xAxis;
    private Box yAxis;
    private Box zAxis;

    /**
     * Constructor for the debug axis
     * @param xMin the min x for the x-axis (values > 0 are ignored)
     * @param xMax the max x for the x-axis (values < 0 are ignored)
     * @param yMin the min y for the y-axis (values > 0 are ignored)
     * @param yMax the max x for the y-axis (values < 0 are ignored)
     * @param zMin the min z for the z-axis (values > 0 are ignored)
     * @param zMax the max z for the z-axis (values < 0 are ignored)
     */
    public DebugAxis(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        PhongMaterial red = new PhongMaterial(Color.RED);
        PhongMaterial green = new PhongMaterial(Color.GREEN);
        PhongMaterial blue = new PhongMaterial(Color.BLUE);

        double xmin = xMin > 0 ? 0 : xMin;
        double xmax = xMax < 0 ? 0 : xMax;
        double ymin = yMin > 0 ? 0 : yMin;
        double ymax = yMax < 0 ? 0 : yMax;
        double zmin = zMin > 0 ? 0 : zMin;
        double zmax = zMax < 0 ? 0 : zMax;

        this.xAxis = new Box(xmax - xmin, 0.1, 0.1);
        this.xAxis.setMaterial(green);
        this.xAxis.getTransforms().add(new Translate((xmax - xmin) / 2 + xmin, 0, 0));

        this.yAxis = new Box(0.1, ymax - ymin, 0.1);
        this.yAxis.setMaterial(red);
        this.yAxis.getTransforms().add(new Translate(0, (ymax - ymin) / 2 + ymin, 0));

        this.zAxis = new Box(0.1, 0.1, zmax - zmin);
        this.zAxis.setMaterial(blue);
        this.zAxis.getTransforms().add(new Translate(0, 0, (zmax - zmin) / 2 + zmin));

        this.getChildren().add(this.xAxis);
        this.getChildren().add(this.yAxis);
        this.getChildren().add(this.zAxis);
    }
}
