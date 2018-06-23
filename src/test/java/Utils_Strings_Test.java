import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sacontreras.library.Utils.Strings.Useful;

public class Utils_Strings_Test {
	
	@Test
	@DisplayName("test_bitwise_XOR_swap_chars")
    public void test_bitwise_XOR_swap_chars() {
        String 
        	test_string = "Hello World!", 
        	result_string__expect = "!dlroW olleH";
        
//        assertTrue(
//    		result_string__expect.compareTo(Useful.bitwise_XOR_swap_chars(test_string)) == 0, 
//			String.format("Utils.Strings.Useful.bitwise_XOR_swap_chars(\"%s\") should return \"%s\"", test_string, result_string__expect)
//		);
        assertEquals(
    		result_string__expect,
    		Useful.bitwise_XOR_swap_chars(test_string),
    		String.format("Utils.Strings.Useful.bitwise_XOR_swap_chars(\"%s\") should return \"%s\"", test_string, result_string__expect)
		);
    }
    
//    @Test
//    public void test_find_substring_permutations() {
//    }
}
