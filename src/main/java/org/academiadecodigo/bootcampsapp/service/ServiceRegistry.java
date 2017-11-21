package org.academiadecodigo.bootcampsapp.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 13/11/17.
 */
public final class ServiceRegistry {

    public static ServiceRegistry serviceRegistryInstance = null;

    Map<String, Service> serviceList = new HashMap<>();

    private ServiceRegistry() {
    }

    public static ServiceRegistry getInstance() {

        if (serviceRegistryInstance == null) {

            serviceRegistryInstance = new ServiceRegistry();
        }

        return serviceRegistryInstance;
    }

    public void addServiceList(String serviceName, Service service) {

        serviceList.put(serviceName, service);
    }


    public Service getService(String serviceName) {

        return serviceList.get(serviceName);
    }
}
