/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.text.ParseException;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import static org.quartz.JobBuilder.*;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.JobDetail;
import static org.quartz.TriggerBuilder.*;
import org.quartz.Trigger;

public class QuartzTest {

    static final Logger logger = Logger.getLogger(QuartzTest.class);

    public static void main(String[] args) throws ParseException {

        try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
// define the job and tie it to our ReinitiateReq class
            JobDetail job = newJob(ReinitiateReq.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("someProp", "someValue")
                    .build();
                Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 6 14 * * ?"))
                    .build();
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
           
            scheduler.shutdown(true);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
