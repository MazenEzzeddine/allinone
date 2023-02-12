
import  org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

public class RebalanceTiming implements ConsumerRebalanceListener {

    private static final Logger log = LogManager.getLogger(RebalanceTiming.class);


    Instant start;
    Instant end;
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {

        start = Instant.now();
        log.info("Rebalance started {}", start.toString());

    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        end = Instant.now();

        log.info("Rebalance  completed + {}", end.toString());

        if (start != null) {
            log.info("Rebalance took {}", end.toEpochMilli() - start.toEpochMilli());
        }


    }
}
