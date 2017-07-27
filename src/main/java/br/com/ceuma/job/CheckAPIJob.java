package br.com.ceuma.job;

import br.com.ceuma.business.ICheckAPIBO;
import br.com.ceuma.facade.IFacade;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marcus on 26/07/17.
 */
public class CheckAPIJob implements Job {

    @Autowired
    private IFacade facade;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            facade.get(ICheckAPIBO.class).checar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
