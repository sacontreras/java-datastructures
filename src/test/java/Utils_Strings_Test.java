import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sacontreras.library.util.StringUtils;


public class Utils_Strings_Test {
	
	@Test
	@DisplayName("test_bitwise_XOR_swap_chars")
    public void test_bitwise_XOR_swap_chars() {
        String 
        	s_test = "Hello World!", 
        	s_expect = new StringBuilder(s_test).reverse().toString(),
        	s_result = StringUtils.bitwise_swap_chars(s_test);
      
        assertEquals(
    		s_expect,
    		s_result,
    		String.format("StringUtils.Useful.bitwise_XOR_swap_chars(\"%s\") should return \"%s\"", s_test, s_expect)
		);
    }
}
