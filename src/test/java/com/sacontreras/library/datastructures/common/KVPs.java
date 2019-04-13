package com.sacontreras.library.datastructures.common;

import com.sacontreras.library.BoxedType;
import com.sacontreras.library.CKeyValuePair;
import com.sacontreras.library.util.Generics;

public class KVPs {
    public static BoxedType<CKeyValuePair<String, String>>[] ary_kvp_ssn_name_expected_inorder;
    static {
        try {
            KVPs.ary_kvp_ssn_name_expected_inorder = Generics.<CKeyValuePair<String, String>>newBoxedTypeArray(5);
            KVPs.ary_kvp_ssn_name_expected_inorder[0] = new BoxedType<CKeyValuePair<String, String>>(new CKeyValuePair<String, String>("012345678", "Steven Contreras"));
            KVPs.ary_kvp_ssn_name_expected_inorder[1] = new BoxedType<CKeyValuePair<String, String>>(new CKeyValuePair<String, String>("123456789", "A Mistake!"));
            KVPs.ary_kvp_ssn_name_expected_inorder[2] = new BoxedType<CKeyValuePair<String, String>>(new CKeyValuePair<String, String>("-1878723", "ksdjsdkj ksjdksjkd ,m"));
            KVPs.ary_kvp_ssn_name_expected_inorder[3] = new BoxedType<CKeyValuePair<String, String>>(new CKeyValuePair<String, String>("9309u08u2034", "fjdnflkndfmnskdjvn sdsx"));
            KVPs.ary_kvp_ssn_name_expected_inorder[4] = new BoxedType<CKeyValuePair<String, String>>(new CKeyValuePair<String, String>("94t824398r", "fsdfs"));
        } catch (Exception e) {}
    }
}
