import com.yifeng.functionalprogramming.exercises.chapter3streams.Q7FindLargest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guoyifeng on 10/10/18
 */
public class Q7FindLargestTest {
    @Test
    public void test1() {
        String s1 = "asmiaj2jjjdkssdj";
        String s2 = "AIJDSIJIEIR";
        String s3 = "jsijdji9JISjijfSOKOS";
        String s4 = "121212121111111111111111111";

        List<String> list1 = Arrays.asList(s1, s2, s3, s4);

        List<String> list2 = new ArrayList<>();

        String res1 = Q7FindLargest.findLargest(list1);

        String res2 = Q7FindLargest.findLargest(list2);

        Assert.assertEquals(res1, s1);
        Assert.assertEquals(res2, null);
    }
}
