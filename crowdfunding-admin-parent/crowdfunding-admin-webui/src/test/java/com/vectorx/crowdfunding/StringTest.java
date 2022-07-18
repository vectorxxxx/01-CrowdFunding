package com.vectorx.crowdfunding;

import com.vectorx.crowdfunding.util.CrowdUtil;
import org.junit.Test;

public class StringTest
{
    @Test
    public void testMd5(){
        String md5 = CrowdUtil.md5("123456");
        System.out.println(md5);
    }
}
