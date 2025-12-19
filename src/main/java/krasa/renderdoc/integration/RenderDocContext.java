package krasa.renderdoc.integration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;

/*dirty, but works*/
public class RenderDocContext {
	private static final Logger log = Logger.getInstance(RenderDocContext.class.getName());
	private static volatile RenderDocContext currentlyExecuted;

	protected Long appId;
	protected Module module;
	protected String jdkPath;

	public RenderDocContext(Long appId, String jdkPath, Module module) {
		this.appId = appId;
		this.jdkPath = jdkPath;
		this.module = module;
	}

	public Long getAppId() {
		return appId;
	}

	public String getJdkPath() {
		return jdkPath;
	}

	public void save() {
		if (log.isDebugEnabled()) {
			log.debug("saving context: " + this.toString());
		}
		RenderDocContext.currentlyExecuted = this;
	}

	public static RenderDocContext load() {
		return currentlyExecuted;
	}

	public static boolean isValid(RenderDocContext visualVMContext) {
		return visualVMContext != null && visualVMContext.getAppId() != null;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("RenderDocContext");
		sb.append("{appId=").append(appId);
		sb.append(", module='").append(module).append('\'');
//		sb.append(", jdkPath='").append(jdkPath).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
