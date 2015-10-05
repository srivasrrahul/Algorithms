package ConnectionPool;


public class ConfigParam {
    private int min;
    private int max;
    private int expansionFactor;

    public ConfigParam(int min, int max, int expansionFactor) {
        this.min = min;
        this.max = max;
        this.expansionFactor = expansionFactor;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getExpansionFactor() {
        return expansionFactor;
    }
}
