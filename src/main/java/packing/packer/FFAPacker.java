package packing.packer;

public class FFAPacker extends GenericPacker {

    public FFAPacker() {
    }

    public FFAPacker(int carrying) {
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
                for (int j = containersUsed - 1; j >= 0; j--) {
                    comparesCount++;
                    if (containers[j] >= items[i]) {
                        fit = true;
                        containers[j] -= items[i];
                        break;
                    }
                }
                if (!fit) {
                    containers[++containersUsed] -= items[i];
                }
            }
            comparesCount++;
        }
        return containersUsed + 1;
    }
}
