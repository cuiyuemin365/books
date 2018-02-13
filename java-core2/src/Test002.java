public class Test002 {

    public static void main(String[] args) {
        test(16);
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    private static class Holder {

        /**
         * Enable alternative hashing of String keys?
         * <p>
         * <p>Unlike the other hash map implementations we do not implement a
         * threshold for regulating whether alternative hashing is used for
         * String keys. Alternative hashing is either enabled for all instances
         * or disabled for all instances.
         */
        static final boolean ALTERNATIVE_HASHING;

        static {
            // Use the "threshold" system property even though our threshold
            // behaviour is "ON" or "OFF".
            String altThreshold = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction(
                            "jdk.map.althashing.threshold"));

            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : Integer.MAX_VALUE;

                // disable alternative hashing if -1
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }

                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch (IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
            }
            ALTERNATIVE_HASHING = threshold <= MAXIMUM_CAPACITY;
        }
    }


    private static transient final int hashSeed = randomHashSeed(null);


    private static int randomHashSeed(Object instance) {
        if (sun.misc.VM.isBooted() && Holder.ALTERNATIVE_HASHING) {
            return sun.misc.Hashing.randomHashSeed(new Object());
        }

        return 0;
    }

    public static int hash(Object k) {
        int h = hashSeed;

        if ((0 != h) && (k instanceof String)) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    public static void test(int concurrencyLevel) {
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        int segmentShift = 32 - sshift;
        int segmentMask = ssize - 1;
        System.out.println(String.format("并发度:%s;sshift:%s;ssize:%s;segmentShift:%s;segmentMask:%s;",
                concurrencyLevel, sshift, ssize, segmentShift, Integer.toBinaryString(segmentMask)));
        for (int i = 0; i < 10; i++) {
            int hash = hash(new Integer((int) (Math.random() * 10000)));
            int k = (hash >>> segmentShift);
            int j = k & segmentMask;
            System.out.println("==============>");
            System.out.println("hash:" + hash + ":" + Integer.toBinaryString(hash));
            System.out.println("hash >>> segmentShift:" + k + ":" + Integer.toBinaryString(k));
            System.out.println("(hash >>> segmentShift) & segmentMask:" + j);
            System.out.println("<==============");
        }
    }

}
