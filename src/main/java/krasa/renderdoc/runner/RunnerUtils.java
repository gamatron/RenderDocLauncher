package krasa.renderdoc.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.JvmPatchableProgramRunner;
import com.intellij.openapi.diagnostic.Logger;
import krasa.renderdoc.ApplicationSettingsService;
import krasa.renderdoc.Hacks;
import krasa.renderdoc.LogHelper;
import krasa.renderdoc.integration.RenderDocContext;
import krasa.renderdoc.integration.RenderDocHelper;

public class RunnerUtils {
	private static final Logger log = Logger.getInstance(RunnerUtils.class.getName());

	static void runRenderDoc(final JvmPatchableProgramRunner runner, ExecutionEnvironment env, RunProfileState state) throws ExecutionException {
		try {
			// tomcat uses PatchedLocalState
			if (state.getClass().getSimpleName().equals(Hacks.BUNDLED_SERVERS_RUN_PROFILE_STATE)) {
				LogHelper.print("#runRenderDoc ExecutionEnvironment", runner);
				new Thread() {
					@Override
					public void run() {
						LogHelper.print("#Thread run", this);
						try {
							Thread.sleep(ApplicationSettingsService.getInstance().getState().getDelayForRenderDocStartAsLong());
							RenderDocHelper.startRenderDoc(RenderDocContext.load(), env.getProject(), runner);
						} catch (Throwable e) {
							log.error(e);
						}
					}
				}.start();
			} else {
				RenderDocHelper.startRenderDoc(RenderDocContext.load(), env.getProject(), runner);
			}
		} catch (Throwable e) {
			log.error(e);
		}
	}
}
