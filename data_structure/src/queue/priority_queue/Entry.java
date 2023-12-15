package queue.priority_queue;

public class Entry<E> implements Priority{
    E val;
    int mPriority;

    public Entry(E val, int mPriority) {
        this.val = val;
        this.mPriority = mPriority;
    }

    @Override
    public int priority() {
        return mPriority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "val=" + val +
                ", mPriority=" + mPriority +
                '}';
    }
}
