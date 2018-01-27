package hw_practice;

import java.util.NoSuchElementException;

public class MinHeap {

    private int[] arr;
    private int size;

    public MinHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array can not be null or empty");
        }
        arr = array;
        size = arr.length;
        heapify();
    }


    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            // percolateUp(i); not up here
            percolateDown(i);
        }
    }

    public MinHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity can not be <= 0");
        }
        arr = new int[cap];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    private void percolateUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (arr[parent] > arr[idx]) {
                swap(arr, parent, idx);
                idx = parent;
            } else {
                break;
            }
        }
    }

    private void percolateDown(int idx) {
        while (idx <= size / 2 - 1) {
            int leftChild = idx * 2 + 1;
            int rightChild = idx * 2 + 1;
            int candidate = leftChild;
            if (rightChild <= size - 1 && arr[rightChild] < arr[leftChild]) {
                candidate = rightChild;
            }
            if (arr[idx] > arr[candidate]) { // don't forget this comparison
                swap(arr, idx, candidate);
                idx = candidate;
            } else {
                break;
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public int peek() {
        if (size == 0) {
            return -1;
        }
        return arr[0];
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty.");
        }
        int result = arr[0];
        arr[0] = arr[size - 1];
        size--; // decrease the size and BEFORE percolateDown()
        percolateDown(0);
        return result;
    }

    public void offer(int ele) {
        // ref -- add resize;
        if (size == arr.length) {
            resize();
        }
        arr[size] = ele;
        size++;
        percolateUp(size - 1);
    }

    private void resize() {
        int[] newArr = new int[(int)(arr.length * 1.5)];
        // actually here should avoid magic number, define a resizing factor!
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    // return the original value
    public int update(int idx, int ele) {
        if (idx > size - 1 || idx < 0) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        int result = arr[idx];
        arr[idx] = ele;
        if (result > ele) {
            percolateUp(idx);
        } else {
            percolateDown(idx);
        }
        return result;
    }



}


class MinHeap_self {

    private Integer[] arr;
    private int size;
    private static final int SCALE_FACTOR = 2;
    private static final int INITIAL_CAP = 16;


    public MinHeap_self(int cap) {
        arr = new Integer[cap];
        size = 0;
    }

    public void offer(Integer ele) {
        // ref -- add resize;
        if (size >= arr.length) {
            resize();
        }
        arr[size] = ele;
        size++;
        percolateUp(size - 1);
    }

    private void resize() {
        Integer[] newArr = new Integer[SCALE_FACTOR * arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
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
