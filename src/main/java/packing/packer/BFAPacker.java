package packing.packer;

public class BFAPacker extends GenericPacker {
    public BFAPacker() {
    }

    public BFAPacker(int carrying) {
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
            } else if (containersUsed == 0) {
                containers[++containersUsed] -= items[i];
            } else {
                boolean fit = false;
                int minFreeIndex = -1;
                for (int j = 0; j < containersUsed; j++) {
                    comparesCount++;
                    if (containers[j] >= items[i]) {
                        if (minFreeIndex == -1) {
                            minFreeIndex = j;
                        } else if (containers[j] < containers[minFreeIndex]) {
                            minFreeIndex = j;
                        }
                        fit = true;
                    }
                }
                if (!fit) {
                    containers[++containersUsed] -= items[i];
                } else {
                    containers[minFreeIndex] -= items[i];
                }
            }
            comparesCount++;
        }
        return containersUsed + 1;
    }
}
