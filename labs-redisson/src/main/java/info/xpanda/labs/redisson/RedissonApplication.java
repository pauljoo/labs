package info.xpanda.labs.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonApplication {
    public static void main(String[] args) {
        try {
            Config config = new Config();
            config.useMasterSlaveServers()
                    //可以用"rediss://"来启用SSL连接
                    .setMasterAddress("redis://127.0.0.1:6380")
                    .addSlaveAddress("redis://127.0.0.1:6381")
            .setPassword("redis");

            while(true){
                Thread.sleep(1000);
                RedissonClient redisson = Redisson.create(config);
                RAtomicLong ll = redisson.getAtomicLong("test111");
                ll.set(3);
                System.out.println(ll.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
