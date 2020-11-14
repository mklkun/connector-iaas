/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package org.ow2.proactive.connector.iaas.cloud.provider.azure;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.ow2.proactive.connector.iaas.model.Infrastructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.credentials.AzureTokenCredentials;
import com.microsoft.azure.management.Azure;


/**
 * @author ActiveEon Team
 * @since 07/03/17
 */
@Component
public class AzureServiceCache {

    public final String MANAGEMENT_URL = "https://management.azure.com/";

    @Autowired
    private AzureServiceBuilder serviceBuilder;

    private Map<Infrastructure, Azure> serviceCache;

    private Function<Infrastructure, Azure> buildComputeService = memoise(infrastructure -> serviceBuilder.buildServiceFromInfrastructure(infrastructure));

    public AzureServiceCache() {
        serviceCache = new ConcurrentHashMap<>();
    }

    public Azure getService(Infrastructure infrastructure) {
        return buildComputeService.apply(infrastructure);
    }

    public String getInfrastructureToken(Infrastructure infra) {
        try {
            return serviceBuilder.getTokenfromInfra(infra).getToken(MANAGEMENT_URL);
        } catch (IOException e) {
            throw new RuntimeException("Unable to determine token for infrastructure " + infra);
        }
    }

    public void removeService(Infrastructure infrastructure) {
        serviceCache.remove(infrastructure);
    }

    private Function<Infrastructure, Azure> memoise(Function<Infrastructure, Azure> fn) {
        return (a) -> serviceCache.computeIfAbsent(a, fn);
    }
}
