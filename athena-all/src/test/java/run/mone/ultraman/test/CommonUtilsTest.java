package run.mone.ultraman.test;

import com.google.common.collect.Lists;
import run.mone.mone.ultraman.grpc.CommonUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author goodjava@qq.com
 * @date 2023/8/1 15:12
 */
public class CommonUtilsTest {

    @Test
    public void testGetIntersection() {
        CommonUtils commonUtils = new CommonUtils();
        List<String> list1 = Arrays.asList("a", "b", "c", "d");
        List<String> list2 = Arrays.asList("c", "d", "e", "f");
        List<String> expected = Arrays.asList("c", "d");
        List<String> actual = commonUtils.getIntersection(list1, list2);
        assertEquals(expected, actual);
    }


    @Test
    public void testSubList() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        System.out.println(getLastElements(list, 3));
    }


    //给你一个list,然后给定一个num,你从这个list中取后边的元素(如果num大于list长度,则取整个list)
    public List<String> getLastElements(List<String> list, int num) {
        if (list == null || list.isEmpty() || num <= 0) {
            return Collections.emptyList();
        }
        int startIndex = Math.max(list.size() - num, 0);
        return list.subList(startIndex, list.size());
    }


}
