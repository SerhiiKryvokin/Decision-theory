package packing.packer;

public abstract class GenericPacker implements Packer {
    private int carrying;
    protected int comparesCount;

    public GenericPacker() {

    }

    public GenericPacker(int carrying) {
        this.carrying = carrying;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    @Override
    public int getComparesCount() {
        return comparesCount;
    }
}
