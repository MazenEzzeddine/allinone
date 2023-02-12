import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;


public class Scaler {



    private static final Logger log = LogManager.getLogger(Scaler.class);


    public static void main(String[] args) {

        log.info("Sleeping for 1 minutes ");

        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Sleeping ended calling the scaler");

        int currentSize=1;

        for (int i = 5; i > 1; i--) {





            try (final KubernetesClient k8s = new DefaultKubernetesClient()) {
                log.info("Scaling +  {}", i );
                long before = System.currentTimeMillis();

                k8s.apps().deployments().inNamespace("default").withName("nginx-deployment").scale(++currentSize );
                long after= System.currentTimeMillis();

                log.info("nginx {}", after- before);
                before = System.currentTimeMillis();

                k8s.apps().deployments().inNamespace("default").withName("consumer").scale(i);

                after= System.currentTimeMillis();

                log.info("consumer {}", after- before);



            }



            log.info("scaling  ended bye bye");







        }

    }
}
