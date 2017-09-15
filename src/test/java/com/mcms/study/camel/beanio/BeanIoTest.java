package com.mcms.study.camel.beanio;

import java.util.Map;

import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.junit.Assert;
import org.junit.Test;

public class BeanIoTest {
    private static final String NEW_LINE = "\n";// "X";
    public static final String INPUT = "1234:Content starts from here" + NEW_LINE + "then continues" + NEW_LINE + "and ends here.";

    @Test
    public void testMultiLineContentUnmarshal() throws Exception {
        StreamFactory factory = StreamFactory.newInstance();
        factory.loadResource("mapping.xml");

        Unmarshaller unmarshaller = factory.createUnmarshaller("keyValueStream");
        @SuppressWarnings("unchecked")
        Map<String, Object> entryMap = (Map<String, Object>) unmarshaller.unmarshal(INPUT);
        System.out.println("Key: " + entryMap.get("key"));
        System.out.println("Value: " + entryMap.get("value"));

        Assert.assertEquals(3, entryMap.size());

    }

}