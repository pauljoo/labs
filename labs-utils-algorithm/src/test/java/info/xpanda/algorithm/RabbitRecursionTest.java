package info.xpanda.algorithm;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RabbitRecursionTest {
    @Test
    public void simple(){
        int actual = RabbitRecursion.simple(12);
        Assert.assertThat(actual, CoreMatchers.equalTo(41));
    }

    @Test
    public void simple2(){
        int actual = RabbitRecursion.simple2(12);
        Assert.assertThat(actual, CoreMatchers.equalTo(16));
    }
}