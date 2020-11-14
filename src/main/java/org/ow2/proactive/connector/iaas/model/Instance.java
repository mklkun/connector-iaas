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
package org.ow2.proactive.connector.iaas.model;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;


@EqualsAndHashCode(exclude = { "image", "number", "status", "hardware", "network", "credentials", "options",
                               "initScript" })
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Builder
public class Instance {

    private String id;

    private String tag;

    private String image;

    private String number;

    private String status;

    private Hardware hardware;

    private Network network;

    private InstanceCredentials credentials;

    private Options options;

    private InstanceScript initScript;

    private String customScriptUrl;

    @Override
    public String toString() {
        return String.format("{id=%s; tag=%s; image=%s; number=%s; status=%s; hardware=%s; network=%s; credential=%s; options=%s; initScript=%s; customScriptUrl=%s}",
                             id,
                             tag,
                             image,
                             number,
                             status,
                             hardware,
                             network,
                             credentials,
                             options,
                             initScript,
                             customScriptUrl);
    }

}
