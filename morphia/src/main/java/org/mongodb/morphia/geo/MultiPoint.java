package org.mongodb.morphia.geo;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a series of points, which will saved into MongoDB as per the <a href="http://geojson.org/geojson-spec
 * .html#id5">GeoJSON
 * specification</a>. Therefore this entity will never have its own ID or store the its Class name.
 * <p/>
 * The factory for creating a MultiPoint is the {@code GeoJson.multiPoint} method.
 *
 * @see org.mongodb.morphia.geo.GeoJson#multiPoint(Point...)
 */
@Embedded
@Entity(noClassnameStored = true)
public class MultiPoint implements Geometry, PointCollection {
    private final List<Point> coordinates;

    @SuppressWarnings("UnusedDeclaration") // used by Morphia
    private MultiPoint() {
        this.coordinates = new ArrayList<Point>();
    }

    MultiPoint(final Point... points) {
        this.coordinates = Arrays.asList(points);
    }

    MultiPoint(final List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public List<Point> getPoints() {
        return coordinates;
    }

    /* equals, hashCode and toString. Useful primarily for testing and debugging. Don't forget to re-create when changing this class */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MultiPoint that = (MultiPoint) o;

        if (!coordinates.equals(that.coordinates)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return coordinates.hashCode();
    }

    @Override
    public String toString() {
        return "MultiPoint{"
               + "coordinates=" + coordinates
               + '}';
    }
}
