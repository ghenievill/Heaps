public class VillanuevaHeap {
    int[] heap;  
    int size;    


    public VillanuevaHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

 
    public void insert(int value) {
        heap[size] = value; 
        int current = size;
        size++;

        while (current > 0 && heap[current] > heap[(current - 1) / 2]) {
            int temp = heap[current];
            heap[current] = heap[(current - 1) / 2];
            heap[(current - 1) / 2] = temp;
            current = (current - 1) / 2; 
        }
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap[0];         
        heap[0] = heap[size - 1];   
        size--;

        int current = 0;
        while (current * 2 + 1 < size) { 
            int largerChild = current * 2 + 1; 
            if (current * 2 + 2 < size && heap[current * 2 + 2] > heap[largerChild]) {
                largerChild = current * 2 + 2; 
            }

            if (heap[current] > heap[largerChild]) {
                break;
            }

            int temp = heap[current];
            heap[current] = heap[largerChild];
            heap[largerChild] = temp;

            current = largerChild; 
        }

        return max;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        VillanuevaHeap Heap = new VillanuevaHeap(10); 


        Heap.insert(40);
        Heap.insert(20);
        Heap.insert(30);
        Heap.insert(10);

        System.out.println("Heap after insertions:");
        Heap.printHeap();

        System.out.println("Extracted max: " + Heap.extractMax());
        System.out.println("Heap after extraction:");
        Heap.printHeap();
    }
}
