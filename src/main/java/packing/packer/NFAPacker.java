package packing.packer;

public class NFAPacker extends GenericPacker {

    public NFAPacker() {

    }

    public NFAPacker(int carrying) {
        super(carrying);
    }

    @Override
    public int pack(int[] items) {
        int n = items.length;
        int[] containers = new int[n];
        for (int i = 0; i < n; i++) {
            containers[i] = getCarrying();
        }
        comparesCount = 0;
        int containersUsed = 0;
        for (int i = 0; i < n; i++) {
            if (containers[containersUsed] >= items[i]) {
                containers[containersUsed] -= items[i];
            } else {
                containers[++containersUsed] -= items[i];
            }
            comparesCount++;
        }
        return containersUsed + 1;
    }
}
