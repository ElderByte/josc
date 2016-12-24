package com.elderbyte.josc.core;


import org.junit.Assert;
import org.junit.Test;

public class JoscConnectionStringTest {

    @Test
    public void testSimple(){
        JoscConnectionString cs = JoscConnectionString.parse("josc:s3:http://myserver.com:9000");

        Assert.assertEquals("http://myserver.com:9000", cs.getHost());
        Assert.assertEquals("s3", cs.getProtocol());
    }

    @Test
    public void testVendorProperties(){
        JoscConnectionString cs = JoscConnectionString.parse("josc:s3:http://myserver.com:9000;some=prop;another=myprop");

        Assert.assertEquals("http://myserver.com:9000", cs.getHost());
        Assert.assertEquals("s3", cs.getProtocol());
        Assert.assertEquals("prop", cs.getProperties().getRequiredProperty("some"));
        Assert.assertEquals("myprop", cs.getProperties().getRequiredProperty("another"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMissingJsocFail(){
        JoscConnectionString.parse("s3:http://myserver.com:9000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingProtocolFail(){
        JoscConnectionString.parse("josc://myserver.com:9000");
    }

}