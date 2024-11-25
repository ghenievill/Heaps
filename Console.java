import java.util.Arrays;
import java.util.Scanner;

public class PriorityQueue {
    private int[] heap;
    private int size;
    private int capacity;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }
    public void insert(int value) {
        if (size >= capacity) {
            System.out.println("Priority Queue is full!");
            return;
        }
        heap[size] = value;
        size++;
        heapifyUp();
    }
    public int removeMax() {
        if (size <= 0) {
            System.out.println("Priority Queue is empty!");
            return Integer.MIN_VALUE;
        }

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return max;
    }
    public void displayQueue() {
        System.out.println("Current Priority Queue: " + Arrays.toString(Arrays.copyOfRange(heap, 0, size)));
    }
    private void heapifyUp() {
        int index = size - 1;
        while (index > 0 && heap[parent(index)] < heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    
    private void heapifyDown() {
        int index = 0;
        while (leftChild(index) < size) {
            int largerChild = leftChild(index);
            if (rightChild(index) < size && heap[rightChild(index)] > heap[largerChild]) {
                largerChild = rightChild(index);
            }
            if (heap[index] >= heap[largerChild]) {
                break;
            }
            swap(index, largerChild);
            index = largerChild;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public static void main(String[] args) {
        Scanner gv = new Scanner(System.in);
        PriorityQueue priorityQueue = new PriorityQueue(10);

        System.out.println("Priority Queue Application using Max Heap");
        System.out.println("1. Insert Element");
        System.out.println("2. Remove Highest Priority Element");
        System.out.println("3. Display Priority Queue");
        System.out.println("4. Exit");

        while (true) {
            System.out.print("\nChoose an option: ");
            int choice = gv.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int value = gv.nextInt();
                    priorityQueue.insert(value);
                    break;
                case 2:
                    int removed = priorityQueue.removeMax();
                    if (removed != Integer.MIN_VALUE) {
                        System.out.println("Removed: " + removed);
                    }
                    break;
                case 3:
                    priorityQueue.displayQueue();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    gv.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}

