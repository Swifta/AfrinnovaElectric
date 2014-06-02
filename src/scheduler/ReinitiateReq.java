/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import com.afrinnova.api.schoolfees.service.exception.AccountDAOException;
import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import java.io.IOException;
import java.util.logging.Level;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author princeyekaso
 */
public class ReinitiateReq implements Job {

    public ReinitiateReq() {
        // Instances of Job must have a public no-argument constructor.
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        JobDataMap data = context.getMergedJobDataMap();
        System.out.println("someProp = " + data.getString("someProp"));
       
    }

}