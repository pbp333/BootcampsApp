import org.academiadecodigo.bootcampsapp.service.MockBootcampService;
import org.academiadecodigo.bootcampsapp.service.MockUserService;
import org.academiadecodigo.bootcampsapp.service.Service;
import org.academiadecodigo.bootcampsapp.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by codecadet on 21/11/17.
 */
public class MainTest {

    ServiceRegistry serviceRegistry;


    @Before
    public void setup() {

        serviceRegistry = ServiceRegistry.getInstance();
    }

    @Test
    public void getInstance() {

        ServiceRegistry serviceRegistry1 = ServiceRegistry.getInstance();

        assertEquals(serviceRegistry1, serviceRegistry);

    }

    @Test
    public void addServiceAndReturnTheSame() {

        Service service = new MockBootcampService();

        serviceRegistry.addServiceList("whatever", service);

        Service service1 = serviceRegistry.getService("whatever");

        assertEquals(service, service1);
    }

    @Test
    public void addService2ServicesSameKey() {

        Service service = new MockUserService();

        Service service1 = new MockBootcampService();

        serviceRegistry.addServiceList("whatever", service);

        serviceRegistry.addServiceList("whatever", service1);

        Service service2 = serviceRegistry.getService("whatever");

        assertEquals(service1, service2);
    }

    @Test
    public void addServiceSameServiceDifferentKey() {

        Service service = new MockUserService();

        serviceRegistry.addServiceList("whatever", service);

        serviceRegistry.addServiceList("whatever2", service);

        Service service1 = serviceRegistry.getService("whatever");

        Service service2 = serviceRegistry.getService("whatever2");

        assertEquals(service1, service2);
    }


}
