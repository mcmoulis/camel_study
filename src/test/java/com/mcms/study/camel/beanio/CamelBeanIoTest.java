package com.mcms.study.camel.beanio;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelBeanIoTest extends CamelTestSupport {

    @Test
    public void testMultiLineContentUnmarshal() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);

        template.sendBody("direct:unmarshal", BeanIoTest.INPUT);

        mock.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                DataFormat format = new BeanIODataFormat("mapping.xml", "keyValueStream");
                from("direct:unmarshal").unmarshal(format).process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody());
                    }
                }).marshal(format).to("mock:result");
            }
        };
    }
}