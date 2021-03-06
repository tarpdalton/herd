/*
* Copyright 2015 herd contributors
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.finra.dm.rest;

import java.util.Arrays;

import org.junit.Test;

import org.finra.dm.model.dto.ConfigurationValue;
import org.finra.dm.model.api.xml.Parameter;
import org.finra.dm.model.api.xml.SystemJobRunRequest;
import org.finra.dm.model.api.xml.SystemJobRunResponse;
import org.finra.dm.service.systemjobs.FileUploadCleanupJob;
import org.finra.dm.service.systemjobs.JmsPublishingJob;

/**
 * This class tests various functionality within the Job REST controller.
 */
public class SystemJobRestControllerTest extends AbstractRestTest
{
    @Test
    public void testRunSystemJobFileUploadCleanup() throws Exception
    {
        // Create the system job run request.
        SystemJobRunRequest systemJobRunRequest = createSystemJobRunRequest(FileUploadCleanupJob.JOB_NAME,
            Arrays.asList(new Parameter(ConfigurationValue.FILE_UPLOAD_CLEANUP_JOB_THRESHOLD_MINUTES.getKey(), String.valueOf(INTEGER_VALUE))));

        // Request to run the system job.
        SystemJobRunResponse resultSystemJobRunResponse = systemJobRestController.runSystemJob(systemJobRunRequest);

        // Validate the returned object.
        validateSystemJobRunResponse(FileUploadCleanupJob.JOB_NAME,
            Arrays.asList(new Parameter(ConfigurationValue.FILE_UPLOAD_CLEANUP_JOB_THRESHOLD_MINUTES.getKey(), String.valueOf(INTEGER_VALUE))),
            resultSystemJobRunResponse);
    }

    @Test
    public void testRunSystemJobJmsPublisher() throws Exception
    {
        // Create the system job run request.
        SystemJobRunRequest systemJobRunRequest = createSystemJobRunRequest(JmsPublishingJob.JOB_NAME, null);

        // Request to run the system job.
        SystemJobRunResponse resultSystemJobRunResponse = systemJobRestController.runSystemJob(systemJobRunRequest);

        // Validate the returned object.
        validateSystemJobRunResponse(JmsPublishingJob.JOB_NAME, null, resultSystemJobRunResponse);
    }
}
