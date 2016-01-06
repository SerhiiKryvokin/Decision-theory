package packing.packer;

import java.util.Arrays;

public class SortingPacker extends GenericPacker {

    private Packer packer;

    public SortingPacker(Packer packer) {
        this.packer = packer;
    }

    @Override
    public int pack(int[] items) {
        items = items.clone();
        Arrays.sort(items);
        for (int i = 0; i < items.length / 2; i++) {
            int tmp = items[i];
            items[i] = items[items.length - i - 1];
            items[items.length - i - 1] = tmp;
        }
        int ret = packer.pack(items);
        comparesCount = packer.getComparesCount() + (int) (2 * items.length * Math.log(items.length));
        return ret;
    }
}
