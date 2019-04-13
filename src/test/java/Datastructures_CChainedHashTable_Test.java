import com.sacontreras.library.CKeyValuePair;
import com.sacontreras.library.datastructures.hashtable.CChainedHashTable;
import com.sacontreras.library.datastructures.hashtable.ChainedHashTableKeyHasher;
import com.sacontreras.library.datastructures.linkedlist.CLinkedList;
import com.sacontreras.library.datastructures.test.mock.KVPs;
import com.sacontreras.library.util.MathStuff;
import com.sacontreras.library.util.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Datastructures_CChainedHashTable_Test {

    private class CChainedHashTableStringKeyHasher extends ChainedHashTableKeyHasher<String> {
        @Override
        public int hash(String key, Integer modulus) {
            byte[] bytes = key.getBytes();
            long value = 0;
            for (int i = 0; i < bytes.length; i++) {
                value += ((long) bytes[i] & 0xffL) << (8 * i);
            }
            value = Math.abs(value);
            //System.out.println(String.format("CChainedHashTableStringKeyHasher::hash: bytes to val for key \"%s\" is %d", key, value));
            long hashval = (value % (long)modulus);
            //System.out.println(String.format("CChainedHashTableStringKeyHasher::%d mod %d == %d", value, modulus, hashval));
            return (int)hashval;
        }
    }

    public <TKey, TVal>
    void dump_hashtable(CChainedHashTable<TKey, TVal> chainedHashTable) {
        Iterator<CLinkedList<CKeyValuePair<TKey, TVal>>> it = chainedHashTable.iterator();
        int n = 0;
        CLinkedList<CKeyValuePair<TKey, TVal>> kvp_chain = null;
        while ((kvp_chain = it.next()) != null) {
            System.out.println(String.format("dump_hashtable: \tkvp_chain[%d]: %d nodes", n, kvp_chain.getSize()));
            int m = 0;
            for (CKeyValuePair<TKey, TVal> kvp: kvp_chain) {
                System.out.println(String.format("dump_hashtable: \t\t[%d]: key==\"%s\", val==\"%s\"", m, kvp.getKey(), kvp.getValue()));
                m++;
            }
            n++;
        }
    }

    @Test
    @DisplayName("test_CChainedHashTable_init")
    public void test_CChainedHashTable_init() {
        CChainedHashTable<String, String> chainedHashTable = new CChainedHashTable<String, String>(0, new CChainedHashTableStringKeyHasher());

        assertEquals(
            0,
            chainedHashTable.getSize()
        );

        assertEquals(
            CChainedHashTable.DEFAULT_CAPACITY,
            chainedHashTable.getCapacity()
        );

        chainedHashTable = new CChainedHashTable<String, String>(CChainedHashTable.DEFAULT_CAPACITY - 1, new CChainedHashTableStringKeyHasher());

        assertEquals(
            CChainedHashTable.DEFAULT_CAPACITY,
            chainedHashTable.getCapacity()
        );

        assertEquals(
            CChainedHashTable.LOAD_FACTOR_THRESHOLD,
            chainedHashTable.getLoadFactorThreshold()
        );

        assertEquals(
            0,
            chainedHashTable.getLoadFactor()
        );
    }

    @Test
    @DisplayName("test_CChainedHashTable_loadfactor__basic")
    public void test_CChainedHashTable_loadfactor__basic() {//note that this also tests add unique
        CChainedHashTable<String, String> chainedHashTable = new CChainedHashTable<String, String>(0, new CChainedHashTableStringKeyHasher());

        chainedHashTable.put("1", "a");
        assertEquals(
            (1/(float)CChainedHashTable.DEFAULT_CAPACITY),
            chainedHashTable.getLoadFactor()
        );

        chainedHashTable.put("2", "b");
        assertEquals(
            (2/(float)CChainedHashTable.DEFAULT_CAPACITY),
            chainedHashTable.getLoadFactor()
        );

        chainedHashTable.put("3", "c");
        assertEquals(
           (3/(float)CChainedHashTable.DEFAULT_CAPACITY),
            chainedHashTable.getLoadFactor()
        );

        chainedHashTable.put("4", "d");
        assertEquals(
            (4/(float)CChainedHashTable.DEFAULT_CAPACITY),
            chainedHashTable.getLoadFactor()
        );
    }

    @Test
    @DisplayName("test_CChainedHashTable_loadfactor_resize")
    public void test_CChainedHashTable_loadfactor_resize() {//note that this also tests add unique
        CChainedHashTable<String, String> chainedHashTable = new CChainedHashTable<String, String>(0, new CChainedHashTableStringKeyHasher());

        //randomize times we should resize - from 1 to 8 times AFTER initial allocation
        int times_to_resize = MathStuff.getInstance().randomIntVal(1, 8);
        int key_len = 0;
        int which_set = 0;
        char c = 0;
        String key, val;

        for (int i = 0; i < times_to_resize + 1; i++) {
            for (int j = (i == 0 ? 0 : (int)(CChainedHashTable.DEFAULT_CAPACITY * Math.pow(2, i - 1))); j < CChainedHashTable.DEFAULT_CAPACITY * Math.pow(2, i); j++) {
                key = StringUtils.gen_random_string(MathStuff.getInstance().randomIntVal(16, 32));
                val = StringUtils.gen_random_string(MathStuff.getInstance().randomIntVal(1, 32));
                chainedHashTable.put(key, val);
            }
        }
        //add one more kvp after we've resized times_to_resize times
        key = StringUtils.gen_random_string(MathStuff.getInstance().randomIntVal(16, 32));
        val = StringUtils.gen_random_string(MathStuff.getInstance().randomIntVal(1, 32));
        chainedHashTable.put(key, val);

        assertEquals(
            (int)(CChainedHashTable.DEFAULT_CAPACITY * Math.pow(2, times_to_resize + 1)),
            chainedHashTable.getCapacity()
        );

        //this section does not contribute to the test - it merely dumps the contents of the hashtable
        System.out.println(String.format("test_CChainedHashTable_loadfactor_resize: all done! chainedHashTable has %d chains with %d total items; iterating items!", chainedHashTable.bucket_count(), chainedHashTable.getSize()));
        dump_hashtable(chainedHashTable);
    }

    @Test
    @DisplayName("test_CChainedHashTable_operations")
    public void test_CChainedHashTable_operations() {//note that this also tests add unique
        CChainedHashTable<String, String> chainedHashTable = new CChainedHashTable<String, String>(0, new CChainedHashTableStringKeyHasher());

        CKeyValuePair<String, String> kvp = null;
        String val;

        //assert correct retrieval after EACH insert
        for (int i = 0; i < KVPs.ary_kvp_ssn_name_expected_inorder.length; i++) {
            kvp = KVPs.ary_kvp_ssn_name_expected_inorder[i].getValue();
            chainedHashTable.put(kvp.getKey(), kvp.getValue());
            System.out.println(String.format("test_CChainedHashTable_operations: chainedHashTable size==%d, load==%d, load_factor==%f", chainedHashTable.getSize(), chainedHashTable.getCapacity(), chainedHashTable.getLoadFactor()));

            val = chainedHashTable.get(kvp.getKey());
            System.out.println(String.format("test_CChainedHashTable_operations -- post-insert-inline-retrieval validation: val for key \"%s\" is \"%s\"", kvp.getKey(), val));
            assertEquals(kvp.getValue(), val);
        }

        //assert correct retrieval after BATCH insert
        for (int i = 0; i < KVPs.ary_kvp_ssn_name_expected_inorder.length; i++) {
            kvp = KVPs.ary_kvp_ssn_name_expected_inorder[i].getValue();
            val = chainedHashTable.get(kvp.getKey());
            System.out.println(String.format("test_CChainedHashTable_operations -- post-batch-insert-retrieval validation: val for key \"%s\" is \"%s\"", kvp.getKey(), val));
            assertEquals(kvp.getValue(), val);
        }

        //assert correct replacement
        kvp = KVPs.ary_kvp_ssn_name_expected_inorder[0].getValue();
        chainedHashTable.put(kvp.getKey(), kvp.getValue().toLowerCase());
        val = chainedHashTable.get(kvp.getKey());
        assertEquals(kvp.getValue().toLowerCase(), val);

        //now just dump
        System.out.println(String.format("test_CChainedHashTable_operations: all done! chainedHashTable has %d chains with %d total items; iterating items!", chainedHashTable.bucket_count(), chainedHashTable.getSize()));
        dump_hashtable(chainedHashTable);
    }
}
