///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.admis.scheduler;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.annotation.WebListener;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//import org.quartz.impl.StdSchedulerFactory;
//
///**
// *
// * @author Adeep My IT Solution Private Limited
// */
//@WebListener
//public class QuartzListener extends QuartzInitializerListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        super.contextInitialized(sce);
//        ServletContext ctx = sce.getServletContext();
//        try {
//            JobDetail jobDetail1 = JobBuilder.newJob(JobClass.class).build();
//            Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("simple1").withSchedule(
//                    CronScheduleBuilder.cronSchedule("0 0 */2 ? * *")).startNow().build();
//            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//            scheduler.scheduleJob(jobDetail1, trigger1);
//            scheduler.start();
//        } catch (Exception e) {
//            ctx.log("There was an error scheduling the job.", e);
//        }
//    }
//
//}