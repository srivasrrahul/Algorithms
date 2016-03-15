package ByteCode;

/**
 * Created by Rahul on 1/27/16.
 */
public class Test {
    private int intArr[] = new int[10];
    public synchronized int top1()
    {
        return intArr[0];
    }
    public int top2()
    {
        synchronized (this) {
            return intArr[0];
        }
    }
}
