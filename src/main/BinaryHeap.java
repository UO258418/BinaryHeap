package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    List<T> heap;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public BinaryHeap(T[] heap) {
        this();
        for(int i = 0; i < heap.length; i++)
            add(heap[i]);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public void add(T element) {
        if(element == null)
            throw new NullPointerException("Element to add cannot be empty");

        heap.add(element);
        filterUp(heap.indexOf(element));
    }

    private void filterUp(int pos) {
        int parent = (pos - 1) / 2;
        if(heap.get(parent).compareTo(heap.get(pos)) > 0) {
            Collections.swap(heap, pos, parent);
        }

        if(parent != 0)
            filterUp(parent);
    }

    private void filterDown(int pos) {
        int lowestChildPos = getLowestChild(pos);

        if(lowestChildPos != -1) {
            if(heap.get(pos).compareTo(heap.get(lowestChildPos)) > 0) {
                Collections.swap(heap, pos, lowestChildPos);
            }

            filterDown(lowestChildPos);
        }
    }

    private int getLowestChild(int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;

        if(right < heap.size()) {
            return heap.get(left).compareTo(heap.get(right)) <= 0 ? left : right;
        }

        if(left < heap.size()) {
            return left;
        }

        return -1;
    }

    public T getMin() {
        Collections.swap(heap, 0, heap.size() - 1);
        T element = heap.remove(heap.size() - 1);
        filterDown(0);
        return element;
    }

}
