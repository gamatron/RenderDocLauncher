package krasa.renderdoc.runner;

import com.intellij.debugger.impl.GenericDebuggerRunner;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.ModuleRunProfile;
import com.intellij.execution.configurations.RemoteConnection;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.jar.JarApplicationConfiguration;
import com.intellij.execution.remote.RemoteConfiguration;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import krasa.renderdoc.LogHelper;
import krasa.renderdoc.MyConfigurable;
import krasa.renderdoc.executor.DebugRenderDocExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DebugRenderDocRunner extends GenericDebuggerRunner {
	private static final Logger log = Logger.getInstance(DebugRenderDocRunner.class.getName());

	@NotNull
	public String getRunnerId() {
		return DebugRenderDocExecutor.EXECUTOR_ID;
	}

	@Override
	public void execute(@NotNull final ExecutionEnvironment environment)
			throws ExecutionException {
		LogHelper.print("#execute", this);

		boolean b = MyConfigurable.openSettingsIfNotConfigured(environment.getProject());
		if (!b) {
			return;
		}
		super.execute(environment);
	}

	public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
		return executorId.equals(DebugRenderDocExecutor.EXECUTOR_ID) && (profile instanceof ModuleRunProfile || profile instanceof JarApplicationConfiguration)
				&& !(profile instanceof RemoteConfiguration);
	}

	@Nullable
	@Override
	protected RunContentDescriptor attachVirtualMachine(RunProfileState state, @NotNull ExecutionEnvironment env,
														RemoteConnection connection, boolean pollConnection) throws ExecutionException {
		RunContentDescriptor runContentDescriptor = super.attachVirtualMachine(state, env, connection, pollConnection);
		LogHelper.print("#attachVirtualMachine", this);
		RunnerUtils.runRenderDoc(this, env, state);
		return runContentDescriptor;
	}

}
