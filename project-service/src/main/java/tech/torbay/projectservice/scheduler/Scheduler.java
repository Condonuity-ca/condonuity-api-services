package tech.torbay.projectservice.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import tech.torbay.projectservice.service.ProjectService;

public class Scheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	private ProjectService projectService;
	
	@Scheduled(fixedDelay = 10000)
	public void run() {
	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
	    try {
			UpdateProjectStatus();
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void UpdateProjectStatus() {
		// TODO Auto-generated method stub
		projectService.UpdateProjectStatus();
	}

}
