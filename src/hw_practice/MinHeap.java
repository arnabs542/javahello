package hw_practice;

public class MinHeap {

    private Integer[] arr;
    private int size;
    private static final int SCALE_FACTOR = 2;
    private static final int INITIAL_CAP = 16;


    public MinHeap(int cap) {
        arr = new Integer[cap];
        size = 0;
    }

    public void offer(Integer ele) {
        // ref -- add resize;
        if (size >= arr.legnth) {
            resize();
        }
        arr[size] = ele;
        size++;
        percolateUp(size-1);
    }

    private void resize() {
        Integer[] newArr = new Integer[SCALE_FACTOR * arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        // or can use arrayCopy
    }

    private void percolateUp(int idx) {
        while (idx > 0) {
            int par = idx / 2 - 1;
            if (arr[par] < arr[idx]) {
                swap(arr, par, idx);
                idx = par;
            } else {
                break;
            }
        }
    }

    private void percolateUp_self(int idx) {
        int par = idx / 2 - 1;
        int child = idx;
        while (par >= 0) {
            if (arr[par] < arr[child]) {
                swap(arr, par, child);
                child = par;
                par = child / 2 - 1;
            } else {
                break;
            }
        }
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }
        Integer result = arr[0];
        arr[0] = arr[size - 1];
        size--; // decrease the size and BEFORE percolateDown()
        percolateDown(0);
        return result;
    }

    private void percolateDown(int idx) {
        int par = idx;
        // ref idea, !!!the last parent, can guarantee leftChild is good, rightRight may be out of bound.
        while (par <= size / 2 - 1) {
            int leftChild = par * 2 + 1;
            int rightChild = par * 2 + 2;
            int candidate = leftChild; // ref idea, super!!!
            if (rightChild <= size - 1 && arr[rightChild] < arr[leftChild]) {
                candidate = rightChild;
            }
            if (arr[par] > arr[candidate]) {
                swap(arr, par, candidate);
                par = candidate;
            } else {
                break;
            }
        }
    }

    private void swap(Integer[] arr, int a, int b) {
        Integer tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer update(int idx, Integer ele) {
        if (idx >= size || idx < 0) {
            return null;
        }
        Integer old = arr[idx];
        arr[idx] = ele;
        percolateDown(idx);
        return old;
    }


    public void heapify() {
        if (size == 0) {
            return;
        }
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateUp(i);
        }
    }

}
