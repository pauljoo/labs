package info.xpanda.labs.monitor.spring.service.impl;

import info.xpanda.labs.monitor.core.metric.MetricTypeEnum;
import info.xpanda.labs.monitor.core.transaction.TracerEventTypeEnum;
import info.xpanda.labs.monitor.spring.annonations.Event;
import info.xpanda.labs.monitor.spring.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    @Event(type = TracerEventTypeEnum.EVENT, name = "xpanda:trade", metric = MetricTypeEnum.COUNT, metricValue = "1")
    @Override
    public void trade() {
    }
}
