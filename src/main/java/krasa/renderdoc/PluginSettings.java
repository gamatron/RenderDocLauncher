package krasa.renderdoc;

import krasa.renderdoc.integration.RenderDocHelper;

public class PluginSettings {

	private String renderDocExecutable;
	private String durationToSetContextToButton = "10000";
	private String delayForRenderDocStart = "10000";
	private String jdkHome;
	private boolean useTabIndex;
	private String tabIndex = "2";
	private boolean sourceConfig = false;
	private boolean useModuleJdk = true;
	private String laf = "";


	public String getRenderDocExecutable() {
		return renderDocExecutable;
	}

	public void setRenderDocExecutable(final String renderDocExecutable) {
		this.renderDocExecutable = renderDocExecutable;
	}


	public static boolean isValid(PluginSettings state) {
		return state != null && RenderDocHelper.isValidPath(state.getRenderDocExecutable());
	}

	public String getDurationToSetContextToButton() {
		return durationToSetContextToButton;
	}

	public void setDurationToSetContextToButton(final String durationToSetContextToButton) {
		this.durationToSetContextToButton = durationToSetContextToButton;
	}

	public String getDelayForRenderDocStart() {
		return delayForRenderDocStart;
	}

	public void setDelayForRenderDocStart(String delayForRenderDocStart) {
		this.delayForRenderDocStart = delayForRenderDocStart;
	}

	public long getDurationToSetContextToButtonAsLong() {
		return Long.parseLong(durationToSetContextToButton);
	}

	public long getDelayForRenderDocStartAsLong() {
		return Long.parseLong(delayForRenderDocStart);
	}


	public String getJdkHome() {
		return jdkHome;
	}

	public void setJdkHome(final String jdkHome) {
		this.jdkHome = jdkHome;
	}

	public boolean isUseTabIndex() {
		return useTabIndex;
	}

	public void setUseTabIndex(final boolean useTabIndex) {
		this.useTabIndex = useTabIndex;
	}

	public String getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(final String tabIndex) {
		this.tabIndex = tabIndex;
	}

	public boolean isSourceConfig() {
		return sourceConfig;
	}

	public void setSourceConfig(final boolean sourceConfig) {
		this.sourceConfig = sourceConfig;
	}

	public boolean isUseModuleJdk() {
		return useModuleJdk;
	}

	public void setUseModuleJdk(final boolean useModuleJdk) {
		this.useModuleJdk = useModuleJdk;
	}


	public String getLaf() {
		return laf;
	}

	public void setLaf(final String laf) {
		this.laf = laf;
	}
}
