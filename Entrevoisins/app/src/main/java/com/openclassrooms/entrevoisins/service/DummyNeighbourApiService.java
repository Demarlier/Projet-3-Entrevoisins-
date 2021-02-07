package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getNeighboursFavorite() {
        List<Neighbour> neighboursFavorite = new ArrayList<>();
        for (Neighbour n : neighbours) {
            if (n.isFav()) {
                neighboursFavorite.add(n);
                }
        }
        return neighboursFavorite;
    }

    @Override
    public void toggleNeighbour(Neighbour neighbour) {
        Neighbour originalNeighbour = neighbours.get(neighbours.indexOf(neighbour));
        originalNeighbour.setFav(!neighbour.isFav());
    }


}
