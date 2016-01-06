package packing.packer;

public class WFAPacker extends GenericPacker {

    public WFAPacker() {
    }

    public WFAPacker(int carrying) {
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
                int maxFreeIndex = 0;
                for (int j = containersUsed - 1; j >= 0; j--) {
                    comparesCount++;
                    if (containers[j] >= items[i]) {
                        fit = true;
                        if (containers[j] > containers[maxFreeIndex]) {
                            maxFreeIndex = j;
                        }
                    }
                }
                if (!fit) {
                    containers[++containersUsed] -= items[i];
                } else {
                    containers[maxFreeIndex] -= items[i];
                }
            }
            comparesCount++;
        }
        return containersUsed + 1;
    }
}
