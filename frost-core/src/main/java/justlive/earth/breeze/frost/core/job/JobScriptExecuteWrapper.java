package justlive.earth.breeze.frost.core.job;

import java.util.Objects;
import justlive.earth.breeze.frost.api.model.JobExecuteParam;
import justlive.earth.breeze.frost.api.model.JobInfo;
import justlive.earth.breeze.frost.core.util.ScriptJobFactory;
import justlive.earth.breeze.snow.common.base.exception.Exceptions;

/**
 * script模式job包装
 * 
 * @author wubo
 *
 */
public class JobScriptExecuteWrapper extends AbstractJobExecuteWrapper {

  public JobScriptExecuteWrapper() {}

  public JobScriptExecuteWrapper(JobExecuteParam jobExecuteParam) {
    this.jobExecuteParam = jobExecuteParam;
  }

  @Override
  public void doRun() {
    if (!Objects.equals(jobExecuteParam.getType(), JobInfo.TYPE.SCRIPT.name())) {
      throw Exceptions.fail("30002", "执行job类型不匹配");
    }
    this.before();
    IJob job = getIJob();
    job.execute(new DefaultJobContext(jobInfo));
  }

  @Override
  protected IJob getIJob() {
    return ScriptJobFactory.parse(jobInfo.getScript());
  }

}
